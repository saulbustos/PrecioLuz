package com.apps.android.starblank;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;
 
public class PrecioLuz extends AppWidgetProvider {
   
    
 @Override
 public void onDeleted(Context context, int[] appWidgetIds) {

  super.onDeleted(context, appWidgetIds);
 }
 
 @Override
 public void onDisabled(Context context) {
  
  Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
  PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
  AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
  alarmManager.cancel(sender);
  super.onDisabled(context);
 }
 
 @Override
 public void onEnabled(Context context) {
  super.onEnabled(context);
  AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
  Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
  PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);

//Set alarmManager to call the webservice every 30 seconds
  am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+ 1000, 30000 , pi);
  RemoteViews remoteViews =
                new RemoteViews( context.getPackageName(), R.layout.main );
                ComponentName myWidget =
                new ComponentName( context, PrecioLuz.class );

                Intent intent2 = new Intent(context, DummyActivity.class);
                
                PendingIntent pendingIntent =
                   PendingIntent.getActivity(context, 15, intent2, 0);    

                remoteViews.setOnClickPendingIntent(R.id.widget_textview, pendingIntent);
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                appWidgetManager.updateAppWidget(myWidget, remoteViews);
  
 }
 
 @Override
 public void onUpdate(Context context, AppWidgetManager appWidgetManager,
   int[] appWidgetIds) {
  RemoteViews remoteViews =
                new RemoteViews( context.getPackageName(), R.layout.main );
                ComponentName myWidget =
                new ComponentName( context, PrecioLuz.class );

                Intent intent = new Intent(context, DummyActivity.class);
                PendingIntent pendingIntent =
                   PendingIntent.getActivity(context, 151, intent, 0);    

                remoteViews.setOnClickPendingIntent(R.id.widget_textview, pendingIntent);
                appWidgetManager = AppWidgetManager.getInstance(context);
                appWidgetManager.updateAppWidget(myWidget, remoteViews);
 }
 

 public void onAppWidgetOptionsChanged(Context context,
   AppWidgetManager appWidgetManager, int appWidgetId,
   Bundle newOptions) {
 //pokemon!
 }
 
 
 
}
