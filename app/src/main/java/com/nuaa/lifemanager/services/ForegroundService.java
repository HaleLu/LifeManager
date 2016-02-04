package com.nuaa.lifemanager.services;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.nuaa.lifemanager.MainActivity;
import com.nuaa.lifemanager.R;

/**
 * Created by Hale Lu on 2016/2/3.
 */
public class ForegroundService extends Service {
    private static final String TAG = ForegroundService.class.getName();
    private static volatile boolean running = false;
    private static volatile boolean shuttingDown = false;

    public static boolean isRunning()
    {
        return running;
    }

    public static boolean isShuttingDown()
    {
        return shuttingDown;
    }

    private void scheduleRestart()
    {
    }

    public static void setShuttingDown()
    {
        shuttingDown = true;
    }

    private void startForeground()
    {
//        all localall = ((QTApplication)getApplicationContext()).b().d;
//        int i = ?;
//        long l1 = ?;
//        if (localall != null)
//        {
//            i = localall.a(aqy.a());
//            long l2 = aqy.a();
//            long l3 = System.currentTimeMillis();
//            l1 = localall.a.c().getTotalAppUsage(l2, l3);
//        }
        while (true)
        {
//            String str;
//            RemoteViews localRemoteViews = null;
            NotificationCompat.Builder localBuilder = null;
            if (getPackageName() != null)
            {
//                str = getPackageName();
//                localRemoteViews = new RemoteViews(str, 2130903094);
//                Object[] arrayOfObject = new Object[1];
//                arrayOfObject[0] = Integer.valueOf(i);
//                localRemoteViews.setTextViewText(2131427469, getString(2131558645, arrayOfObject));
//                localRemoteViews.setTextViewText(2131427468, aqy.a((int)l1 / 60));
                localBuilder = new NotificationCompat.Builder(this);
                localBuilder.setSmallIcon(R.mipmap.ic_launcher);
                Intent localIntent = new Intent(getApplicationContext(), MainActivity.class);
                localIntent.putExtra("notification", true);
                localBuilder.setPriority(Notification.PRIORITY_MIN);
                localBuilder.setContentIntent(PendingIntent.getActivity(this, 1, localIntent, PendingIntent.FLAG_CANCEL_CURRENT));
            }
            for (Notification localNotification = localBuilder.getNotification(); ; localNotification = localBuilder.build())
            {
//                localNotification.contentView = localRemoteViews;
                startForeground(START_REDELIVER_INTENT, localNotification);
                return;
            }
        }
    }

    @Override
    public final void onCreate()
    {
        super.onCreate();
        shuttingDown = false;
        startForeground();
        running = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.d(TAG, "onStartCommand");

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        running = false;
        scheduleRestart();
    }
}
