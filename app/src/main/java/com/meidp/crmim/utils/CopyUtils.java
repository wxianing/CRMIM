package com.meidp.crmim.utils;

import android.content.ClipboardManager;
import android.content.Context;

/**
 * Package： com.meidp.crmim.utils
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/26
 */
public class CopyUtils {
    /**
     * 实现文本复制功能
     * add by wangqianzhou
     *
     * @param content
     */
    public static void copy(String content, Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }

    /**
     * 实现粘贴功能
     * add by wangqianzhou
     *
     * @param context
     * @return
     */
    public static String paste(Context context) {
        // 得到剪贴板管理器
        if (context != null) {
            ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            return cmb.getText().toString().trim();
        }
        return null;
    }
}
