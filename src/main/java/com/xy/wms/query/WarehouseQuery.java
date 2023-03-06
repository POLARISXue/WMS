package com.xy.wms.query;

import com.xy.wms.base.BaseQuery;
import org.apache.commons.lang3.StringUtils;

public class WarehouseQuery extends BaseQuery {
    //物品的ID
    private Integer goodsId;
    //物品的名称
    private String goodsName;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    //状态
    private Integer devResult;

    public Integer getDevResult() {
        return devResult;
    }

    public void setDevResult(Integer devResult) {
        this.devResult = devResult;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public StringBuffer getRedisKey() {
        StringBuffer key = new StringBuffer(super.getRedisKey());
        if (goodsId!=null){
            key.append(":goodsId:"+goodsId);
        }
        if (StringUtils.isNotBlank(goodsName)){
            key.append(":goodsName:"+goodsName);
        }
        return key;
    }
}
