package com.spiderman.example.ui.activity.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;
import com.spiderman.example.R;
import com.spiderman.example.adapter.MyImageAdapter;
import com.spiderman.example.ui.activity.BaseActivity;
import com.spiderman.example.util.PhotoViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 2018/7/1.
 */

public class PhotoViewActivity extends BaseActivity implements View.OnClickListener {

    public static final String TAG = PhotoViewActivity.class.getSimpleName();
    private PhotoViewPager mViewPager;
    private int currentPosition;
    private MyImageAdapter adapter;
    private TextView mTvImageCount;
    private List<String> Urls;

    @Override
    protected void loadLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_photo_view);
    }

    protected void initView() {
        mViewPager = (PhotoViewPager) findViewById(R.id.view_pager_photo);
        mTvImageCount = (TextView) findViewById(R.id.tv_image_count);

    }

    @Override
    public Context getContext() {
        return this;
    }

    protected void loadData() {

        Intent intent = getIntent();
        currentPosition = intent.getIntExtra("currentPosition", 0);
        Urls=new ArrayList<String>();
        Urls.add("http://img1.imgtn.bdimg.com/it/u=1868686557,2333875000&fm=27&gp=0.jpg");
        Urls.add("http://pic31.nipic.com/20130708/7447430_090053177000_2.jpg");
        Urls.add("http://pic10.nipic.com/20101104/3995638_193928073578_2.jpg");
        Urls.add("http://pic14.photophoto.cn/20100127/0036036848818577_b.jpg");
        Urls.add("http://pic30.nipic.com/20130624/7447430_170946887000_2.jpg");
        Urls.add("http://img01.taopic.com/160626/240389-1606260Z54370.jpg");
        Urls.add("http://img04.tooopen.com/images/20131214/sy_51865286928.jpg");
        Urls.add("http://pic1.16pic.com/00/57/58/16pic_5758899_b.jpg");

        adapter = new MyImageAdapter(Urls, this);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(currentPosition, false);
        mTvImageCount.setText(currentPosition+1 + "/" + Urls.size());
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentPosition = position;
                mTvImageCount.setText(currentPosition + 1 + "/" + Urls.size());
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }
}
