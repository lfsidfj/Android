package com.spiderman.example.presentation.presenter;

import com.lzy.okgo.model.Progress;
import com.spiderman.example.bean.BaseBean;

import java.io.File;

/**
 * Created by HP on 2018/6/19.
 */

public interface BasePresenter {

    void receiveData(String object, String dataType, Class<? extends BaseBean> clazz);

    void receiveData(String object, String dataType);

    void receiveHttpError(String error, String dataType);

    void setViewData(Object object, String dataType);

    void receiveProgress(Progress object, String dataType);

    void setViewProgress(Progress progress, String dataType);

    void receiveFile(File object, String dataType);

    void setViewFile(File file, String dataType);

}
