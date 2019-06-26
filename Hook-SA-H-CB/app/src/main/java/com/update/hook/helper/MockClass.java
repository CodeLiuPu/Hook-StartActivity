package com.update.hook.helper;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * @author : liupu
 * date    : 2019/6/24
 * desc    :
 */
public class MockClass implements Handler.Callback {

    Handler mBase;

    public MockClass(Handler base) {
        this.mBase = base;
    }

    @Override
    public boolean handleMessage(Message msg) {

        switch (msg.what) {
            // ActivityThread里面 "LAUNCH_ACTIVITY" 这个字段的值是100
            // 本来使用反射的方式获取最好, 这里为了简便直接使用硬编码
            case 100:
                handleLaunchActivity(msg);
                break;
        }
        mBase.handleMessage(msg);
        return true;
    }


    private void handleLaunchActivity(Message msg) {
        // 这里简单起见,直接取出TargetActivity;
        Object obj = msg.obj;
        Log.e("hook", obj.toString());
    }
}
