package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.DocBean;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/9/1
 */
public class DocumentAdapter extends BasicAdapter<DocBean> {
    public DocumentAdapter(List<DocBean> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_document_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.documentName.setText(mDatas.get(position).getFileName());
        return convertView;
    }

    private static class ViewHolder {
        private TextView documentName;

        public ViewHolder(View view) {
            this.documentName = (TextView) view.findViewById(R.id.document_name);
        }
    }
}
