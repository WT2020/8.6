package com.hdo.WarehouseDroid.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * description 仓库封装类
 * author 张建银
 * version 1.0
 * created 2018/2/2
 */

public class WarehouseInfo implements Serializable {

    private int warehouseId;//仓库Id
    private String warehouseName;//仓库名称
    private ArrayList<WarehouseZone> warehouseZoneVos;//下属区域

    public class WarehouseZone implements Serializable {

        private String zoneName;//区域名称
        private ArrayList<WarehouseShelf> warehouseShelfVos;//下属货位架

        public class WarehouseShelf implements Serializable {

            private int warehouseId;//货位架Id
            private String warehouseShelf;//货物架名称

            public int getWarehouseId() {
                return warehouseId;
            }

            public void setWarehouseId(int warehouseId) {
                this.warehouseId = warehouseId;
            }

            public String getWarehouseShelf() {
                return warehouseShelf;
            }

            public void setWarehouseShelf(String warehouseShelf) {
                this.warehouseShelf = warehouseShelf;
            }
        }

        public String getZoneName() {
            return zoneName;
        }

        public void setZoneName(String zoneName) {
            this.zoneName = zoneName;
        }

        public ArrayList<WarehouseShelf> getWarehouseShelfVos() {
            return warehouseShelfVos;
        }

        public void setWarehouseShelfVos(ArrayList<WarehouseShelf> warehouseShelfVos) {
            this.warehouseShelfVos = warehouseShelfVos;
        }
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public ArrayList<WarehouseZone> getWarehouseZoneVos() {
        return warehouseZoneVos;
    }

    public void setWarehouseZoneVos(ArrayList<WarehouseZone> warehouseZoneVos) {
        this.warehouseZoneVos = warehouseZoneVos;
    }
}
