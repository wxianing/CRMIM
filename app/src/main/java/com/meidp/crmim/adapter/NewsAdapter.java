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
        vh.currTime.setText(mDatas.get(position).getModifiedDate());
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

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
