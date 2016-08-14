package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.Product;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/12
 */
public class ProduceAdapter extends BasicAdapter<Product> {

    private static HashMap<Integer, Boolean> isSelected;

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        ProduceAdapter.isSelected = isSelected;
    }

    public ProduceAdapter(List<Product> mDatas, Context context) {
        super(mDatas, context);
        isSelected = new HashMap<Integer, Boolean>();
        initDate();
    }

    // 初始化isSelected的数据
    private void initDate() {
        for (int i = 0; i < mDatas.size(); i++) {
            getIsSelected().put(i, false);
        }
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

    public static class ViewHolder {
        @ViewInject(R.id.produce_name)
        public TextView produceName;

        @ViewInject(R.id.check_box)
        public CheckBox checkBox;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
