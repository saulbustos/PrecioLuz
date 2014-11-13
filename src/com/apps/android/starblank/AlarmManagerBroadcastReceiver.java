package com.apps.android.starblank;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.widget.RemoteViews;
 
public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {
 Utility ut=new Utility();   
 
 @Override
 public void onReceive(Context context, Intent intent) {
     
  PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
  PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "TAG");

  
  //Acquire the lock
  wl.acquire();
  //Bloqueamos, pintamos y desbloqueamos
  try{
      //You can do the processing here update the widget/remote views.
      RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
      R.layout.main);
      String[]salida=ut.parsea();
      if (salida!=null){
        remoteViews.setInt(R.id.widget_textview, "setBackgroundResource",ut.getColor(salida[0]));
        remoteViews.setTextViewText(R.id.widget_textview,"Precio Actual: "+salida[1]+"€/kWh\n  Max: "+salida[2]+"€  Media: "+salida[3]+"€  ");
      }
      ComponentName thiswidget = new ComponentName(context, PrecioLuz.class);
      AppWidgetManager manager = AppWidgetManager.getInstance(context);
      manager.updateAppWidget(thiswidget, remoteViews);
  }catch(Exception e){}
 //Release the lock
  wl.release();
 }
}
