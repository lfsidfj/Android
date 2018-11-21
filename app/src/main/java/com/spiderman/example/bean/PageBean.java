package com.spiderman.example.bean;

/**
 * Created by HP on 2018/6/19.
 */

public class PageBean implements BaseBean {
    protected int pages;//当前页
    protected int pageSize;//一页条数
    protected int currentPage;//总页数

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
