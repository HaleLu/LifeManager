package com.nuaa.lifemanager.services;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Hale Lu on 2016/2/1.
 */
public class GetMovementService extends Service {
    public enum Movement {
        STANDING, LYING, RUNNING
    }

    private Sensor sensor = null;
    private SensorManager sensorManager;

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
//            Log.i("onSensorChanged", "changed");
            float[] values = event.values;
//            StringBuffer sb = new StringBuffer(256);
//            sb.append("Time : ");
//            sb.append(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.getDefault()).format(new Date()).toString());
//            sb.append("\n沿X轴旋转的角度为：" + Float.toString(values[0]));
//            sb.append("\n沿Y轴旋转的角度为：" + Float.toString(values[1]));
//            sb.append("\n沿Z轴旋转的角度为：" + Float.toString(values[2]) + "\n");
//            Log.i("text",sb.toString());
//            Log.i("movement", toMovement(values).toString());
            //WriteTxtFile(sb.toString(), "xyzMessage.txt");
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    private Movement toMovement(float values[]) {
        // TODO: 2016/2/2 Judge Movement situation
        return Movement.STANDING;
    }
}
