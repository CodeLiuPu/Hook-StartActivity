package com.update.hook.helper;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

/**
 * @author : liupu
 * date   : 2019/6/24
 * desc   :
 */
public class EvilInstrumentation extends Instrumentation {
    private static final String TAG = "EvilInstrumentation";

    // ActivityThread中原始的对象, 保存起来
    Instrumentation mBase;

    public EvilInstrumentation(Instrumentation base) {
        this.mBase = base;
    }

    public ActivityResult execStartActivity(
            Context who, IBinder contextThread, IBinder token, Activity target,
            Intent intent, int requestCode, Bundle options) {
        Log.e(TAG, "Hook startActivity Instrumentation");

        Class[] c = {Context.class, IBinder.class, IBinder.class, Activity.class,
                Intent.class, int.class, Bundle.class};
        Object[] v = {who, contextThread, token, target,
                intent, requestCode, options};

        return (ActivityResult) RefInvoke.invokeInstanceMethod(mBase, "execStartActivity", c, v);

    }
}
