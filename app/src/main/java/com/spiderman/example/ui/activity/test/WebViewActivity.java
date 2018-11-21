package com.spiderman.example.ui.activity.test;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.spiderman.example.R;
import com.spiderman.example.ui.activity.BaseActivity;


/**
 * Created by HP on 2018/5/17.
 */

public class WebViewActivity extends BaseActivity {
    private static final String TAG = "WebViewActivity";
    private BridgeWebView mBridgeWebView;
    @Override
    protected void loadLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_web_view);
    }

    @Override
    protected void initView() {
        setTitleBar("WebView测试",true);
        mBridgeWebView= (BridgeWebView) findViewById( R.id.test_bridge_webView);
        mBridgeWebView.loadUrl("file:///android_asset/wx.html");

        mBridgeWebView.setDefaultHandler(new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.e(TAG, "DefaultHandler接收全部来自web的数据："+data);
                function.onCallBack("DefaultHandler收到Web发来的数据，回传数据给你");
            }
        });

        //必须和js同名函数，注册具体执行函数，类似java实现类。
        //第一参数是订阅的java本地函数名字 第二个参数是回调Handler , 参数返回js请求的resqustData,function.onCallBack（）回调到js，调用function(responseData)
        mBridgeWebView.registerHandler("submitFromWeb", new BridgeHandler() {

            @Override
            public void handler(String data, CallBackFunction function) {
                Log.e(TAG, "指定Handler接收来自web的数据：" + data);
                function.onCallBack("指定Handler收到Web发来的数据，回传数据给你");
            }
        });
        findViewById(R.id.to_web_default).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBridgeWebView.send("发送数据给web默认接收",new CallBackFunction(){
                    @Override
                    public void onCallBack(String data) {
                        Log.e(TAG, "来自web的回传数据：" + data);
                    }
                });
            }
        });
        findViewById(R.id.to_web).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBridgeWebView.callHandler("functionInJs","发送数据给web指定接收",new CallBackFunction(){
                    @Override
                    public void onCallBack(String data) {
                        Log.e(TAG, "来自web的回传数据：" + data);
                    }
                });
            }
        });
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
