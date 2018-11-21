package com.spiderman.example.util.http;

import com.lzy.okgo.model.Progress;

import java.io.File;

/**
 * Created by ${wong} on 2017/9/13.
 */

public interface RequestCallBack {
    /**
     * 请求成功回调（下载不回调）
     * @param json
     */
    void onsuccess(String json);

    /**
     * 请求失败回调
     * @param exception
     */
    void onfailed(String exception);

    /**
     * 上传、下载进度回调
     * @param progress
     */
    void onprogress(Progress progress);

    /**
     * 下载文成功回调
     * @param file
     */
    void onDownloadSuccess(File file);
}
