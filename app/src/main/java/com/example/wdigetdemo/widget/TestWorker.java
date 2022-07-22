package com.example.wdigetdemo.widget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.wdigetdemo.R;
import com.example.wdigetdemo.TimeUtil;

/**
 * Author: clement
 * Create: 2022/7/22
 * Desc:
 */
public class TestWorker extends Worker {

    public TestWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        //模拟耗时/网络请求操作
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //刷新widget
        updateWidget(getApplicationContext());

        return Result.success();
    }

    /**
     * 刷新widget
     */
    private void updateWidget(Context context) {
        String data = TimeUtil.long2String(System.currentTimeMillis(), TimeUtil.HOUR_MM_SS);
        //只能通过远程对象来设置appwidget中的控件状态
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        //通过远程对象修改textview
        remoteViews.setTextViewText(R.id.tv_text, data);

        //获得appwidget管理实例，用于管理appwidget以便进行更新操作
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        //获得所有本程序创建的appwidget
        ComponentName componentName = new ComponentName(context, TestWidgetProvider.class);
        //更新appwidget
        appWidgetManager.updateAppWidget(componentName, remoteViews);
    }
}
