package com.spiderman.example.presentation.presenter;

import com.google.gson.Gson;
import com.lzy.okgo.model.Progress;
import com.spiderman.example.bean.BaseBean;
import com.spiderman.example.bean.HeadBean;
import com.spiderman.example.config.HttpAddress;
import com.spiderman.example.model.BaseModel;
import com.spiderman.example.ui.viewfeatures.HttpMvpView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Map;

/**
 * Created by HP on 2018/6/23.
 */

public class CommonPresenter implements BasePresenter {
    public HttpMvpView iView;
    public BaseModel model;

    public CommonPresenter(HttpMvpView iView) {
        this.iView=iView;
        this.model=new BaseModel(this);
    }
    /**
     * 加载
     */
    public void request(String url,Map param,String dataType,Class<? extends BaseBean> clazz){
        request(url,param,dataType,clazz,true);
    }
    /**
     * 加载
     */
    public void request(String url,Map param,String dataType,Class<? extends BaseBean> clazz,boolean showLoading){
        if(showLoading){
            iView.showLoading();
        }
        model.request(url.contains("http")?url:(HttpAddress.mBaseUrl+url),param,iView,dataType,clazz);
    }

    public void downLoad(String url,String dataType){
        model.downLoad(url,iView,dataType);
    }

    @Override
    public void receiveData(String object, String dataType,Class<? extends BaseBean> clazz) {
        Gson gson=new Gson();
        try {
            JSONObject json=new JSONObject(object);
            BaseBean model = gson.fromJson(json.getJSONObject("content").toString(), clazz);
            setViewData(model,dataType);
            iView.hideLoading();
            iView.complete(dataType);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void receiveData(String object, String dataType) {
        Gson gson=new Gson();
        try {
            HeadBean model = gson.fromJson(object, HeadBean.class);
            setViewData(model,dataType);
            iView.hideLoading();
            iView.complete(dataType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void receiveHttpError(String error, String dataType) {
        iView.showHttpError(error, dataType);
        iView.hideLoading();
        iView.complete(dataType);
    }

    @Override
    public void setViewData(Object object, String dataType) {
        iView.setResultData(object,dataType);
    }

    @Override
    public void receiveProgress(Progress object, String dataType) {
        setViewProgress(object,dataType);
    }

    @Override
    public void setViewProgress(Progress progress, String dataType) {
        iView.onProgress(progress,dataType);
    }

    @Override
    public void receiveFile(File file, String dataType) {
        setViewFile(file,dataType);
    }

    @Override
    public void setViewFile(File file, String dataType) {
        iView.onDownloadSuccess(file,dataType);
    }
}
