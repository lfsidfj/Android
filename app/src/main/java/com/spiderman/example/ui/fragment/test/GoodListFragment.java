package com.spiderman.example.ui.fragment.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.spiderman.example.R;
import com.spiderman.example.adapter.recyclerviewadapter.base.BaseRecyclerAdapter;
import com.spiderman.example.adapter.recyclerviewadapter.base.BaseRecyclerHolder;
import com.spiderman.example.bean.test.GoodsList;
import com.spiderman.example.config.HttpAddress;
import com.spiderman.example.presentation.presenter.PagingPresenter;
import com.spiderman.example.ui.fragment.HttpBaseFragment;
import com.spiderman.example.util.ToastUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ${wangjiasheng} on 2017/12/21 0021.
 */

public class GoodListFragment extends HttpBaseFragment {
    private int gc_id;
    private PagingPresenter pagingBasePresenter;
    private XRecyclerView xRecyclerView;
    private BaseRecyclerAdapter<GoodsList.Goods> recyclerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pagingBasePresenter=new PagingPresenter(this,GoodsList.class, HttpAddress.GoodList,HttpAddress.GoodList);
        Bundle bundle=getArguments();
        gc_id=bundle.getInt("gc_id");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView==null){
            rootView=inflater.inflate(R.layout.xrecycler_layout,null);
        }
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        xRecyclerView = (XRecyclerView) rootView.findViewById(R.id.xRecyclerView);
        xRecyclerView.getItemAnimator().setChangeDuration(0);
        xRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
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
        xRecyclerView.setAdapter(recyclerAdapter);/*
        xRecyclerView.setLongClickable(true);
        registerForContextMenu(xRecyclerView);*/
    }

    @Override
    public void setResultData(Object object, String dataType) {
        super.setResultData(object, dataType);
        if(HttpAddress.GoodList.equals(dataType)){
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
    protected void lazyLoad() {
        super.lazyLoad();
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("gc_id",gc_id);
        pagingBasePresenter.goToPage(1, param);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
    /*@Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        *//*AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;*//*
        menu.add(0, 1, Menu.NONE, "查看详情");
    }*/


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        GoodsList.Goods goods=recyclerAdapter.getitem(info.position);
        switch (item.getItemId()){
            case 1:
                ToastUtils.getInstance().showToast(getContext(),goods.getGoods_name());
        }
        return super.onContextItemSelected(item);
    }
}
