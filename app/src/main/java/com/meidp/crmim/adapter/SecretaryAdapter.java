package com.meidp.crmim.adapter;

import android.content.Context;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.Secretary;
import com.meidp.crmim.utils.NullUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/9
 */
public class SecretaryAdapter extends BasicAdapter<Secretary> {

    public SecretaryAdapter(List<Secretary> mDatas, Context context) {
        super(mDatas, context);
    }
//    ListView

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        Secretary data = mDatas.get(position);
//        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_secretary_list, parent, false);
//            vh = new ViewHolder(convertView);
//            convertView.setTag(vh);
        }

        TextView title = (TextView) convertView.findViewById(R.id.title_name);
        ImageView img = (ImageView) convertView.findViewById(R.id.img);
        title.setText("");
        TextPaint tp = title.getPaint();
        tp.setFakeBoldText(true);
//        else {
//            vh = (ViewHolder) convertView.getTag();
//        }

        if (NullUtils.isNull(data.getTitle())) {
            title.setText(mDatas.get(position).getTitle() + mDatas.get(position).getMsg());
        }
//        if (NullUtils.isNull(mDatas.get(position).getMsg())) {
//            vh.content.setText(mDatas.get(position).getMsg());
//        }
//        String timeStr = mDatas.get(position).getCreateTimeStr();
//        if (NullUtils.isNull(timeStr)) {
//            timeStr = timeStr.substring(5);
//            vh.time.setText(timeStr);
//        }

        String statusName = mDatas.get(position).getStatusName();
//        if (!statusName.equals("未读")) {
//            //已读消息
//            title.setTextColor(Color.rgb(189, 189, 189));
////            content.setTextColor(Color.rgb(189, 189, 189));
//        } else {
//            //未读
//            TextPaint tp1 = title.getPaint();
//            tp1.setFakeBoldText(true);
//        }

        int billTypeFlag = mDatas.get(position).getBillTypeFlag();
        int billTypeCode = mDatas.get(position).getBillTypeCode();

        if (billTypeFlag == 1 && billTypeCode == 4) {//费用
            img.setImageResource(R.mipmap.assistant_fee_icon);
        }
        if (billTypeFlag == 4 && billTypeCode == 4) {//客户拜访
            img.setImageResource(R.mipmap.assistant_customer_icon);
        }
        if (billTypeFlag == 4 && billTypeCode == 3) {//客户拜访
            img.setImageResource(R.mipmap.assistant_customer_icon);
        }
        if (billTypeFlag == 4 && billTypeCode == 7) {//客户联系人
            img.setImageResource(R.mipmap.assistant_customer_icon);
        }
        if (billTypeFlag == 5 && billTypeCode == 11) {//样机申请
            img.setImageResource(R.mipmap.assistant_prototype_icon);
        }
        if (billTypeFlag == 13 && billTypeCode == 1) {//项目
            img.setImageResource(R.mipmap.assistant_project_icon);
        }
        if (billTypeFlag == 0 && billTypeCode == 1) {

        }
        if (billTypeFlag == 5 && billTypeCode == 3) {//备货
            img.setImageResource(R.mipmap.assistant_prototype_icon);
        }

        return convertView;
    }

    private static class ViewHolder {

        @ViewInject(R.id.title_name)
        private TextView title;

        @ViewInject(R.id.content_tv)
        private TextView content;
        @ViewInject(R.id.time_tv)
        private TextView time;
        @ViewInject(R.id.img)
        private ImageView img;

        public ViewHolder(View v) {
            x.view().inject(this, v);
        }
    }
}
