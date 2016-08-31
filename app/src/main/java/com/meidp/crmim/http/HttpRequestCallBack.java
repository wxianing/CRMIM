package com.meidp.crmim.http;

import org.xutils.common.Callback;

import java.io.IOException;

/**
 * Package：com.meidp.crmim.http
 * 作  用：
 * Author：wxianing
 * 时  间：2016/6/18
 */

public abstract class HttpRequestCallBack<ResultType> {

    public abstract void onSuccess(String result) throws IOException;

    public void onSuccess(ResultType result) {
    }

    public void onError(Throwable ex, boolean isOnCallback) {
    }

    public void onCancelled(Callback.CancelledException cex) {

    }

    public void onFinished() {

    }

    public void onWaiting() {

    }

    public void onStarted() {

    }

    public void onLoading(long total, long current, boolean isDownloading) {

    }
}