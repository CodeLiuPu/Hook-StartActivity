package com.update.hook.helper;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * @author : liupu
 * date   : 2019/6/25
 * desc   :
 */
public class EvilInstrumentation extends Instrumentation {

    private static String TAG = "EvilInstrumentation";

    // ActivityThread中原始的对象, 保存起来
    Instrumentation mBase;

    public EvilInstrumentation(Instrumentation base) {
        this.mBase = base;
    }

    @Override
    public Activity newActivity(ClassLoader cl, String className,
                                Intent intent)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Log.e(TAG, "Hook newActivity");
        return mBase.newActivity(cl, className, intent);
    }

    @Override
    public void callActivityOnCreate(Activity activity, Bundle bundle) {
        Log.e(TAG, "Hook callActivityOnCreate");
        mBase.callActivityOnCreate(activity, bundle);
    }
}
