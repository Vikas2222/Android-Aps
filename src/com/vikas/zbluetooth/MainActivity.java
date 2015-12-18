package com.vikas.zbluetooth;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Button on,off;
	BluetoothAdapter bA;
	TextView tv;
	ListView lv;
	Set<BluetoothDevice> paireddevices;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bA = BluetoothAdapter.getDefaultAdapter();
		on = (Button) findViewById(R.id.button1);
		off = (Button) findViewById(R.id.button2);
		tv = (TextView) findViewById(R.id.textView1);
		lv = (ListView) findViewById(R.id.listView1);
		
		
		on.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!bA.isEnabled()){
					Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
					startActivity(intent);
					Toast.makeText(getApplicationContext(), "Bluetooth Started..", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(getApplicationContext(), "Bluetooth Already Started..", Toast.LENGTH_SHORT).show();
				}				
			}
		});
		
		off.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bA.disable();
				Toast.makeText(getApplicationContext(), "Bluetooth Disabled..", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				paireddevices = bA.getBondedDevices();
				List<String> list = new ArrayList<String>();
				
				for(BluetoothDevice ba : paireddevices)
					list.add(ba.getName());
				ArrayAdapter<String> arr = new ArrayAdapter<String>(MainActivity.this,
						android.R.layout.simple_list_item_1,list);
				lv.setAdapter(arr);
			}
		});
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
