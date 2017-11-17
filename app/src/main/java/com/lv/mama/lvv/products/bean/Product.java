package com.lv.mama.lvv.products.bean;

/**
 * Created by 卢总 on 2017/11/17.
 */

public class Product {
    private String pscid;

    public Product(String pscid) {
        this.pscid = pscid;
    }

    public String getPscid() {
        return pscid;
    }

    public void setPscid(String pscid) {
        this.pscid = pscid;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
