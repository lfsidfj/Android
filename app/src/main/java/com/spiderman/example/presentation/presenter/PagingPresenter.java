package com.spiderman.example.presentation.presenter;

import com.spiderman.example.bean.PageBean;
import com.spiderman.example.config.HttpAddress;
import com.spiderman.example.ui.viewfeatures.HttpMvpView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HP on 2018/6/23.
 */

public class PagingPresenter extends CommonPresenter {

    private int currentPage;//当前页
    private int pageSize;//一页条数
    private int pages;//总页数

    private Map param=new HashMap();//接口参数
    private String dataType;//接口名称
    private String url="";//接口路径

    private Class<? extends PageBean> clazz;//用于JSON转化

    public PagingPresenter(HttpMvpView iView, Class<? extends PageBean> clazz, String url, String dataType) {
        super(iView);
        this.clazz=clazz;
        this.url= HttpAddress.mBaseUrl+url;
        this.dataType=dataType;
    }
    @Override
    public void setViewData(Object object, String dataType) {
        setPage((PageBean) object);
        super.setViewData(object, dataType);
    }
    /**
     * 记录分页数据
     * @param model
     */
    private void setPage(PageBean model){
        currentPage=model.getCurrentPage();
        pageSize=model.getPageSize();
        pages=model.getPages();
    }
    /**
     * 加载
     */
    private void loadData(Map param){
        super.request(url,param,dataType,clazz,currentPage==0?true:false);
    }
    /**
     * 刷新
     */
    public void refresh(){
        currentPage=0;
        param.put("currentPage",currentPage+1);
        loadData(param);
    }

    /**
     * 加载更多
     */
    public void loadMore(){
        if(currentPage < pages||currentPage==0){
            param.put("currentPage",currentPage+1);
            loadData(param);
        }
    }

    /**
     * 跳到某一页
     * @param currentPage
     * @param param
     */
    public void goToPage(int currentPage,Map param){
        if(param!=null){
            this.param=param;
        }
        this.param.put("currentPage",currentPage);
        loadData(this.param);
    }

    /**
     * 是否第一页
     * @return
     */
    public  boolean isFirstPage(){
        return currentPage<=1?true:false;
    }

    /**
     * 是否最后一页
     * @return
     */
    public boolean isLastPage(){
        return currentPage>=pages&&currentPage!=0?true:false;
    }
}
