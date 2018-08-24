package com.app.qrcodedemo.v1.interfaces;


import com.google.zxing.Result;


public interface OnScanerListener {
    void onSuccess(String type, Result result);

    void onFail(String type, String message);
}
