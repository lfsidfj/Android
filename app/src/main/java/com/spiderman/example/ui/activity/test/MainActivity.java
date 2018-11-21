package com.spiderman.example.ui.activity.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.LinearLayout;

import com.lzy.okgo.OkGo;
import com.spiderman.example.R;
import com.spiderman.example.ui.activity.HttpBaseActivity;
import com.spiderman.example.ui.fragment.test.CartFragment;
import com.spiderman.example.ui.fragment.test.CenterFragment;
import com.spiderman.example.ui.fragment.test.IndexFragment;
import com.spiderman.example.ui.fragment.test.SortFragment;
import com.spiderman.example.util.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends HttpBaseActivity {
    private LinearLayout index;
    private LinearLayout sort;
    private LinearLayout cart;
    private LinearLayout center;
    private NoScrollViewPager mainViewPager;
    List<Fragment> fragmentList=new ArrayList<Fragment>();
    MainPageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void loadLayout(Bundle savedInstanceState) {
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        index= (LinearLayout) findViewById(R.id.index);
        sort= (LinearLayout) findViewById(R.id.sort);
        cart= (LinearLayout) findViewById(R.id.cart);
        center= (LinearLayout) findViewById(R.id.center);
        mainViewPager= (NoScrollViewPager) findViewById(R.id.mainViewPager);
        index.setOnClickListener(this);
        sort.setOnClickListener(this);
        cart.setOnClickListener(this);
        center.setOnClickListener(this);
        fragmentList.add(new IndexFragment());
        fragmentList.add(new SortFragment());
        fragmentList.add(new CartFragment());
        fragmentList.add(new CenterFragment());
        adapter=new MainPageAdapter(getSupportFragmentManager(),fragmentList);
        mainViewPager.setAdapter(adapter);
        mainViewPager.setOffscreenPageLimit(fragmentList.size());
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void setResultData(Object object, String contentType) {
        super.setResultData(object, contentType);
    }

    @Override
    public void complete(String dataType) {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.index:
                mainViewPager.setCurrentItem(0,false);
                break;
            case R.id.sort:
                mainViewPager.setCurrentItem(1,false);
                break;
            case R.id.cart:
                mainViewPager.setCurrentItem(2,false);
                break;
            case R.id.center:
                mainViewPager.setCurrentItem(3,false);
                break;
        }
    }
    class MainPageAdapter extends FragmentPagerAdapter{
        List<Fragment> fragmentList;
        public MainPageAdapter(FragmentManager fm,List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList=fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
}
