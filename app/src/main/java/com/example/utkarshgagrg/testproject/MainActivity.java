package com.example.utkarshgagrg.testproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.thalmic.myo.AbstractDeviceListener;
import com.thalmic.myo.DeviceListener;
import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;
import com.thalmic.myo.scanner.ScanActivity;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Hub hub = Hub.getInstance();
        if (!hub.init(this)) {
            Log.e("Something", "Could not initialize the Hub.");
            finish();
            return;
        }
        Hub.getInstance().setLockingPolicy(Hub.LockingPolicy.NONE);
        Hub.getInstance().addListener(mListener);
        Intent intent = new Intent(this, ScanActivity.class);
        this.startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    private DeviceListener mListener = new AbstractDeviceListener() {
        @Override
        public void onConnect(Myo myo, long timestamp) {
            Toast.makeText(MainActivity.this, "Myo Connected!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDisconnect(Myo myo, long timestamp) {
            Toast.makeText(MainActivity.this, "Myo Disconnected!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPose(Myo myo, long timestamp, Pose pose) {
            Toast.makeText(MainActivity.this, "Pose: " + pose, Toast.LENGTH_SHORT).show();

            //TODO: Do something awesome.
        }
    };
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}