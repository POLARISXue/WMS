package com.xy.wms.query;

import com.xy.wms.base.BaseQuery;
import org.apache.commons.lang3.StringUtils;

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

    @Override
    public StringBuffer getRedisKey() {
        StringBuffer key = new StringBuffer(super.getRedisKey());
        if (StringUtils.isNotBlank(goodsName)){
            key.append(":goodsName:"+goodsName);
        }
        if (StringUtils.isNotBlank(goodsType)){
            key.append(":goodsType:"+goodsType);
        }
        return key;
    }
}
