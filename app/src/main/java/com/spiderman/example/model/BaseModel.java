package com.spiderman.example.model;

import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Progress;
import com.spiderman.example.bean.BaseBean;
import com.spiderman.example.presentation.presenter.BasePresenter;
import com.spiderman.example.util.http.OkGoEngine;
import com.spiderman.example.util.http.RequestCallBack;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/22.
 */

public class BaseModel {

    private BasePresenter presenter;
    private OkGoEngine okGoEngine;

    public BaseModel(BasePresenter presenter) {
        this.presenter=presenter;
        okGoEngine = OkGoEngine.getInstance();
    }

    public void request(String url, Map<String,Object> param, Object tag, final String dataType, final Class<? extends BaseBean> clazz){
        HttpParams map = new HttpParams();
        if(param!=null){
            Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                if(entry.getValue() instanceof File){
                    map.put(entry.getKey(), (File)entry.getValue());
                }else{
                    map.put(entry.getKey(), entry.getValue()+"");
                }
            }
        }

        okGoEngine.postMap(tag, url, map, new RequestCallBack() {
            @Override
            public void onsuccess(String json) {
                if(clazz!=null){
                    presenter.receiveData(json,dataType,clazz);
                }else {
                    presenter.receiveData(json,dataType);
                }
            }

            @Override
            public void onfailed(String exception) {
                presenter.receiveHttpError(exception,dataType);
            }

            @Override
            public void onprogress(Progress progress) {
                presenter.receiveProgress(progress,dataType);
            }

            @Override
            public void onDownloadSuccess(File file) {

            }
        });
    }
    public void downLoad(String url, Object tag, final String dataType){

        okGoEngine.downLoad(tag, url, new RequestCallBack() {
            @Override
            public void onsuccess(String json) {

            }

            @Override
            public void onfailed(String exception) {
                presenter.receiveHttpError(exception,dataType);
            }

            @Override
            public void onprogress(Progress progress) {
                presenter.receiveProgress(progress,dataType);
            }

            @Override
            public void onDownloadSuccess(File file) {
                presenter.receiveFile(file,dataType);
            }
        });
    }
}
