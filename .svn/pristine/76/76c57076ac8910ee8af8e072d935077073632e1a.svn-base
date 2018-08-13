package com.hdo.WarehouseDroid.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * description 入/移/出库申请类
 * author 张建银
 * version 1.0
 * Created on 2017/11/22.
 * modified on 2017/12/15 删除仓库house、时间time、状态state字段,修改入库负责人、来源、去向、物品单，备注，单号字段名
 */

public class HouseChange implements Serializable{
    //单号
    private String number;
    //入库负责人
    private String applyInUser;
    //出库负责人
    private String applyOutUser;
    //备注
    private String remark;
    //物品单
    private ArrayList<WarehouseThing> goodsData;
    //物品来源
    private String whereFrom;
    //物品去向
    private String whereGo;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getApplyInUser() {
        return applyInUser;
    }

    public void setApplyInUser(String applyInUser) {
        this.applyInUser = applyInUser;
    }

    public String getApplyOutUser() {
        return applyOutUser;
    }

    public void setApplyOutUser(String applyOutUser) {
        this.applyOutUser = applyOutUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ArrayList<WarehouseThing> getGoodsData() {
        return goodsData;
    }

    public void setGoodsData(ArrayList<WarehouseThing> goodsData) {
        this.goodsData = goodsData;
    }

    public String getWhereFrom() {
        return whereFrom;
    }

    public void setWhereFrom(String whereFrom) {
        this.whereFrom = whereFrom;
    }

    public String getWhereGo() {
        return whereGo;
    }

    public void setWhereGo(String whereGo) {
        this.whereGo = whereGo;
    }
}
