package com.imooc.pojo.bo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 用于创建购物车的BO对象
 */
public class ShopcartBO {

    @ApiModelProperty(value = "商品id", name="itemId", example = "cake-1001", required = true)
    private String itemId;
    @ApiModelProperty(value = "商品图片", name="itemImgUrl", example = "food.jpg", required = false)
    private String itemImgUrl;
    @ApiModelProperty(value = "商品名", name="itemName", example = "猪肉脯", required = false)
    private String itemName;
    @ApiModelProperty(value = "规格id", name="specId", example = "001", required = false)
    private String specId;
    @ApiModelProperty(value = "规格名", name="specName", example = "原味", required = false)
    private String specName;
    @ApiModelProperty(value = "购买数量", name="buyCounts", example = "10", required = false)
    private Integer buyCounts;
    @ApiModelProperty(value = "优惠价", name="priceDiscount", example = "88", required = false)
    private String priceDiscount;
    @ApiModelProperty(value = "售价", name="priceNormal", example = "100", required = false)
    private String priceNormal;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemImgUrl() {
        return itemImgUrl;
    }

    public void setItemImgUrl(String itemImgUrl) {
        this.itemImgUrl = itemImgUrl;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Integer getBuyCounts() {
        return buyCounts;
    }

    public void setBuyCounts(Integer buyCounts) {
        this.buyCounts = buyCounts;
    }

    public String getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(String priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    public String getPriceNormal() {
        return priceNormal;
    }

    public void setPriceNormal(String priceNormal) {
        this.priceNormal = priceNormal;
    }

    @Override
    public String toString() {
        return "ShopcartBO{" +
                "itemId='" + itemId + '\'' +
                ", itemImgUrl='" + itemImgUrl + '\'' +
                ", itemName='" + itemName + '\'' +
                ", specId='" + specId + '\'' +
                ", specName='" + specName + '\'' +
                ", buyCounts=" + buyCounts +
                ", priceDiscount='" + priceDiscount + '\'' +
                ", priceNormal='" + priceNormal + '\'' +
                '}';
    }
}
