package com.app.qrcodedemo;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.app.qrcodedemo.v1.ActivityScanerCode;
import com.app.qrcodedemo.v1.tools.PermissionsTool;
import com.app.qrcodedemo.v2.SecondActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button button1,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.btn1);
        button2 = findViewById(R.id.btn2);
        PermissionsTool.with(this).addPermission(Manifest.permission.CAMERA).initPermission();
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                startActivity(new Intent(this,ActivityScanerCode.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(this,SecondActivity.class));
                break;
        }
    }
}
