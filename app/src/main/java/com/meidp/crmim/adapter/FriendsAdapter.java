package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.Friends;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/20
 */
public class FriendsAdapter extends BasicAdapter<Friends> {
    public FriendsAdapter(List<Friends> mDatas, Context context) {
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
        }
        vh.userName.setText(mDatas.get(position).getLoginName());
        return convertView;
    }

    private static class ViewHolder {
        @ViewInject(R.id.header_img)
        private ImageView img;
        @ViewInject(R.id.username)
        private TextView userName;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
