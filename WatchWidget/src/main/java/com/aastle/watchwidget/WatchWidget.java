package com.aastle.watchwidget;

/**
 * Created by prometheus on 8/13/13.
 */


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.util.Calendar;
import com.aastle.android.utility;


public class WatchWidget extends AppWidgetProvider
{
    Context mContext;
    Resources res;
    public static String ASAWA_WIDGET_UPDATE = "com.aastle.watchwidget.ASAWA_WIDGET_UPDATE";
    public static String LOG_TAG = "asawa";


    private PendingIntent createClockTickIntent(Context context) {
        //Log.d(LOG_TAG, "createClockTickIntent()");
        Intent intent = new Intent(ASAWA_WIDGET_UPDATE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }

    private void updateAppWidget(Context context,AppWidgetManager appWidgetManager,int appWidgetId){
        mContext = context;
        res = mContext.getResources();
        RemoteViews remoteViews;
        ComponentName watchWidget;
        remoteViews = new RemoteViews(context.getPackageName(),R.layout.main);
        watchWidget = new ComponentName(context,WatchWidget.class);
        remoteViews.setTextViewText(R.id.widget_textview, randomLoveNote());
        remoteViews.setTextColor(R.id.widget_textview,utility.randomColor());
        remoteViews.setInt(R.id.LinearLayout01,"setBackgroundResource",utility.getRandomBackgroundId(this.randomBackgroundName(),mContext,mContext.getPackageName()));
        appWidgetManager.updateAppWidget(watchWidget, remoteViews);
    }

    @Override
    public void onEnabled(Context context){
        //Log.d(LOG_TAG, "onEnabled()");
        super.onEnabled(context);
        mContext = context;
        res = mContext.getResources();

        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 1);

        alarmManager.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), 1800000, createClockTickIntent(context));

        RemoteViews view = new RemoteViews(context.getPackageName(), R.layout.main);
        String note = randomLoveNote();
        view.setTextViewText(R.id.widget_textview, note);
        view.setTextColor(R.id.widget_textview,utility.randomColor());
        // Push update for this widget to the home screen
        ComponentName thisWidget = new ComponentName(context, WatchWidget.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(thisWidget, view);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,int[] appWidgetIds){
        //Log.d(LOG_TAG, "onUpdate()");
        createClockTickIntent(context);
        updateAppWidget(context,appWidgetManager,appWidgetIds[0]);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (ASAWA_WIDGET_UPDATE.equals(intent.getAction())) {
            //Log.d(LOG_TAG, "onReceive()");
            ComponentName thisAppWidget = new ComponentName(context.getPackageName(), getClass().getName());
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            int ids[] = appWidgetManager.getAppWidgetIds(thisAppWidget);
            for (int appWidgetID: ids) {
                updateAppWidget(context, appWidgetManager, appWidgetID);
            }
        }
    }
    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        //Log.d(LOG_TAG, "Widget Provider disabled. Turning off timer");
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(createClockTickIntent(context));
    }



    private String randomLoveNote (){
        int randomNumber;
        String love;
        String[] love_notes = res.getStringArray(R.array.love_notes);
        randomNumber = utility.randomNumber(0, love_notes.length -1 );
        love = love_notes[randomNumber];
        return love;
    }



    private String randomBackgroundName(){

        int randomNumber;
        String back = "";
        String[] backgrounds = res.getStringArray(R.array.backgrounds);
        randomNumber = utility.randomNumber(0,backgrounds.length-1);
        back = backgrounds[randomNumber];
        return back;
    }



}
