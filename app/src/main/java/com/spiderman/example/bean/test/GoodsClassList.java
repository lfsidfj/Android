package com.spiderman.example.bean.test;

import com.spiderman.example.bean.PageBean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/25.
 */

public class GoodsClassList extends PageBean {
    List<GoodsClass> goodsClass;

    public List<GoodsClass> getGoodsClass() {
        return goodsClass;
    }

    public void setGoodsClass(List<GoodsClass> goodsClass) {
        this.goodsClass = goodsClass;
    }

    public class GoodsClass{
        private String className;
        private int id;

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
