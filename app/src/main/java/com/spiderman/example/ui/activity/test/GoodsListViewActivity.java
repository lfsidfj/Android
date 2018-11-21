package com.spiderman.example.ui.activity.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

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

public class GoodsListViewActivity extends HttpBaseActivity {

    private PagingPresenter pagingBasePresenter;
    private ListView listView;
    BaseAdapter adapter;
    private List<GoodsList.Goods> goodsList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        pagingBasePresenter=new PagingPresenter(GoodsListViewActivity.this, GoodsList.class, HttpAddress.GoodList,HttpAddress.GoodList);
        goodsList=new ArrayList<GoodsList.Goods>();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void loadLayout(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.list_layout);

        listView= (ListView) findViewById(R.id.listView);
        View head= LayoutInflater.from(getContext()).inflate(R.layout.head,null);
        listView.addHeaderView(head);
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
                if(position>0){
                    ToastUtils.getInstance().showToast(getContext(),goodsList.get(position-1).getGoods_name());
                }
            }
        });
        registerForContextMenu(listView);
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
        } else {
            //xRecyclerView.loadMoreComplete();
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.add(0, 1, Menu.NONE, "查看详情"+info.position);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case 1:
                ToastUtils.getInstance().showToast(getContext(),goodsList.get(info.position-1).getGoods_name());
        }
        return super.onContextItemSelected(item);
    }
}

