package com.spiderman.example.ui.activity.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spiderman.example.ui.activity.BaseActivity;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2018/6/28.
 */

public class JpushActivity extends BaseActivity {
    @Override
    protected void loadLayout(Bundle savedInstanceState) {
        TextView tv = new TextView(this);
        tv.setText("用户自定义打开的Activity");
        Intent intent = getIntent();
        if (null != intent) {
            Bundle bundle = getIntent().getExtras();
            String title = null;
            String content = null;
            String extra = null;
            if(bundle!=null){
                title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
                content = bundle.getString(JPushInterface.EXTRA_ALERT);
                extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
            }
            tv.setText("Title : " + title + "  " + "Content : " + content+"Extra:"+extra);
        }
        addContentView(tv, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void onClick(View v) {

    }
}
