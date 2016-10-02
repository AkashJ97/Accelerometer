package org.world.asa.accelerometer;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class AcceleroActivity extends AppCompatActivity {

    private SensorManager sm;
    private Sensor sensor;
    private String s = "";
    TextView textView;
    private Socket socket;
    AcceleroService acceleroService = new AcceleroService();
    static public PrintWriter out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelero);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            s = extras.getString("key");
//get the value based on the key
        }
        Connect connect=new Connect();
        connect.execute();

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sm.getSensorList(Sensor.TYPE_LINEAR_ACCELERATION).get(0);
        textView= (TextView)findViewById(R.id.textView);
        textView.setText("Connected to " + s);
    }

    public void disconnect(View view) {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class Connect extends AsyncTask<String,Void,Boolean>{


    @Override
    protected Boolean doInBackground(String... strings) {
        boolean result = true;
        try{

            socket = new Socket(s, 8080);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(Boolean result){
      try{
          out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);

              startService(new Intent(AcceleroActivity.this,AcceleroService.class));

      } catch (Exception e) {


      }
    }


}




}