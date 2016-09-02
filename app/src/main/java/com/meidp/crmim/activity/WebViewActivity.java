package com.meidp.crmim.activity;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppMsg;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.CustomDialogUtils;
import com.meidp.crmim.utils.NetUtils;
import com.meidp.crmim.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

@ContentView(R.layout.activity_web_view)
public class WebViewActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;

    @ViewInject(R.id.webview)
    private WebView mWebView;
    private int oid;
    private int sType;

    @Override
    public void onInit() {
        String titleName = getIntent().getStringExtra("TITLE");
        title.setText(titleName);
        String link = getIntent().getStringExtra("ClickUrl");
        if (NetUtils.isConnected(this)) {
            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            //设置可以访问文件
            webSettings.setAllowFileAccess(true);
            //设置支持缩放
            webSettings.setBuiltInZoomControls(true);
            //加载需要显示的网页
            mWebView.loadUrl(link);
            //设置Web视图
            mWebView.setWebViewClient(new MyWebViewClient());

            oid = getIntent().getIntExtra("OID", -1);
            sType = getIntent().getIntExtra("sType", -1);
            if (sType != -1) {
                sendMsg(sType);
            }
        }else {
            ToastUtils.shows(this, "网络连接不可用");
        }
    }

    /**
     * "IdTwo": 1,
     * "IdThree": 2,
     * "Id": 3
     */
    private void sendMsg(int sType) {
        HashMap params = new HashMap();

        params.put("IdTwo", 2);
        params.put("IdThree", sType);
        params.put("Id", oid);

        HttpRequestUtils.getmInstance().send(WebViewActivity.this, Constant.SAVE_UNREADER, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                });
                if (appMsg != null && appMsg.getEnumcode() == 0) {
                    Log.e("未读标记", "标记成功");
                } else {
                    Log.e("未读标记", "标记失败");
                }
            }
        });
    }

    @Event({R.id.back_arrows})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
        }
    }

    //Web视图
    private class MyWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            CustomDialogUtils.cannelProgressDialog();
        }
    }

    @Override
    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        finish();//结束退出程序
        return false;
    }
}
