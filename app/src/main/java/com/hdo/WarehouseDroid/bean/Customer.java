package com.hdo.WarehouseDroid.bean;

import java.io.Serializable;

/**
 * description 客户类
 * author 张建银
 * version 1.0
 * created 2018/2/2
 */

public class Customer extends User implements Serializable {

    private Cost costType;//计费类型
    private double rentArea;//租用面积

    public Cost getCostType() {
        return costType;
    }

    public void setCostType(Cost costType) {
        this.costType = costType;
    }

    public double getRentArea() {
        return rentArea;
    }

    public void setRentArea(double rentArea) {
        this.rentArea = rentArea;
    }
}
