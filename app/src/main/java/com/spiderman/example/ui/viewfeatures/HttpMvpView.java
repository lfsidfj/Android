package com.spiderman.example.ui.viewfeatures;

import com.lzy.okgo.model.Progress;

import java.io.File;

/**
 * Created by HP on 2018/6/19.
 */

public interface HttpMvpView extends MvpView {

    void showLoading();

    void onProgress(Progress progress, String dataType);

    void onDownloadSuccess(File file, String dataType);

    void setResultData(Object object, String dataType);

    void showHttpError(String error, String dataType);

    void hideLoading();

    void complete(String dataType);
}
