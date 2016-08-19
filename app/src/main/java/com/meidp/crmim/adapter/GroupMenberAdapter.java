package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meidp.crmim.MyApplication;
import com.meidp.crmim.R;
import com.meidp.crmim.model.Menber;
import com.meidp.crmim.utils.NullUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/8
 */
public class GroupMenberAdapter extends BasicAdapter<Menber.UsersBean> {
    public GroupMenberAdapter(List<Menber.UsersBean> mDatas, Context context) {
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

        if (NullUtils.isNull(mDatas.get(position).getQuarterName())) {
            vh.userName.setText(mDatas.get(position).getEmployeeName() + "(" + mDatas.get(position).getQuarterName() + ")");
        } else {
            vh.userName.setText(mDatas.get(position).getEmployeeName());
        }
        ImageLoader.getInstance().displayImage(mDatas.get(position).getPhotoURL(), vh.headerImg, MyApplication.optionsRounds);
        if (NullUtils.isNull(mDatas.get(position).getDeptName())) {
            vh.department.setText(mDatas.get(position).getDeptName());
        }
        return convertView;
    }

    public static class ViewHolder {
        @ViewInject(R.id.username)
        public TextView userName;
        @ViewInject(R.id.header_img)
        public ImageView headerImg;
        @ViewInject(R.id.layout)
        public LinearLayout layout;
        @ViewInject(R.id.department_tv)
        private TextView department;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }

    private void resetView(ViewHolder vh) {
        vh.userName.setText(null);
        vh.headerImg.setImageBitmap(null);
    }
}
