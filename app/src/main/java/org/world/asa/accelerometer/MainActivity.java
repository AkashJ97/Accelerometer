package org.world.asa.accelerometer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{

    EditText id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void start(View view) {
        id = (EditText) findViewById(R.id.ipadd);
        String s=id.getText().toString();

        Intent i=new Intent(MainActivity.this,AcceleroActivity.class);
        i.putExtra("key",s);
        startActivity(i);
    }
}
