package com.hdo.WarehouseDroid.bean;

import java.io.Serializable;

/**
 * description 仓库物品类
 * author 张建银
 * version 1.0
 * Created on 2017/11/22.
 * modified on 2017/12/21 修改为继承于Thing类
 */

public class WarehouseThing extends Thing implements Serializable {

    private String goodsId;//物品RFID
    private int addAmount;//添加数量
    private int removeAmount;//移除数量
    private int changedNum;//操作后数量
    private String remark;//备注
    private String warehouseId;//货位架
    private Warehouse warehouse;//仓库

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public int getAddAmount() {
        return addAmount;
    }

    public void setAddAmount(int addAmount) {
        this.addAmount = addAmount;
    }

    public int getRemoveAmount() {
        return removeAmount;
    }

    public void setRemoveAmount(int removeAmount) {
        this.removeAmount = removeAmount;
    }

    public int getChangedNum() {
        return changedNum;
    }

    public void setChangedNum(int changedNum) {
        this.changedNum = changedNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public String toString() {
        return "WarehouseThing{" +
                "goodsId='" + goodsId + '\'' +
                ", addAmount=" + addAmount +
                ", removeAmount=" + removeAmount +
                ", changedNum=" + changedNum +
                ", remark='" + remark + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                ", warehouse=" + warehouse +
                '}';
    }
}
