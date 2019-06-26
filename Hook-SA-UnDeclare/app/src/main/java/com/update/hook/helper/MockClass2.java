package com.update.hook.helper;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

/**
 * @author : liupu
 * date   : 2019/6/25
 * desc   :
 */
public class MockClass2 implements Handler.Callback {
    Handler mBase;

    public MockClass2(Handler base) {
        this.mBase = base;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            // ActivityThread里面 "LAUNCH_ACTIVITY" 这个字段的值是100
            // 本来使用反射的方式获取最好, 这里为了简便直接使用硬编码
            // 阅读源码发现 该字段 在28以后去除掉了 所以只能兼容28版本之前的系统
            case 100:
                handleLaunchActivity(msg);
                break;
        }

        mBase.handleMessage(msg);
        return true;
    }

    private void handleLaunchActivity(Message msg) {
        // 这里简单起见,直接取出Activity 的 ActivityClientRecord
        // 查看老版本源码msg的obj 格式是 ActivityClientRecord
        Object obj = msg.obj;

        // 把替身恢复成真身
        Intent intent = (Intent) RefInvoke.getFieldObject(obj, "intent");

        // 下面逻辑是错误的
        // 查看老版本源码msg的obj 格式是 ActivityClientRecord
        // 所以并不能当做 Activity 来用
        // Intent intent = ((Activity)obj).getIntent();

        Intent targetIntent = intent.getParcelableExtra(AMSHookHelper.EXTRA_TARGET_INTENT);

        intent.setComponent(targetIntent.getComponent());
    }

}
