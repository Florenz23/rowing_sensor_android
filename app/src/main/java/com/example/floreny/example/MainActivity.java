package com.example.floreny.example;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private BluetoothAdapter mBluetoothAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sendData();
        connectToBluetooth();
        //startTimer();
        //setText();
        countDown();
    }


//    public void sendMessage(View view) {
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
//    }

    private void countDown() {
        new CountDownTimer(30000, 100) {
            TextView textView = (TextView) findViewById(R.id.sendDataTextView);
            public void onTick(long millisUntilFinished) {
                long sendValue = millisUntilFinished/100;
                textView.setText("" + sendValue);
                sendData(""+sendValue);
            }

            public void onFinish() {
                textView.setText("done!");
                sendData("done");
            }
        }.start();

    }
    public void sendMessage(View view) {
        Log.d("moin","moinm");
    }
    public void sendData(String value) {
        Log.d("moin","haha");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message/check/moin");

        myRef.setValue(value);
    }

    public void connectToBluetooth() {

        Log.d("log","ini adapter");
        // Initializes Bluetooth adapter.
        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
    }
}
