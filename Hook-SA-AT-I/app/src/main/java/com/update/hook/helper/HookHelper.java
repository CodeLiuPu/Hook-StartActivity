package com.update.hook.helper;

import android.app.Instrumentation;

/**
 * @author : liupu
 * date   : 2019/6/25
 * desc   :
 */
public class HookHelper {
    public static void attachContext() throws Exception {
        // 先获取到当前的ActivityThread
        // 经测试 28 版本 为static方法 需要修改下获取逻辑 才能生效
//        Object currentActivityThread = RefInvoke.invokeInstanceMethod("android.app.ActivityThread", "currentActivityThread");
        Object currentActivityThread = RefInvoke.invokeStaticMethod("android.app.ActivityThread", "currentActivityThread");

        // 拿到原始的 mInstrumentation字段
        Instrumentation mInstrumentation = (Instrumentation) RefInvoke.getFieldObject(currentActivityThread, "mInstrumentation");

        // 创建代理对象
        Instrumentation evilInstrumentation = new EvilInstrumentation(mInstrumentation);

        // 替换为代理对象
        RefInvoke.setFieldObject(currentActivityThread, "mInstrumentation",evilInstrumentation);
    }

}
