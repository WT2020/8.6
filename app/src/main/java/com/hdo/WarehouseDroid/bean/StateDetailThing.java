package com.hdo.WarehouseDroid.bean;

import java.io.Serializable;

/**
 * description 库存物品详细数据类
 * author 张建银
 * version 1.0
 * Created on 2017/12/18.
 */

public class StateDetailThing extends Thing implements Serializable {

    private Warehouse warehouse;//仓库位置
    private String batch;//批次
    private int batchNum;//批次数量
    private String isPallet;//是否是栈板(0:不是栈板;1:是栈板)
    private String state;//状态值 1:正常 2:本批次销售完毕 3：已冻结 4：已挂起

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public int getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(int batchNum) {
        this.batchNum = batchNum;
    }

    public String getIsPallet() {
        return isPallet;
    }

    public void setIsPallet(String isPallet) {
        this.isPallet = isPallet;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
