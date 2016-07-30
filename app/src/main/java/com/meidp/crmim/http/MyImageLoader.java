package com.meidp.crmim.http;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.meidp.crmim.utils.ImageUtils;
import com.meidp.crmim.utils.SPUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Package： com.meidp.crmim.http
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/20
 */
public class MyImageLoader extends AsyncTask<String, Void, Bitmap> {
    private Context mContext;

    public MyImageLoader(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream inputStream = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap != null) {
            String headPortrait = ImageUtils.bitmaptoString(bitmap);
            SPUtils.save(mContext, "headPortrait", headPortrait);
        }
    }
}
