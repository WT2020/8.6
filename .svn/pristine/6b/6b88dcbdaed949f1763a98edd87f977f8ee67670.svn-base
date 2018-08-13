package com.hdo.WarehouseDroid.bean;

import java.io.Serializable;

/**
 * 项目名称：WarehouseDroid
 * 创建人:吴小雪
 * 创建时间:2017/12/14  16:36
 */

public class Department implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;//主键
    private String name;// 部门名称
    private String code;// 部门代码
    private int level;// 部门等级（0：无父类；1：有父类）
    private Department parent;// 所属部门
    private String remark;// 备注

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Department getParent() {
        return parent;
    }

    public void setParent(Department parent) {
        this.parent = parent;
    }



    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Department() {
        super();
    }


}
