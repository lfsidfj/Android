package com.spiderman.example.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {
    public static void setValue(Context context, String key, String value){
        SharedPreferences sharedPreferences=context.getSharedPreferences("account", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //使用editor保存数据
        editor.putString(key, value);
        //注意一定要提交数据，此步骤容易忘记
        editor.commit();
    }
    public static String getValue(Context context, String key){
        SharedPreferences sharedPreferences=context.getSharedPreferences("account", context.MODE_PRIVATE);
        //使用SharedPreferences查询数据
        String result = sharedPreferences.getString(key, null);
        return result;
    }
    public static void removeValue(Context context, String key){
        SharedPreferences sharedPreferences=context.getSharedPreferences("account", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //使用editor删除数据
        editor.remove(key);
        //一定要提交，该步骤非常容易忽略
        editor.commit();
    }
}
