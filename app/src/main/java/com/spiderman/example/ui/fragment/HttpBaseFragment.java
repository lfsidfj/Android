package com.spiderman.example.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.okgo.model.Progress;
import com.spiderman.example.ui.viewfeatures.HttpMvpView;
import com.spiderman.example.ui.dialog.LoadingDialog;

import java.io.File;

/**
 * Created by Administrator on 2017/11/29 0029.
 * 子类中swipeRefreshLayout名字要一致
 */

public abstract class HttpBaseFragment extends BaseFragment implements HttpMvpView {

    private SwipeRefreshLayout swipeRefreshLayout;

    public HttpBaseFragment() {
        super();
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void showLoading() {
        if (null == getContext()) {
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
    public void setResultData(Object object, String dataType) {
    }

    @Override
    public void showHttpError(String error, String dataType) {
        if(error!=null&&error.equals("")){
            showToast(error);
        }else{
            showToast("获取数据失败");
        }
        hideLoading();
    }
    @Override
    public void hideLoading() {

        if (null == getContext()) {
            return;
        }
        hideDialogLoading();
    }

    @Override
    public void complete(String dataType) {

    }

    protected void showDialogLoading() {
        LoadingDialog.showLoadingDialog(getContext(), "");
    }

    protected void hideDialogLoading() {
        LoadingDialog.hideLoadingDialog();
    }
}

