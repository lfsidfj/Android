package com.spiderman.example.util.http;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.spiderman.example.util.LogUtil;

import java.io.File;

/**
 * Created by ${wangjiasheng} on 2017/12/25 0025.
 */

public class OkGoEngine {

    private static OkGoEngine instance;

    public static OkGoEngine getInstance() {
        if (null == instance) {
            synchronized (OkGoEngine.class) {
                if (null == instance) {
                    instance = new OkGoEngine();
                }
            }
        }
        return instance;
    }

    /**
     * get请求
     * @param tag
     * @param url
     * @param cacheKey
     * @param requestCallBack
     */
    public void get(Object tag, String url, String cacheKey, CacheMode cacheMode, final RequestCallBack requestCallBack) {
        LogUtil.info("get", "请求接口"+url);
        OkGo.<String>get(url).tag(tag).cacheKey(cacheKey).cacheMode(cacheMode)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        requestCallBack.onsuccess(response.body());
                        LogUtil.info("get", "-------------onSuccess:请求成功了");
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        requestCallBack.onfailed(response.body());
                    }

                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        super.onStart(request);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }

                    @Override
                    public void onCacheSuccess(Response<String> response) {
                        super.onCacheSuccess(response);
                        requestCallBack.onsuccess(response.body());
                    }
                });
    }
    public void get(Object tag, String url, final RequestCallBack requestCallBack) {
        OkGo.<String>get(url).tag(tag).cacheMode(CacheMode.NO_CACHE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        requestCallBack.onsuccess(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        requestCallBack.onfailed(response.body());
                    }

                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        super.onStart(request);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    /**
     * post请求
     */
    public void post(Object tag, String url, String cacheKey, CacheMode cacheMode, final RequestCallBack requestCallBack) {
        LogUtil.info("post", "请求接口"+url);
        OkGo.<String>post(url).tag(tag).cacheKey(cacheKey).cacheMode(cacheMode).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                requestCallBack.onsuccess(response.body());
                LogUtil.info("post", "-------------onSuccess:请求成功了");
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                requestCallBack.onfailed(response.body());
                LogUtil.info("post", "-------------onError:请求失败了");
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                LogUtil.info("post", "-------------onStart:请求开始了");

            }

            @Override
            public void onFinish() {
                super.onFinish();
                LogUtil.info("post", "-------------onFinish:请求结束了");
            }

            @Override
            public void onCacheSuccess(Response<String> response) {
                super.onCacheSuccess(response);
                LogUtil.info("post", "-------------onCacheSuccess:请求缓存成功了");
                requestCallBack.onsuccess(response.body());
            }

            @Override
            public void uploadProgress(Progress progress) {
                super.uploadProgress(progress);
                requestCallBack.onprogress(progress);
            }
        });
    }
    public void post(Object tag, String url, final RequestCallBack requestCallBack) {
        LogUtil.info("httpparamsrequest", "请求接口"+url);
        OkGo.<String>post(url).tag(tag).cacheMode(CacheMode.NO_CACHE).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                requestCallBack.onsuccess(response.body());
                LogUtil.info("httpparamsrequest", "-------------onsuccess:请求开始了");
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                requestCallBack.onfailed(response.body());
                LogUtil.info("httpparamsrequest", "-------------onError:请求失败了");
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                LogUtil.info("httpparamsrequest", "-------------onStart:请求开始了");
            }

            @Override
            public void onFinish() {
                super.onFinish();
                LogUtil.info("httpparamsrequest", "-------------onFinish:请求结束了");
            }

            @Override
            public void uploadProgress(Progress progress) {
                super.uploadProgress(progress);
                requestCallBack.onprogress(progress);
            }
        });
    }
    /**
     * 上传string
     * @param tag
     * @param string
     * @param url
     * @param cacheKey
     * @param requestCallBack
     */
    public void postString(Object tag, String string, String url, String cacheKey, CacheMode cacheMode, final RequestCallBack requestCallBack) {
        LogUtil.info("httpparamsrequest", "请求接口"+url);
        OkGo.<String>post(url).cacheKey(cacheKey).cacheMode(cacheMode)
                .upString(string).tag(tag).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                requestCallBack.onsuccess(response.body());
                LogUtil.info("httpparamsrequest", "-------------onsuccess:请求开始了");
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                requestCallBack.onfailed(response.body());
                LogUtil.info("httpparamsrequest", "-------------onfailed:请求开始了");
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                LogUtil.info("httpparamsrequest", "-------------onStart:请求开始了");
            }

            @Override
            public void onFinish() {
                super.onFinish();
                LogUtil.info("httpparamsrequest", "-------------onFinish:请求开始了");
            }

            @Override
            public void onCacheSuccess(Response<String> response) {
                super.onCacheSuccess(response);
                requestCallBack.onsuccess(response.body());
            }
        });
    }
    public void postString(Object tag, String string, String url, RequestCallBack requestCallBack) {
        LogUtil.info("httpparamsrequest", "请求接口"+url);
        OkGo.<String>post(url).upString(string).tag(tag).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LogUtil.info("httpparamsrequest", "-------------onSuccess:请求开始了");
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                LogUtil.info("httpparamsrequest", "-------------onError:请求开始了");
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                LogUtil.info("httpparamsrequest", "-------------onStart:请求开始了");
            }

            @Override
            public void onFinish() {
                super.onFinish();
                LogUtil.info("httpparamsrequest", "-------------onFinish:请求开始了");
            }
        });
    }


    /**
     * post提交键值对组
     * @param tag
     * @param url
     * @param httpParams
     * @param cacheKey
     * @param requestCallback
     */
    public void postMap(Object tag, String url, HttpParams httpParams, String cacheKey, CacheMode cacheMode, final RequestCallBack requestCallback) {
        LogUtil.info("httpparamsrequest", "请求接口"+url);
        OkGo.<String>post(url).params(httpParams).tag(tag).cacheKey(cacheKey).cacheMode(cacheMode)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        requestCallback.onsuccess(response.body());
                        LogUtil.info("httpparamsrequest", "-------------onsuccess:请求成功了");
                        LogUtil.info("httpparamsrequest", response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        requestCallback.onfailed(response.body());
                        LogUtil.info("httpparamsrequest", "-------------onerror:请求失败了");
                    }

                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        super.onStart(request);
                        LogUtil.info("httpparamsrequest", "-------------onstart:请求开始了");
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        LogUtil.info("httpparamsrequest", "-------------onfinish:请求结束了");
                    }

                    @Override
                    public void onCacheSuccess(Response<String> response) {
                        super.onCacheSuccess(response);
                        requestCallback.onsuccess(response.body());
                    }
                });
    }
    public void postMap(Object tag, String url, HttpParams httpParams,final RequestCallBack requestCallback) {
        LogUtil.info("httpparamsrequest", "请求接口"+url);
        OkGo.<String>post(url).params(httpParams).cacheMode(CacheMode.NO_CACHE).tag(tag).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                requestCallback.onsuccess(response.body());
                LogUtil.info("httpparamsrequest", "-------------onsuccess:请求成功了" + "\n" + "json:"+ response.body());
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                requestCallback.onfailed(response.body());
                LogUtil.info("httpparamsrequest", "-------------onerror:请求失败了");
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                LogUtil.info("httpparamsrequest", "-------------onstart:请求开始了");
            }

            @Override
            public void onFinish() {
                super.onFinish();
                LogUtil.info("httpparamsrequest", "-------------onfinish:请求结束了");
            }
        });
    }
    public void downLoad(Object tag, String url,String cacheKey, CacheMode cacheMode,final RequestCallBack requestCallback) {
        LogUtil.info("downLoadrequest", "请求接口"+url);
        OkGo.<File>get(url).tag(tag).cacheKey(cacheKey).cacheMode(cacheMode).execute(new FileCallback() {

            @Override
            public void onSuccess(Response<File> response) {
                LogUtil.info("httpparamsrequest", response.body().getAbsolutePath());
                requestCallback.onDownloadSuccess(response.body());
            }

            @Override
            public void onError(Response<File> response) {
                super.onError(response);
                requestCallback.onfailed("下载失败");
            }

            @Override
            public void downloadProgress(Progress progress) {
                super.downloadProgress(progress);
                LogUtil.info("下载中", progress.fraction+"");
                requestCallback.onprogress(progress);
            }
        });
    }
    public void downLoad(Object tag, String url,final RequestCallBack requestCallback) {
        LogUtil.info("downLoadrequest", "请求接口"+url);
        OkGo.<File>get(url).tag(tag).cacheMode(CacheMode.NO_CACHE).execute(new FileCallback() {

            @Override
            public void onSuccess(Response<File> response) {
                LogUtil.info("httpparamsrequest", response.body().getAbsolutePath());
                requestCallback.onDownloadSuccess(response.body());
            }

            @Override
            public void onError(Response<File> response) {
                super.onError(response);
                requestCallback.onfailed("下载失败"+response.getException());
            }

            @Override
            public void downloadProgress(Progress progress) {
                super.downloadProgress(progress);
                LogUtil.info("下载中", progress.fraction+"");
                requestCallback.onprogress(progress);
            }
        });
    }
}
