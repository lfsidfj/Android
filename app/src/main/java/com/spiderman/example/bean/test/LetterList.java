package com.spiderman.example.bean.test;

import com.spiderman.example.bean.PageBean;

import java.util.List;

/**
 * Created by HP on 2018/7/1.
 */

public class LetterList extends PageBean {
    public List<Letter> getBig_brand_list() {
        return big_brand_list;
    }

    public void setBig_brand_list(List<Letter> big_brand_list) {
        this.big_brand_list = big_brand_list;
    }

    public List<Letter> big_brand_list;
    public class Letter{
        private int brand_id;
        private String brand_logo;
        private String brand_name;
        private String first_letter;

        public int getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(int brand_id) {
            this.brand_id = brand_id;
        }

        public String getBrand_logo() {
            return brand_logo;
        }

        public void setBrand_logo(String brand_logo) {
            this.brand_logo = brand_logo;
        }

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public String getFirst_letter() {
            return first_letter;
        }

        public void setFirst_letter(String first_letter) {
            this.first_letter = first_letter;
        }
    }
}
