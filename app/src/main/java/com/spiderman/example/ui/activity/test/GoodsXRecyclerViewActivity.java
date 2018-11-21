package com.spiderman.example.ui.activity.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.spiderman.example.R;
import com.spiderman.example.adapter.recyclerviewadapter.base.BaseRecyclerAdapter;
import com.spiderman.example.adapter.recyclerviewadapter.base.BaseRecyclerHolder;
import com.spiderman.example.bean.test.GoodsList;
import com.spiderman.example.config.HttpAddress;
import com.spiderman.example.presentation.presenter.PagingPresenter;
import com.spiderman.example.ui.activity.HttpBaseActivity;
import com.spiderman.example.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 2018/6/21.
 */

public class GoodsXRecyclerViewActivity extends HttpBaseActivity {

    private PagingPresenter pagingBasePresenter;
    private XRecyclerView xRecyclerView;
    private BaseRecyclerAdapter<GoodsList.Goods> recyclerAdapter;
    private List<GoodsList.Goods> goodsList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        pagingBasePresenter=new PagingPresenter(GoodsXRecyclerViewActivity.this, GoodsList.class, HttpAddress.GoodList,HttpAddress.GoodList);
        goodsList=new ArrayList<GoodsList.Goods>();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void loadLayout(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.xrecycler_layout);

        xRecyclerView = (XRecyclerView) findViewById(R.id.xRecyclerView);
        View head= LayoutInflater.from(getContext()).inflate(R.layout.head,null);
        xRecyclerView.addHeaderView(head);
        xRecyclerView.getItemAnimator().setChangeDuration(0);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                pagingBasePresenter.refresh();
            }

            @Override
            public void onLoadMore() {
                if (pagingBasePresenter.isLastPage()) {
                    showToast("最后一页");
                    xRecyclerView.loadMoreComplete();
                    return;
                }
                pagingBasePresenter.loadMore();
            }
        });
        recyclerAdapter=new BaseRecyclerAdapter<GoodsList.Goods>(getContext(),null,R.layout.item_goods) {
            @Override
            public void convert(BaseRecyclerHolder holder, GoodsList.Goods data, int position, boolean isScrolling) {
                holder.setText(R.id.goods_name,data.getGoods_name());
                holder.setText(R.id.goods_price,"￥"+data.getGoods_current_price());
                holder.setText(R.id.goods_current_price,"￥"+data.getGoods_current_price());
                holder.setImageByUrl(R.id.goods_imag,data.getGoods_main_photo());
            }
        };
        recyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<GoodsList.Goods>(){

            @Override
            public void onItemClick(BaseRecyclerHolder viewHolder, GoodsList.Goods data, int position) {
                ToastUtils.getInstance().showToast(getContext(),data.getGoods_name());
            }
        });
        xRecyclerView.setAdapter(recyclerAdapter);
        xRecyclerView.setLongClickable(true);
        registerForContextMenu(xRecyclerView);
    }

    @Override
    protected void loadData() {
        pagingBasePresenter.goToPage(1, null);
    }

    @Override
    public void setResultData(Object object, String contentType) {
        super.setResultData(object, contentType);
        if(HttpAddress.GoodList.equals(contentType)){
            GoodsList goodListBean = (GoodsList) object;
            if(pagingBasePresenter.isFirstPage()){
                recyclerAdapter.clear();
            }
            recyclerAdapter.setData(goodListBean.getGoodsList());
        }
    }

    @Override
    public void complete(String dataType) {
        if (pagingBasePresenter.isFirstPage()) {
            xRecyclerView.refreshComplete();
        } else {
            xRecyclerView.loadMoreComplete();
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {/*
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;*/
        menu.add(0, 1, Menu.NONE, "查看详情");
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case 1:
                ToastUtils.getInstance().showToast(getContext(),goodsList.get(info.position).getGoods_name());
        }
        return super.onContextItemSelected(item);
    }
}

