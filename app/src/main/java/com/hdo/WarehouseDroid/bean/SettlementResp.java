package com.hdo.WarehouseDroid.bean;

import java.io.Serializable;

/**
 * description 账单记录响应
 * author 张建银
 * version 1.0
 * created 2018/1/23
 */

public class SettlementResp implements Serializable {

    private String code;
    private Row<Settlement> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Row<Settlement> getData() {
        return data;
    }

    public void setData(Row<Settlement> data) {
        this.data = data;
    }
}
