package com.xy.wms.query;

import com.xy.wms.base.BaseQuery;
import org.apache.commons.lang3.StringUtils;

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

    @Override
    public StringBuffer getRedisKey() {
        StringBuffer key = new StringBuffer(super.getRedisKey());
        if (StringUtils.isNotBlank(goodsName)){
            key.append(":goodsName:"+goodsName);
        }
        if (StringUtils.isNotBlank(supplier)){
            key.append(":supplier:"+supplier);
        }
        if (state!=null){
            key.append(":state:"+state);
        }
        return key;
    }
}
