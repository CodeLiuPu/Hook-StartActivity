package com.update.hook.helper;

import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;

import com.update.hook.StubActivity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author : liupu
 * date   : 2019/6/25
 * desc   :
 */
public class MockClass1 implements InvocationHandler {

    private static final String TAG = "MockClass1";

    Object mBase;

    public MockClass1(Object base) {
        this.mBase = base;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        Log.e(TAG, "hook method " + methodName);

        if ("startActivity".equals(methodName)) {
            // 只拦截这个方法
            // 替换参数, 任你所为;甚至替换原始Activity启动别的Activity偷梁换柱
            // 找到参数里面的第一个Intent 对象
            Intent raw;
            int index =0;
            for (int i =0;i<args.length;i++){
                // 将参数中第一个 Intent 对象 保存起来
                if (args[i] instanceof Intent){
                    index = i;
                    break;
                }
            }

            raw = (Intent) args[index];

            // 开始封装要启动的Activity信息
            Intent newIntent = new Intent();

            // 替身Activity的包名,也就是我们自己的包名
            String stubPackage = raw.getComponent().getPackageName();

            // 将启动的 Activity 临时替换为 StubActivity
            ComponentName componentName = new ComponentName(stubPackage, StubActivity.class.getName());
            newIntent.setComponent(componentName);

            // 将原始要启动的 Activity信息 先保存起来
            newIntent.putExtra(AMSHookHelper.EXTRA_TARGET_INTENT,raw);

            // 替换掉Intent 达到欺骗 AMS 的目的
            args[index] = newIntent;

            Log.d(TAG, "hook startActivity() success");
        }
        return method.invoke(mBase,args);
    }
}
