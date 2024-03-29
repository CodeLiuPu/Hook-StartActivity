package com.update.hook.helper;

import android.os.Handler;

/**
 * @author : liupu
 * date    : 2019/6/24
 * desc    :
 */
public class HookHelper {
    public static void attachBaseContext() throws Exception {
        // 先获取到当前的ActivityThread对象
        Object currentActivityThread = RefInvoke.getStaticFieldObject("android.app.ActivityThread", "sCurrentActivityThread");

        // 由于ActivityThread一个进程只有一个,我们获取这个对象的mH
        Handler mH = (Handler) RefInvoke.getFieldObject(currentActivityThread, "mH");

        //把Handler的mCallback字段，替换为new MockClass2(mH)
        RefInvoke.setFieldObject(Handler.class, mH, "mCallback", new MockClass(mH));
    }

}
