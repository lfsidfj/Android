package com.spiderman.example.ui.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.spiderman.example.R;

import butterknife.ButterKnife;

/**
 * Created by csp on 2017/11/28.
 *
 */

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLayout(savedInstanceState);
        initView();
        loadData();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    protected abstract void loadLayout(Bundle savedInstanceState);

    protected abstract void initView();

    protected abstract void loadData();

    /**
     * 标题栏设置
     * @param titleResId 标题
     * @param isReturn 是否下显示返回按钮
     */
    public void setTitleBar(int titleResId, boolean isReturn){
        if(isReturn){
            $(R.id.title_bar_return).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }else{
            $(R.id.title_bar_return).setVisibility(View.GONE);
        }
        ((TextView)$(R.id.title_bar_title)).setText(getString(titleResId));
    }

    public void setTitleBar(String title, boolean isReturn){
        if(isReturn){
            $(R.id.title_bar_return).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }else{
            $(R.id.title_bar_return).setVisibility(View.GONE);
        }
        ((TextView)$(R.id.title_bar_title)).setText(title);
    }

    /**
     * 获取布局控件
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T $(int id)
    {
        return (T) super.findViewById(id);
    }
    public abstract Context getContext();
}
