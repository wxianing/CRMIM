package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.HomeEntrity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by John on 2016/7/4.
 */
public class HomeGvAdapter extends BasicAdapter<HomeEntrity> {
    public HomeGvAdapter(List<HomeEntrity> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_home_gridview, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.img.setImageResource(mDatas.get(position).getResId());
        vh.titleName.setText(mDatas.get(position).getResName());
        return convertView;
    }


    private static class ViewHolder {
        @ViewInject(R.id.img)
        private ImageView img;
        @ViewInject(R.id.title_name)
        private TextView titleName;

        public ViewHolder(View v) {
            x.view().inject(this, v);
        }
    }

}
