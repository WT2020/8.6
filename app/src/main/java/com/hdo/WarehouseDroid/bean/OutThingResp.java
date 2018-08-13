package com.hdo.WarehouseDroid.bean;

import java.io.Serializable;

/**
 * description 出库物品响应类
 * author 张建银
 * version 1.0
 * Created on 2017/12/15.
 */

public class OutThingResp implements Serializable {

    private String code;//响应码
    private Row<OutThing> data;//物品数据

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Row<OutThing> getData() {
        return data;
    }

    public void setData(Row<OutThing> data) {
        this.data = data;
    }
}
