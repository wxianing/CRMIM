package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.meidp.crmim.MyApplication;
import com.meidp.crmim.R;
import com.meidp.crmim.model.Friends;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/21
 */
public class SelectFriendAdapter extends BasicAdapter<Friends> {
    private static HashMap<Integer, Boolean> isSelected;


    public SelectFriendAdapter(List<Friends> mDatas, Context context) {
        super(mDatas, context);
        isSelected = new HashMap<Integer, Boolean>();
        initDate();
    }

    // 初始化isSelected的数据
    private void initDate() {
        for (int i = 0; i < mDatas.size(); i++) {
            getIsSelected().put(i, false);
        }
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_select_friends, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.userName.setText(mDatas.get(position).getEmployeeName());
        ImageLoader.getInstance().displayImage(mDatas.get(position).getPhotoURL(), vh.img, MyApplication.options);
//        vh.checkBox.setChecked(getIsSelected().get(position));
        return convertView;
    }

    public static class ViewHolder {
        @ViewInject(R.id.header_img)
        private ImageView img;
        @ViewInject(R.id.username)
        private TextView userName;
        @ViewInject(R.id.check_box)
        public CheckBox checkBox;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }


    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        SelectFriendAdapter.isSelected = isSelected;
    }
}
