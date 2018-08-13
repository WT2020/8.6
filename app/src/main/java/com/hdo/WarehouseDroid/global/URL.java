package com.hdo.WarehouseDroid.global;

/**
 * description URL常量类
 * author 张建银
 * version 1.0
 * created 2017/9/6
 */

public class URL {
    //服务器位置
    //private static String baseUrl = "http://192.168.0.67:8080/Civi_Warehouse/m/";
//    private static String baseUrl = "http://122.112.229.247:8080/Civi_Warehouse/m/";
//    private static String baseUrl = "http://192.168.0.118:8080/Civi_Warehouse/m/";
//    private static String baseUrl = "http://192.168.0.118:80/Civi_Warehouse/m/";//王灿
    private static String baseUrl = "http://192.168.0.111:8080/Civi_Warehouse/m/";//我的
    //登录URL
    public static String loginUrl = baseUrl + "user/login";
    //获取入库主表单号
    public static String applyIdUrl = baseUrl+"goodsIn/getNumber";
    //入库表申请
    public static String applyUrl = baseUrl+"goodsIn/apply";
    //获取入库物品列表
    public static String applyInListUrl = baseUrl + "goods/getInList";
    //获取出库物品列表
    public static String applyOutListUrl = baseUrl + "goods/getOutList";
    //入库表审核
    public static String inExamineUrl = baseUrl + "goodsIn/examine";
    //入库主列表
    public static String inMainListUrl = baseUrl + "goodsIn/getList";
    //入库详情列表
    public static String inDetailListUrl = baseUrl  + "goodsIn/getDetailList";
    //获取出库主编号
    public static String outIdUrl = baseUrl + "goodsOut/getNumber";
    //出库表申请
    public static String outApplyUrl = baseUrl + "goodsOut/apply";
    //出库表审核
    public static String outExamineUrl = baseUrl + "goodsOut/examine";
    //出库主列表
    public static String outMainListUrl = baseUrl + "goodsOut/getList";
    //出库表详细表
    public static String outDetailListUrl = baseUrl + "goodsOut/getDetailList";
    //获取所有库存
    public static String allList = baseUrl + "stock/getList";
    //获取库存详情
    public static String allListDetail = baseUrl + "stock/getDetailList";
    //获取所有账单
    public static String settlementList = baseUrl + "settlement/getRecord";
    //获取个人资料URL
    public static String getPersonalInfoUrl = baseUrl + "";
    //修改个人资料URL
    public static String changePersonalInfoUrl = baseUrl + "";
    //修改密码URL
    public static String changePswUrl = baseUrl + "user/changePsw";
//    public static String changePswUrl = baseUrl + "user/AppPwd";
    //检查更新URL
    public static String checkUpdateUrl = baseUrl.substring(0,baseUrl.length()-2) + "files/appInfo.json";

}
