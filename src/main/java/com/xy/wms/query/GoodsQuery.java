package com.xy.wms.query;

import com.xy.wms.base.BaseQuery;

public class GoodsQuery extends BaseQuery {
    private String goodsName;
    private Integer goodsTypeId;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(Integer goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }
}
