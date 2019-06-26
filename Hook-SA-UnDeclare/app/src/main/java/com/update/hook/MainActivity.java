package com.update.hook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.update.hook.helper.AMSHookHelper;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);

        try {
            AMSHookHelper.hookAMN();
            AMSHookHelper.hookActivityThread();
        } catch (Throwable throwable) {
            throw new RuntimeException("hook failed", throwable);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button button = new Button(this);
        button.setText("启动TargetActivity");
        setContentView(button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 启动目标Activity; 注意这个Activity是没有在AndroidManifest.xml中显式声明的
                // 但是调用者并不需要知道, 就像一个普通的Activity一样
                startActivity(new Intent(MainActivity.this, TargetActivity.class));
            }
        });
    }
}
