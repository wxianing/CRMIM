package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meidp.crmim.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by John on 2016/7/7.
 */
public class SpinnerAdapter extends BasicAdapter<String> {
    public SpinnerAdapter(List<String> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_spinner_layout, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.textView.setText(mDatas.get(position));

        return convertView;
    }

    private static class ViewHolder {
        @ViewInject(R.id.type_text)
        private TextView textView;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
