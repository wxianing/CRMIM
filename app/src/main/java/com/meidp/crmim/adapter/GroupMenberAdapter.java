package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meidp.crmim.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/8
 */
public class GroupMenberAdapter extends BasicAdapter<String> {
    public GroupMenberAdapter(List<String> mDatas, Context context) {
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

        if (mDatas.get(position).equals("1")) {
            vh.userName.setText("汪志红");
        } else if (mDatas.get(position).equals("2")) {
            vh.userName.setText("马云");
        } else if (mDatas.get(position).equals("3")) {
            vh.userName.setText("王显宁");
        } else if (mDatas.get(position).equals("4")) {
            vh.userName.setText("熊国和");
        } else if (mDatas.get(position).equals("7")) {
            vh.userName.setText("王健林");
        }
        return convertView;
    }

    private static class ViewHolder {
        @ViewInject(R.id.username)
        private TextView userName;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
