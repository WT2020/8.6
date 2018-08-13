package com.hdo.WarehouseDroid.bean;

import java.io.Serializable;

/**
 * description 计费类型
 * author 张建银
 * version 1.0
 * created 2018/2/2
 */

public class Cost implements Serializable {

    private String code;//缩写代码
    private String createDate;//时间
    private int id;//ID
    private String name;//计费名称
    private double price;//价格
    private String remark;//备注说明
    private String spec;//计费规格
    private String updateDate;//更新时间

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
