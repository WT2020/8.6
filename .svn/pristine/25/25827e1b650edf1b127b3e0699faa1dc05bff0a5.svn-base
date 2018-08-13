package com.hdo.WarehouseDroid.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.hdo.WarehouseDroid.bean.Department;
import com.hdo.WarehouseDroid.bean.User;

/**
 * @author 张建银
 * @version 1.0
 * @description 存储工具类
 *  modified by 吴小雪 on 2017/12/14 添加User
 *
 */
public class SpUtils {

    /**
     * 存储string
     *
     * @param ctx   context对象
     * @param key   需要存储的key
     * @param value 需要存储的key
     */
    public static void putString(Context ctx, String key, String value) {
        SharedPreferences sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit().putString(key, value).apply();
    }
    public static void putCode(Context ctx, String key, String value) {
        SharedPreferences sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit().putString(key, value).apply();
    }

    /**
     * 取出string
     *
     * @param ctx      context 对象
     * @param key      需要取出的key
     * @param defValue 如果没有取到默认的值
     * @return 取到的值
     */
    public static String getString(Context ctx, String key, String defValue) {
        SharedPreferences sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    /**
     * 存储int
     *
     * @param ctx   context对象
     * @param key   需要存储的key
     * @param value 需要存储的key
     */
    public static void putInt(Context ctx, String key, int value) {
        SharedPreferences sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).apply();
    }

    /**
     * 取出int
     *
     * @param ctx      context 对象
     * @param key      需要取出的key
     * @param defValue 如果没有取到默认的值
     * @return 取到的值
     */
    public static int getInt(Context ctx, String key, int defValue) {
        SharedPreferences sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    /**
     * 存储long
     *
     * @param ctx   context对象
     * @param key   需要存储的key
     * @param value 需要存储的key
     */
    public static void putLong(Context ctx, String key, long value) {
        SharedPreferences sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit().putLong(key, value).apply();
    }

    /**
     * 取出int
     *
     * @param ctx      context 对象
     * @param key      需要取出的key
     * @param defValue 如果没有取到默认的值
     * @return 取到的值
     */
    public static long getLong(Context ctx, String key, long defValue) {
        SharedPreferences sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getLong(key, defValue);
    }

    /**
     * 存储boolean
     *
     * @param ctx   context对象
     * @param key   需要存储的key
     * @param value 需要存储的key
     */
    public static void putBoolean(Context ctx, String key, boolean value) {
        SharedPreferences sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).apply();
    }

    /**
     * 取出boolean
     *
     * @param ctx      context 对象
     * @param key      需要取出的key
     * @param defValue 如果没有取到默认的值
     * @return 取到的值
     */
    public static boolean getBoolean(Context ctx, String key, boolean defValue) {
        SharedPreferences sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    /**
     * 存用户实体类到本地
     * @param context 上下文对象
     * @param user 用户实体类
     */
    public static void putUser(Context context,User user){
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        if(user!=null){
            SharedPreferences.Editor ed=sp.edit();
            ed.putString("identity",user.getIdentity());
            ed.putString("password",user.getPassword());
            ed.putString("number",user.getNumber());
            ed.putString("name",user.getName());
            ed.putString("phone",user.getPhone());
            ed.putString("token",user.getToken());
            ed.putString("userType",user.getUserType());
            if (!user.getUserType().equals("admin")){
                ed.putInt("customerId",user.getId());
            }
            if(user.getDepartment() == null){
                ed.putString("departmentName", "");
            }else {
                ed.putString("departmentName", user.getDepartment().getName());
            }
            ed.commit();
        }
    }

    /**
     * 获取用户的实体类
     * @param context   上下文对象
     * @return 用户实体类
     */
    public static User getUser(Context context){
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        User user=new User();
        user.setName(sp.getString("name",""));
        user.setNumber(sp.getString("number",""));
        user.setPassword(sp.getString("password",""));
        user.setPhone(sp.getString("phone",""));
        user.setToken(sp.getString("token",""));
        user.setIdentity(sp.getString("identity",""));
        user.setId(sp.getInt("customerId",0));
        user.setUserType(sp.getString("userType","customer"));
        Department department = new Department();
        department.setName(sp.getString("departmentName",""));
        user.setDepartment(department);
        return user;
    }
}
