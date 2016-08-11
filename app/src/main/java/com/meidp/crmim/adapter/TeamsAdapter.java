package com.meidp.crmim.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.Teams;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/11
 */
public class TeamsAdapter extends BasicAdapter<Teams> {
    public TeamsAdapter(List<Teams> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_teams_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.teamsName.setText(mDatas.get(position).getTeamName());
        vh.teamsMember.setText(mDatas.get(position).getEmployeeNames());
        return convertView;
    }

    private static class ViewHolder {
        @ViewInject(R.id.teams_name)
        private TextView teamsName;
        @ViewInject(R.id.teams_member)
        private TextView teamsMember;

        public ViewHolder(View v) {
            x.view().inject(this, v);
        }
    }
}
