package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.meidp.crmim.MyApplication;
import com.meidp.crmim.R;
import com.meidp.crmim.model.Informations;
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
public class NewsAdapter extends BasicAdapter<Informations> {
    public NewsAdapter(List<Informations> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_news_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(mDatas.get(position).getImgUrl(), vh.img, MyApplication.options);
        vh.newsTitle.setText(mDatas.get(position).getTitle());
        vh.content.setText(mDatas.get(position).getCulturetent());

        String time = mDatas.get(position).getModifiedDate();
        time = time.substring(0,16);

        vh.currTime.setText(time);
        if (mDatas.get(position).getIsRead() == 0) {
            vh.redDot.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    private static class ViewHolder {

        @ViewInject(R.id.img)
        private ImageView img;

        @ViewInject(R.id.news_title)
        private TextView newsTitle;
        @ViewInject(R.id.news_content)
        private TextView content;
        @ViewInject(R.id.curr_time)
        private TextView currTime;
        @ViewInject(R.id.red_dot)
        private ImageView redDot;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
