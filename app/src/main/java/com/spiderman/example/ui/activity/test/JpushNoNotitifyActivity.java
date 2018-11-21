package com.spiderman.example.ui.activity.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.spiderman.example.R;
import com.spiderman.example.ui.activity.BaseActivity;
import com.spiderman.example.util.jpush.ExampleUtil;
import com.spiderman.example.util.jpush.LocalBroadcastManager;
import com.spiderman.example.util.jpush.TagAliasOperatorHelper;

import static com.spiderman.example.util.jpush.TagAliasOperatorHelper.ACTION_CLEAN;
import static com.spiderman.example.util.jpush.TagAliasOperatorHelper.sequence;
import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2018/6/28.
 */

public class JpushNoNotitifyActivity extends BaseActivity {
    private TextView textView;
    private TextView initPush;
    private TextView setTag;
    private TextView deleteTag;
    private TextView stopPush;
    private TextView resumePush;
    public static boolean isForeground = false;
    @Override
    protected void loadLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_jpush_no_notity);
    }

    @Override
    protected void initView() {
        setTitleBar("极光推送",true);
        textView= (TextView) findViewById(R.id.textView);
        initPush= (TextView) findViewById(R.id.initPush);
        setTag= (TextView) findViewById(R.id.setTag);
        deleteTag= (TextView) findViewById(R.id.deleteTag);
        stopPush= (TextView) findViewById(R.id.stopPush);
        resumePush= (TextView) findViewById(R.id.resumePush);
        initPush.setOnClickListener(this);
        setTag.setOnClickListener(this);
        deleteTag.setOnClickListener(this);
        stopPush.setOnClickListener(this);
        resumePush.setOnClickListener(this);
        registerMessageReceiver();
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
        switch (v.getId()){
            case R.id.initPush:
                JPushInterface.init(getApplicationContext());
                break;
            case R.id.setTag:
                setTag();
                break;
            case R.id.deleteTag:
                cleanTag();
                break;
            case R.id.stopPush:
                JPushInterface.stopPush(getApplicationContext());
                break;
            case R.id.resumePush:
                JPushInterface.resumePush(getApplicationContext());
                break;
            default:
                break;
        }
    }
    private void init(){
        JPushInterface.init(getApplicationContext());
    }
    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!ExampleUtil.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }
                    setCostomMsg(showMsg.toString());
                }
            } catch (Exception e){
            }
        }
    }
    private void setCostomMsg(String msg){
        if (null != textView) {
            textView.setText(msg);
        }
    }
    /**
     * 设置极光推送
     */
    private void setTag(){
        TagAliasOperatorHelper.TagAliasBean tagAliasBean = new TagAliasOperatorHelper.TagAliasBean();
        tagAliasBean.action = 2;
        Set<String> tags=new HashSet<>();
        tags.add("csp");
        tagAliasBean.tags = tags;
        tagAliasBean.isAliasAction = false;
        sequence++;
        TagAliasOperatorHelper.getInstance().handleAction(getApplicationContext(),sequence,tagAliasBean);
    }

    private void cleanTag(){
        TagAliasOperatorHelper.TagAliasBean tagAliasBean = new TagAliasOperatorHelper.TagAliasBean();
        tagAliasBean.action = ACTION_CLEAN;
        Set<String> tags=new HashSet<>();
        //tags.add("csp");
        tagAliasBean.tags = tags;
        tagAliasBean.isAliasAction = false;
        sequence++;
        TagAliasOperatorHelper.getInstance().handleAction(getApplicationContext(),sequence,tagAliasBean);
    }
}
