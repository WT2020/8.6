package com.hdo.WarehouseDroid.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * description 出入库准备数据
 * author 张建银
 * version 1.0
 * created 2018/2/2
 */

public class InOutInfo implements Serializable {

    private String number;//出入库单号
    private ArrayList<Customer> customers;//出入库客户
    private ArrayList<WarehouseInfo> warehouseInfo;//仓库

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public ArrayList<WarehouseInfo> getWarehouseInfo() {
        return warehouseInfo;
    }

    public void setWarehouseInfo(ArrayList<WarehouseInfo> warehouseInfo) {
        this.warehouseInfo = warehouseInfo;
    }
}
