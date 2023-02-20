package com.xy.wms.query;

import com.xy.wms.base.BaseQuery;

public class IntoWarehouseQuery extends BaseQuery {

    private String goodsName;
    private String supplier;
    private Integer state;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
