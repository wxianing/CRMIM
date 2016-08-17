package com.meidp.crmim.http;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.meidp.crmim.MyApplication;
import com.meidp.crmim.activity.MainActivity;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.CustomDialogUtils;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.SPUtils;
import com.meidp.crmim.utils.ToastUtils;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class HttpRequestUtils {
    private static HttpRequestUtils mInstance;

    private static String userCode;

    public static String getUserCode() {
        return userCode;
    }

    public static void setUserCode(String userCode) {
        HttpRequestUtils.userCode = userCode;
    }

    public HttpRequestUtils() {
    }

    public static synchronized HttpRequestUtils getmInstance() {
        if (mInstance == null) {
            synchronized (HttpRequestUtils.class) {
                if (mInstance == null) {
                    mInstance = new HttpRequestUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * 发送post请求
     */
    public void post(final Context mContext, String url, HashMap<String, Object> map, final HttpRequestCallBack mCallBack) {
//        CustomDialogUtils.showProgressDialog(mContext);

        RequestParams params = new RequestParams(url);
        params.addHeader("_appId", Constant.APPID);
        params.addHeader("_code", MainActivity.userCode);
        params.addBodyParameter("content-type", "application/json");
        if (null != map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        Log.e("params", params.toJSONString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("HttpResponse", result);
                mCallBack.onSuccess(result);
//                CustomDialogUtils.cannelProgressDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ToastUtils.showl(mContext, ex.getMessage());
                if (ex instanceof HttpException) {//网络异常
                    HttpException httpEx = (HttpException) ex;
                    int responseCode = httpEx.getCode();
                    String responseMsg = httpEx.getMessage();
                    String errorResult = httpEx.getResult();
                }
//                CustomDialogUtils.cannelProgressDialog();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                CustomDialogUtils.cannelProgressDialog();
            }

            @Override
            public void onFinished() {
                CustomDialogUtils.cannelProgressDialog();
            }
        });
    }

    /**
     * 发送get请求
     */

    public void get(Context mContext, String url, Map<String, String> map, final HttpRequestCallBack mCallBack) {
        CustomDialogUtils.showProgressDialog(mContext);
        RequestParams params = new RequestParams(url);
        params.addHeader("_appId", Constant.APPID);
        params.addHeader("_code", Constant.CODE);
        params.addBodyParameter("content-type", "application/json");
        if (null != map) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                params.addQueryStringParameter(entry.getKey(), entry.getValue());
            }
        }
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                mCallBack.onSuccess(result);
                CustomDialogUtils.cannelProgressDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                CustomDialogUtils.cannelProgressDialog();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                CustomDialogUtils.cannelProgressDialog();
            }

            @Override
            public void onFinished() {
                CustomDialogUtils.cannelProgressDialog();
            }
        });
    }

    /**
     * 上传文件
     */
    public void upLoadFile(String url, Map<String, Object> map, final HttpRequestCallBack mCallBack) {
        RequestParams params = new RequestParams(url);
        params.addHeader("_appId", Constant.APPID);
        params.addHeader("_code", Constant.CODE);
        params.addBodyParameter("content-type", "application/json");
        if (null != map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        params.setMultipart(true);
        x.http().get(params, new Callback.ProgressCallback<File>() {

            @Override
            public void onSuccess(File file) {
                mCallBack.onSuccess(file);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                mCallBack.onError(ex, isOnCallback);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                mCallBack.onCancelled(cex);
            }

            @Override
            public void onFinished() {
                mCallBack.onFinished();
            }

            @Override
            public void onWaiting() {
                mCallBack.onWaiting();
            }

            @Override
            public void onStarted() {
                mCallBack.onStarted();
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                mCallBack.onLoading(total, current, isDownloading);
            }

        });
    }

    /**
     * @param url      下载地址
     * @param filepath 保存路径
     */
    public void downLoadFile(String url, String filepath, final HttpRequestCallBack<File> mCallBack) {
        RequestParams params = new RequestParams(url);
        params.addHeader("_appId", Constant.APPID);
        params.addHeader("_code", Constant.CODE);
        params.addBodyParameter("content-type", "application/json");
        //设置断点续传
        params.setAutoResume(true);
        params.setSaveFilePath(filepath);
        x.http().get(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onSuccess(File result) {
                mCallBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                mCallBack.onError(ex, isOnCallback);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                mCallBack.onCancelled(cex);
            }

            @Override
            public void onFinished() {
                mCallBack.onFinished();
            }

            @Override
            public void onWaiting() {
                mCallBack.onWaiting();
            }

            @Override
            public void onStarted() {
                mCallBack.onStarted();
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                mCallBack.onLoading(total, current, isDownloading);
            }
        });
    }

    public void send(final Context mContext, String url, HashMap params, final HttpRequestCallBack mCallBack) {
        CustomDialogUtils.showProgressDialog(mContext);
        Log.e("addParams:", JSON.toJSONString(params));
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, JSON.toJSONString(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("response", response.toString());
                mCallBack.onSuccess(response.toString());
                CustomDialogUtils.cannelProgressDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                CustomDialogUtils.cannelProgressDialog();
            }
        }) {
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("_appId", Constant.APPID);
                if (NullUtils.isNull(MainActivity.userCode)) {
                    headers.put("_code", MainActivity.userCode);
                } else {
                    headers.put("_code", (String) SPUtils.get(mContext, "CODE", ""));
                }
                return headers;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(5000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyApplication.getmInstance().addToRequestQueue(request);
    }
}
