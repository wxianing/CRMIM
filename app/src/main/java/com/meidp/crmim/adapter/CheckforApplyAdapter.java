package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.meidp.crmim.R;
import com.meidp.crmim.model.CheckforApply;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/6
 */
public class CheckforApplyAdapter extends BasicAdapter<CheckforApply> {
    public CheckforApplyAdapter(List<CheckforApply> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView = inflater.inflate(R.layout.item_checkfor_list,parent,false);
        }
        return convertView;
    }
}
