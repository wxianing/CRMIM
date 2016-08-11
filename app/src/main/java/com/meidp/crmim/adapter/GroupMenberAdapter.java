package com.meidp.crmim.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.MyApplication;
import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBean;
import com.meidp.crmim.model.Menber;
import com.meidp.crmim.model.User;
import com.meidp.crmim.utils.Constant;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/8
 */
public class GroupMenberAdapter extends BasicAdapter<Menber> {
    public GroupMenberAdapter(List<Menber> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_friends_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
            resetView(vh);
        }
        vh.headerImg.setImageResource(R.mipmap.headerphoto);
        vh.userName.setText(" ");
        vh.userName.setText(mDatas.get(position).getName());
        ImageLoader.getInstance().displayImage(mDatas.get(position).getPhoto(), vh.headerImg, MyApplication.optionsRounds);
//        findUserById(mDatas.get(position), vh.userName, vh.headerImg);

        return convertView;
    }

    public static class ViewHolder {
        @ViewInject(R.id.username)
        public TextView userName;
        @ViewInject(R.id.header_img)
        public ImageView headerImg;
        @ViewInject(R.id.layout)
        public LinearLayout layout;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }

    private void resetView(ViewHolder vh) {
        vh.userName.setText(null);
        vh.headerImg.setImageBitmap(null);

    }

    private void findUserById(final String userId, final TextView menberName, final ImageView img) {
        HashMap params = new HashMap();
        params.put("Id", Integer.valueOf(userId));
        HttpRequestUtils.getmInstance().post(context, Constant.GET_PERSON_INFORMATION, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppBean<User> appBean = JSONObject.parseObject(result, new TypeReference<AppBean<User>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    String avatar = appBean.getData().getPhotoURL();
                    String name = appBean.getData().getEmployeeName();
                    menberName.setText(name);
                    ImageLoader.getInstance().displayImage(avatar, img, MyApplication.optionsRounds);
                }
            }
        });
    }
}
