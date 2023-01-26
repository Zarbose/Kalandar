package com.example.kalandar.utils;

import android.util.Log;

import org.chromium.net.CronetException;
import org.chromium.net.UrlRequest;
import org.chromium.net.UrlResponseInfo;

import java.nio.ByteBuffer;

public class RequestHttp extends UrlRequest.Callback {

    private static final String TAG = "Request";

    @Override
    public void onRedirectReceived(UrlRequest request, UrlResponseInfo responseInfo, String newLocationUrl) {
        Log.i(TAG, "onRedirectReceived method called.");
        request.followRedirect();
    }

    @Override
    public void onResponseStarted(UrlRequest request, UrlResponseInfo responseInfo) {
        Log.i(TAG, "onResponseStarted method called.");
        int httpStatusCode = responseInfo.getHttpStatusCode();
        if (httpStatusCode == 200) {
            ByteBuffer myBuffer = ByteBuffer.allocateDirect(102400);
            request.read(myBuffer);
        }
    }

    @Override
    public void onReadCompleted(UrlRequest request, UrlResponseInfo responseInfo, ByteBuffer byteBuffer) {
        Log.i(TAG, "onReadCompleted method called.");

        byteBuffer.clear();
        request.read(byteBuffer);
    }

    @Override
    public void onSucceeded(UrlRequest request, UrlResponseInfo responseInfo) {
        Log.i(TAG, "onSucceeded method called.");
    }

    @Override
    public void onFailed(UrlRequest request, UrlResponseInfo responseInfo, CronetException error) {
        Log.e(TAG, "onFailed method called." + error.getMessage());
    }
}

