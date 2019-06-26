package com.update.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.update.hook.helper.EvilInstrumentation;
import com.update.hook.helper.RefInvoke;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Instrumentation mInstrumentation = (Instrumentation) RefInvoke.getFieldObject(Activity.class,this,"mInstrumentation");
        Instrumentation evilInstrumentation = new EvilInstrumentation(mInstrumentation);
        RefInvoke.setFieldObject(Activity.class, this, "mInstrumentation", evilInstrumentation);

        findViewById(R.id.tv_hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
