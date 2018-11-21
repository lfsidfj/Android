package com.spiderman.example.ui.activity.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.scu.miomin.shswiperefresh.core.SHSwipeRefreshLayout;
import com.scu.miomin.shswiperefresh.view.SHListView;
import com.spiderman.example.R;
import com.spiderman.example.adapter.listviewadapter.CommonAdapter;
import com.spiderman.example.adapter.listviewadapter.ViewHolder;
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

public class GoodsPullToRefreshListViewActivity extends HttpBaseActivity {

    private PagingPresenter pagingBasePresenter;
    private SHSwipeRefreshLayout swipeRefreshLayout;
    private SHListView listView;
    BaseAdapter adapter;
    private List<GoodsList.Goods> goodsList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        pagingBasePresenter=new PagingPresenter(GoodsPullToRefreshListViewActivity.this, GoodsList.class, HttpAddress.GoodList,HttpAddress.GoodList);
        goodsList=new ArrayList<GoodsList.Goods>();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void loadLayout(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.pull_to_refresh_list_layout);
        initListView();

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
                goodsList.clear();
            }
            goodsList.addAll(goodListBean.getGoodsList());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void complete(String dataType) {
        if (pagingBasePresenter.isFirstPage()) {
            //xRecyclerView.refreshComplete();
            swipeRefreshLayout.finishRefresh();
        } else {
            //xRecyclerView.loadMoreComplete();
            swipeRefreshLayout.finishLoadmore();
        }
    }
    /**
     * 初始化列表控件上下拉的状态
     */
    private void initListView() {
        // TODO Auto-generated method stub
        swipeRefreshLayout= (SHSwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SHSwipeRefreshLayout.SHSOnRefreshListener() {
            @Override
            public void onRefresh() {
                pagingBasePresenter.refresh();
            }

            @Override
            public void onLoading() {
                pagingBasePresenter.loadMore();
            }

            @Override
            public void onRefreshPulStateChange(float v, int i) {

            }

            @Override
            public void onLoadmorePullStateChange(float v, int i) {

            }
        });

        listView= (SHListView) findViewById(R.id.listView);
        listView.setAdapter(adapter = new CommonAdapter<GoodsList.Goods>(this, goodsList, R.layout.item_goods) {
            @Override
            public void convert(ViewHolder helper, GoodsList.Goods item) {
                helper.setText(R.id.goods_name,item.getGoods_name())
                        .setText(R.id.goods_price,item.getGoods_price()+"")
                        .setText(R.id.goods_current_price,item.getGoods_current_price()+"")
                        .setImageByUrl(R.id.goods_imag,item.getGoods_main_photo());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.getInstance().showToast(getContext(),goodsList.get(position).getGoods_name());
            }
        });
    }
}

