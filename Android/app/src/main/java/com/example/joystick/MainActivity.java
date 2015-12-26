package com.example.joystick;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * MainActivity that provides the USER the
 * ability to provide IP address and port
 * number. Provides a connect button which will
 * initiate the process of completing the connection.
 *
 * @author Arjun Passi (apassi)
 */
public class MainActivity extends Activity {

    /* Reference to key for IP address.
     * This is used while passing it through the Intent */
    public static String IP_ADDR = "IP_ADDR";

    /* Reference to key for Port number.
     * This is used while passing it through the Intent */
    public static String PORT_NUM = "PORT_NUM";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Setup The Buttons
		setUpConnectButton();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * Helper method to setup the connect button.
	 */
	private void setUpConnectButton() {
		
		Button button = (Button) findViewById(R.id.connectButton);
		
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this, Controller.class);
				
				EditText editTextIP = (EditText) findViewById(R.id.IPEditText);
				EditText editTextPort = (EditText) findViewById(R.id.portNumberEditText);
				
				intent.putExtra(IP_ADDR, editTextIP.getText().toString());
				intent.putExtra(PORT_NUM, editTextPort.getText().toString());
				startActivity(intent);
			}
			
		});
	}


}
