package com.spiderman.example.bean.test;

import com.spiderman.example.bean.PageBean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/21.
 */

public class GoodsList extends PageBean {

    private List<Goods> goodsList;

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public class Goods{
        private String goods_name;
        private double goods_price;
        private double goods_current_price;
        private String goods_main_photo;
        private String goods_main_photo1;

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public double getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(double goods_price) {
            this.goods_price = goods_price;
        }

        public double getGoods_current_price() {
            return goods_current_price;
        }

        public void setGoods_current_price(double goods_current_price) {
            this.goods_current_price = goods_current_price;
        }

        public String getGoods_main_photo() {
            return goods_main_photo;
        }

        public void setGoods_main_photo(String goods_main_photo) {
            this.goods_main_photo = goods_main_photo;
        }

        public String getGoods_main_photo1() {
            return goods_main_photo1;
        }

        public void setGoods_main_photo1(String goods_main_photo1) {
            this.goods_main_photo1 = goods_main_photo1;
        }
    }
}
