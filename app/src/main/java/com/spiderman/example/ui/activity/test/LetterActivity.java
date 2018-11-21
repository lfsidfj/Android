package com.spiderman.example.ui.activity.test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.spiderman.example.R;
import com.spiderman.example.adapter.letteradapter.SortAdapter;
import com.spiderman.example.bean.test.LetterList;
import com.spiderman.example.config.HttpAddress;
import com.spiderman.example.presentation.presenter.CommonPresenter;
import com.spiderman.example.ui.activity.HttpBaseActivity;
import com.spiderman.example.ui.otherview.SideBar;

/**
 * Created by HP on 2018/7/1.
 */

public class LetterActivity extends HttpBaseActivity {
    private RecyclerView recyclerView;
    private CommonPresenter brandPresenter;
    private SideBar sideBar;
    private TextView dialog;
    private SortAdapter sortAdapter;
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        brandPresenter=new CommonPresenter(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void loadLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_first_letter_list);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_first_letter_list);
        dialog = (TextView) findViewById(R.id.dialog);
        setTitleBar("品牌", true);
        sideBar = (SideBar) findViewById(R.id.sideBar);
        sideBar.setTextView(dialog);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        sortAdapter = new SortAdapter(getContext(), getLayoutInflater(), null);
        recyclerView.setAdapter(sortAdapter);
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                dialog.setText(s);
                int position = sortAdapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    manager.scrollToPositionWithOffset(position, 0);
                }
            }
        });
    }

    @Override
    protected void loadData() {
        brandPresenter.request(HttpAddress.bigBrandList,null,HttpAddress.bigBrandList, LetterList.class);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void complete(String dataType) {

    }

    @Override
    public void setResultData(Object object, String contentType) {
        LetterList letterList= (LetterList) object;
        sortAdapter.updateList(letterList.getBig_brand_list());
        super.setResultData(object, contentType);
    }
}
