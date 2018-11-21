package com.spiderman.example.ui.fragment.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spiderman.example.R;
import com.spiderman.example.bean.test.GoodsClassList;
import com.spiderman.example.config.HttpAddress;
import com.spiderman.example.presentation.presenter.CommonPresenter;
import com.spiderman.example.ui.fragment.HttpBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 2018/4/24.
 */

public class IndexFragment extends HttpBaseFragment {
    private TabLayout tab_free_title;
    private List<Fragment> fragmentList;
    private List<String> list_title;
    private ViewPager viewPager;
    private CommonPresenter goodClassPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        goodClassPresenter=new CommonPresenter(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView==null){
            rootView=inflater.inflate(R.layout.fragment_index,null);
        }
        initView(rootView);
        initData();
        return rootView;
    }

    private void initView(View v){
        tab_free_title= (TabLayout) v.findViewById(R.id.tab_free_title);
        viewPager= (ViewPager) v.findViewById(R.id.discoverfragment_page);
    }

    private void initData() {
        goodClassPresenter.request(HttpAddress.goodClassList,null,HttpAddress.goodClassList, GoodsClassList.class);
    }

    @Override
    public void setResultData(Object object, String dataType) {
        super.setResultData(object, dataType);
        if(HttpAddress.goodClassList.equals(dataType)){
            GoodsClassList goodsClassBean= (GoodsClassList) object;
            fragmentList = new ArrayList<>();
            list_title=new ArrayList<>();
            for(GoodsClassList.GoodsClass gc:goodsClassBean.getGoodsClass()){
                GoodListFragment goodListFragment=new GoodListFragment();
                Bundle bundle=new Bundle();
                bundle.putInt("gc_id",gc.getId());
                goodListFragment.setArguments(bundle);
                fragmentList.add(goodListFragment);
                list_title.add(gc.getClassName());
            }
            PagerAdapter pagerAdapter=new PagerAdapter(getFragmentManager(),fragmentList,list_title);
            viewPager.setAdapter(pagerAdapter);
            viewPager.setOffscreenPageLimit(goodsClassBean.getGoodsClass().size());
            tab_free_title.setTabMode(TabLayout.MODE_SCROLLABLE);
            tab_free_title.setupWithViewPager(viewPager);
        }
    }

    @Override
    public void complete(String dataType) {

    }

    class PagerAdapter extends FragmentPagerAdapter {
        List<Fragment> fragmentList;
        List<String> titleList;

        public PagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> titleList) {
            super(fm);
            this.fragmentList = fragmentList;
            this.titleList = titleList;
        }

        @Override

        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list_title.get(position % list_title.size());
        }
    }
}
