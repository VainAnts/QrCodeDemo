package com.app.qrcodedemo.v1.tools;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * 常用工具类
 */
public class Tools {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Tools.context = context.getApplicationContext();
    }

    /**
     * 在某种获取不到 Context 的情况下，即可以使用才方法获取 Context
     * <p>
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("请先调用init()方法");
    }
}
