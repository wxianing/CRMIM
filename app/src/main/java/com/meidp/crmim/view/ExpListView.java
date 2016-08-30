package com.meidp.crmim.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;

/**
 * Package： com.meidp.crmim.view
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/10
 */
public class ExpListView extends ExpandableListView {
    public ExpListView(Context context) {
        super(context);
    }

    public ExpListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, height);
    }
}
