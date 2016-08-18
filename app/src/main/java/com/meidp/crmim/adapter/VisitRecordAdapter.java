package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.VisitRecords;
import com.meidp.crmim.view.DActionSheetDialog;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/20
 */
public class VisitRecordAdapter extends BasicAdapter<VisitRecords> {
    public VisitRecordAdapter(List<VisitRecords> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        VisitRecords data = mDatas.get(position);
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_visitrecord_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.titleName.setText(data.getTitle());
        vh.address_tv.setText("地址：" + data.getLocationAddress());
        vh.visitTime.setText(data.getModifiedDate());
        vh.linkman.setText("联系人：" + data.getCustLinkManName());
        vh.phoneNum.setText("电      话：" + data.getLinkTel());

        return convertView;
    }

    private static class ViewHolder {
        @ViewInject(R.id.title_name)
        private TextView titleName;
        @ViewInject(R.id.address_tv)
        private TextView address_tv;
        @ViewInject(R.id.visit_time)
        private TextView visitTime;

        @ViewInject(R.id.linkman)
        private TextView linkman;
        @ViewInject(R.id.phone_num)
        private TextView phoneNum;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
