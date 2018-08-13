package com.hdo.WarehouseDroid.bean;

import java.io.Serializable;

/**
 * description 出入库信息获取响应
 * author 张建银
 * version 1.0
 * created 2018/2/2
 */

public class InOutInfoResp implements Serializable {

    private String code;//响应码
    private InOutInfo data;//出入库准备数据

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public InOutInfo getData() {
        return data;
    }

    public void setData(InOutInfo data) {
        this.data = data;
    }
}
