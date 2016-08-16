package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.ProjectDirections;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/15
 */
public class ProjectDirectionAdapter extends BasicAdapter<ProjectDirections> {
    public ProjectDirectionAdapter(List<ProjectDirections> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_direction_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.textView.setText(mDatas.get(position).getTypeName());
        return convertView;
    }

    private static class ViewHolder {
        @ViewInject(R.id.textview)
        private TextView textView;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
