/*
 * Copyright (C) 2018 Jenly Yu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.app.qrcodedemo.v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.qrcodedemo.R;
import com.app.qrcodedemo.v2.scaner.CaptureActivity;
import com.app.qrcodedemo.v2.scaner.Intents;
import com.app.qrcodedemo.v2.util.StatusBarUtils;


public class SecondActivity extends AppCompatActivity {

    public static final String KEY_TITLE = "key_title";
    public static final String KEY_IS_QR_CODE = "key_code";

    public static final int REQUEST_CODE = 0X01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(R.string.app_name);
        ImageView ivLeft = findViewById(R.id.ivLeft);
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
                //识别结果
                String result = data.getStringExtra(Intents.Scan.RESULT);
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            }
        }
    }


    /**
     * 扫码
     *
     * @param cls
     * @param title
     */
    private void startScan(Class<?> cls, String title) {
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeCustomAnimation(this, R.anim.in, R.anim.out);
        Intent intent = new Intent(this, cls);
        intent.putExtra(KEY_TITLE, title);
        ActivityCompat.startActivityForResult(this, intent, REQUEST_CODE, optionsCompat.toBundle());
    }

    /**
     * 生成二维码/条形码
     *
     * @param isQRCode
     */
    private void startCode(boolean isQRCode) {
        Intent intent = new Intent(this, CodeActivity.class);
        intent.putExtra(KEY_IS_QR_CODE, isQRCode);
        intent.putExtra(KEY_TITLE, isQRCode ? getString(R.string.qr_code) : getString(R.string.bar_code));
        startActivity(intent);
    }

    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                startScan(CaptureActivity.class, ((Button) v).getText().toString());
                break;
            case R.id.btn2:
                startScan(EasyCaptureActivity.class, ((Button) v).getText().toString());
                break;
            case R.id.btn3:
                startScan(CustomCaptureActivity.class, ((Button) v).getText().toString());
                break;
            case R.id.btn4:
                startCode(false);
                break;
            case R.id.btn5:
                startCode(true);
                break;
        }

    }
}
