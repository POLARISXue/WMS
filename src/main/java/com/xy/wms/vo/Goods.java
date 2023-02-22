package com.xy.wms.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Goods {
    private Integer id;

    private String goodsName;

    private Integer goodsTypeId;

    private String typeName;

    private String goodsType;

    private Double goodsCostPrice;

    private Double goodsSalePrice;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

    private Integer isValid;

    public String getGoodsType() {
        return goodsType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Integer getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(Integer goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public Double getGoodsCostPrice() {
        return goodsCostPrice;
    }

    public void setGoodsCostPrice(Double goodsCostPrice) {
        this.goodsCostPrice = goodsCostPrice;
    }

    public Double getGoodsSalePrice() {
        return goodsSalePrice;
    }

    public void setGoodsSalePrice(Double goodsSalePrice) {
        this.goodsSalePrice = goodsSalePrice;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
}