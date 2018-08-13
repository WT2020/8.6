package com.hdo.WarehouseDroid.bean;

import java.io.Serializable;

/**
 * description 出入库申请列表获取
 * author 张建银
 * version 1.0
 * Created on 2017/12/15.
 */

public class InOutListResp implements Serializable {

    private String code;//响应码
    private Row<InOutList> data;//申请数据

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Row<InOutList> getData() {
        return data;
    }

    public void setData(Row<InOutList> data) {
        this.data = data;
    }
}
