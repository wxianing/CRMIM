package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.Product;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/12
 */
public class ProduceAdapter extends BasicAdapter<Product> {

    public ProduceAdapter(List<Product> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.item_produce_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.produceName.setText(mDatas.get(position).getProductName());
        return convertView;
    }

    private static class ViewHolder {
        @ViewInject(R.id.produce_name)
        private TextView produceName;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }

}