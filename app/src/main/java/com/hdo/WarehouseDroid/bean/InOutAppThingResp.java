package com.hdo.WarehouseDroid.bean;

import java.io.Serializable;

/**
 * description 出入库申请物品列表响应类
 * author 张建银
 * version 1.0
 * Created on 2017/12/18.
 */

public class InOutAppThingResp implements Serializable {

    private String code;//响应码
    private Row<WarehouseThing> data;//物品数据

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Row<WarehouseThing> getData() {
        return data;
    }

    public void setData(Row<WarehouseThing> data) {
        this.data = data;
    }
}
