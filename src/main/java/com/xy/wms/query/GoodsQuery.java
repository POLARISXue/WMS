package com.xy.wms.query;

import com.xy.wms.base.BaseQuery;

public class GoodsQuery extends BaseQuery {
    private String goodsName;
    private String goodsType;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }
}
