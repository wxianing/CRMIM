package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.ProduceEntity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/29
 */
public class PrototypeAdapter extends BasicAdapter<ProduceEntity> {
    public PrototypeAdapter(List<ProduceEntity> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_prototype_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.productName.setText(mDatas.get(position).getProductName());
        return convertView;
    }

    private static class ViewHolder {

        @ViewInject(R.id.produce_name)
        private TextView productName;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
