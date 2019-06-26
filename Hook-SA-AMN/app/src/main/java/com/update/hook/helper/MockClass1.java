package com.update.hook.helper;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author : liupu
 * date   : 2019/6/24
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
        if ("startActivity".equals(method.getName())) {
            Log.e(TAG, "hook method " + method.getName());
        }
        return method.invoke(mBase, args);
    }
}
