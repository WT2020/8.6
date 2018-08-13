package com.hdo.WarehouseDroid.bean;

import java.io.Serializable;

/**
 * description 仓库类
 * author 张建银
 * version 1.0
 * Created on 2017/12/15.
 */

public class Warehouse implements Serializable {

    private String name;//库位名称
    private String zone;//库房区位
    private String shelves;//货架

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getShelves() {
        return shelves;
    }

    public void setShelves(String shelves) {
        this.shelves = shelves;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "name='" + name + '\'' +
                ", zone='" + zone + '\'' +
                ", shelves='" + shelves + '\'' +
                '}';
    }
}
