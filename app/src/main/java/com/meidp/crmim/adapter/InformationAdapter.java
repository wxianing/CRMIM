package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jauker.widget.BadgeView;
import com.meidp.crmim.MyApplication;
import com.meidp.crmim.R;
import com.meidp.crmim.model.InformationClassify;
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
public class InformationAdapter extends BasicAdapter<InformationClassify> {

    public InformationAdapter(List<InformationClassify> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_information_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
            resetViewHolder(vh);
        }

        ImageLoader.getInstance().displayImage(mDatas.get(position).getIcon(), vh.imageView, MyApplication.options);
        vh.titleName.setText(mDatas.get(position).getTypeName());
        vh.explain.setText(mDatas.get(position).getLastDataTitle());
        int unReaderCount = mDatas.get(position).getNoReadTotal();

        BadgeView badgeView = new BadgeView(context);
        badgeView.setTargetView(vh.announcementImg);
        badgeView.setBadgeCount(unReaderCount);

        return convertView;
    }

    protected void resetViewHolder(ViewHolder vh) {
        vh.imageView.setImageBitmap(null);
        vh.titleName.setText(null);
        vh.announcementImg.setImageBitmap(null);
    }

    private static class ViewHolder {
        @ViewInject(R.id.img)
        private ImageView imageView;
        @ViewInject(R.id.announcement)
        private TextView titleName;
        @ViewInject(R.id.announcement_img)
        private ImageView announcementImg;
        @ViewInject(R.id.explain)
        private TextView explain;

        public ViewHolder(View v) {
            x.view().inject(this, v);
        }
    }
}
