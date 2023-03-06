package com.xy.wms.query;

import com.xy.wms.base.BaseQuery;
import org.apache.commons.lang3.StringUtils;

public class OutWarehouseQuery extends BaseQuery {

    public String goodsName;//物品名称
    public String customersName;//顾客名称
    public Integer state; //出库状态  0失败  1 成功


    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getCustomersName() {
        return customersName;
    }

    public void setCustomersName(String customersName) {
        this.customersName = customersName;
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
        if (StringUtils.isNotBlank(customersName)){
            key.append(":customersName:"+customersName);
        }
        if (state!=null){
            key.append(":state:"+state);
        }

        return key;
    }
}
