package com.meidp.crmim.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.List;

/**
 * Package： com.meidp.crmim.widget
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/30
 */
public class BudgetPieChart extends AbstractDemoChart {
    @Override
    public String getName() {
        return "Budget chart";
    }

    @Override
    public String getDesc() {
        return "The budget per project for this year (pie chart)";
    }

    @Override
    public Intent execute(Context context) {
        double[] values = new double[]{39, 61,};//饼图分层5块,每块代表的数值
        int[] colors = new int[]{Color.BLUE, Color.MAGENTA};//每块饼图的颜色
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(false);//设置显示放大缩小按钮
        renderer.setZoomEnabled(true);//设置允许放大缩小.
        renderer.setChartTitleTextSize(30);//设置图表标题的文字大小
        return ChartFactory.getPieChartIntent(context, buildCategoryDataset("Project budget", values), renderer, "Budget");//构建Intent, buildCategoryDataset是调用AbstraDemoChart的构建方法.
    }

    public View createView(Context context, String[] titles, double[] values) {

        int[] colors = new int[]{Color.BLUE, Color.MAGENTA};//每块饼图的颜色
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(false);//设置显示放大缩小按钮
        renderer.setZoomEnabled(true);//设置允许放大缩小.
        renderer.setChartTitleTextSize(30);//设置图表标题的文字大小
//        renderer.setChartTitle("我的业绩");
        renderer.setLegendTextSize(20);//左下导航字体大小
        renderer.setLabelsTextSize(20);//饼图周围说明字体大小
        renderer.setDisplayValues(true);//图形上显示数值
        renderer.setInScroll(false);//设置是否可以滚动
        renderer.setExternalZoomEnabled(true);//设置是否可以缩放
        renderer.setShowAxes(true);
        renderer.setPanEnabled(true);

        CategorySeries series = new CategorySeries("我的业绩");
        series.add(titles[0], values[0]);
        series.add(titles[1], values[1]);

        View view = ChartFactory.getPieChartView(context, series, renderer);
        return view;
    }
}
