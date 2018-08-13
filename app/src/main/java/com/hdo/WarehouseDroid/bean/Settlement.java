package com.hdo.WarehouseDroid.bean;

import java.io.Serializable;

/**
 * description 账单记录实体
 * author 张建银
 * version 1.0
 * created 2018/1/23
 */

public class Settlement implements Serializable {

    //主键
    private Integer id;
    //结算的客户
    private User customer;
    //结算方式  1：平方    2：货架   3：件数
    private String settlementType;
    //存储的天数
    private Integer days;
    //应付金额
    private double apMoney;
    //已付金额
    private double alreadyApMoney;
    //费用状态 0：未结算 1.部分结算 2.付清
    private String status;
    //每月生成记录的年月
    private String yearMonth;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public String getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(String settlementType) {
        this.settlementType = settlementType;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public double getApMoney() {
        return apMoney;
    }

    public void setApMoney(double apMoney) {
        this.apMoney = apMoney;
    }

    public double getAlreadyApMoney() {
        return alreadyApMoney;
    }

    public void setAlreadyApMoney(double alreadyApMoney) {
        this.alreadyApMoney = alreadyApMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }
}
