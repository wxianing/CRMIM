package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.CustomerLists;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/19
 */
public class CustomerListAdapter extends BasicAdapter<CustomerLists> {
    public CustomerListAdapter(List<CustomerLists> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_customer_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.name.setText(mDatas.get(position).getCustName());
        vh.number.setText(mDatas.get(position).getCustNo());

        return convertView;
    }

    private static class ViewHolder {
        @ViewInject(R.id.name_tv)
        private TextView name;
        @ViewInject(R.id.number)
        private TextView number;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
