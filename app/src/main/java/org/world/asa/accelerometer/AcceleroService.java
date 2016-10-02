package org.world.asa.accelerometer;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Akash on 29-09-2016.
 */
public class AcceleroService extends Service implements SensorEventListener {

    static float y;
    private SensorManager sm;
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        {
            y = sensorEvent.values[1];
            Log.d("valure",""+y);
        AcceleroActivity.out.println(y+","+" ");}
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public int onStartCommand(Intent i , int flag , int start)
    {
        sm=(SensorManager)getSystemService(SENSOR_SERVICE);;
        Sensor s = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, s,SensorManager.SENSOR_DELAY_NORMAL);
        return Service.START_STICKY;
    }
}
