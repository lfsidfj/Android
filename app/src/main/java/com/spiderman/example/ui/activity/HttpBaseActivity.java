package com.spiderman.example.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;

import com.lzy.okgo.model.Progress;
import com.spiderman.example.ui.dialog.LoadingDialog;
import com.spiderman.example.ui.viewfeatures.HttpMvpView;
import com.spiderman.example.util.ToastUtils;

import java.io.File;

/**
 * Created by Administrator on 2017/11/28 0028.
 * pullRefresh表示使用swiperefresh功能，列表推荐使用XRecyclerView
 * supportX表示使用XRecyclerView，包含refresh、loadmore等方法
 */

public abstract class HttpBaseActivity extends BaseActivity implements HttpMvpView {

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.mContext = this;
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public void showLoading() {
        if (null == mContext) {
            return;
        }
        showDialogLoading();

    }

    @Override
    public void onProgress(Progress progress,String dataType) {

    }

    @Override
    public void onDownloadSuccess(File file,String dataType) {

    }

    @Override
    public void setResultData(Object object, String contentType) {
    }

    @Override
    public void showHttpError(String error, String dataType) {
        if(error!=null&&!error.equals("")){
            showToast(error);
        }else{
            showToast("获取数据失败");
        }
    }

    @Override
    public void hideLoading() {
        if (null == mContext) {
            return;
        }
        hideDialogLoading();
    }

    @Override
    public void complete(String dataType) {

    }

    protected void showDialogLoading() {
        LoadingDialog.showLoadingDialog(this, "");
    }

    protected void hideDialogLoading() {
        LoadingDialog.hideLoadingDialog();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void showToast(String string) {
        ToastUtils.getInstance().showToast(getContext(), string);
    }

}
