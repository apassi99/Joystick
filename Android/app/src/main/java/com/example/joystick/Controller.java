package com.example.joystick;


import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * Controller activity that defines the Joystick
 * controller. It initiates a TCP/IP connection
 * given the IP and Port Number by the user. It
 * provides four options (UP, DOWN, LEFT, RIGHT).
 *
 * @author Arjun Passi (apassi)
 */
public class Controller extends Activity {

    /* Reference to the Socket communicating with Wifly chip*/
	private Socket socket = new Socket();

    /* Reference to the connection thread responsible for establishing
     * the TCP/IP connection with Wifly chip */
    private Thread connectionThread;

    /* Tag for logging debug/error statements */
	private String TAG = "ControllerActivity";

    /* Timeout in milliseconds to wait for connection thread to finish. */
    private long TIMEOUT = 10l;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_controller);

        String IP_ADDR = getIntent().getExtras().getString(MainActivity.IP_ADDR);
        int portNumber = Integer.parseInt(getIntent().getExtras().getString(MainActivity.PORT_NUM));
		
		// Set up directional buttons
        setUpButton("UP", R.id.upButton);
        setUpButton("DOWN", R.id.downButton);
        setUpButton("LEFT", R.id.leftButton);
        setUpButton("RIGHT", R.id.rightButton);
        setUpConnection(IP_ADDR, portNumber);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
        super.onDestroy();
		try {
            // Not thread safe (Runnable could be calling connect)
			socket.close();
            connectionThread.join(TIMEOUT);
			Log.d(TAG, "Connection is closed.");
		} catch (IOException e) {
			Log.e(TAG, "Error occured while closing the connection.");
			e.printStackTrace();
		} catch (InterruptedException e) {
            Log.e(TAG, "Error occured while closing the connection.");
            e.printStackTrace();
        }
	}

    /**
     * Helper method to set up the connection.
     *
     * @param IP_ADDR - IP address
     * @param portNumber - Port number
     */
	private void setUpConnection(final String IP_ADDR, final int portNumber) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {

                    // Not thread safe (Destroy could be calling close at the same time)
                    socket.connect((new InetSocketAddress(
                            InetAddress.getByName(IP_ADDR), portNumber)));
                    Log.d(TAG, "Connection Successfull");

                } catch (UnknownHostException e) {
                    Log.e(TAG, "Connection Failed");
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.e(TAG, "Connection Failed");
                    e.printStackTrace();
                }
            }
        };

        connectionThread = new Thread(runnable);
        connectionThread.start();
	}

    /**
     * Helper method to setup the UP, DOWN, LEFT, RIGHT buttons.
     *
     * @param sendValue - string value to send when the button is pressed
     * @param ID - ID to find the corresponding button.
     */
    private void setUpButton(final String sendValue, int ID) {
        final Button button = (Button)findViewById(ID);

        button.setEnabled(true);
        button.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction() == MotionEvent.ACTION_DOWN) {
                    button.setPressed(true);
                    sendData(sendValue);
                }

                else if (arg1.getAction() == MotionEvent.ACTION_UP) {
                    button.setPressed(false);
                }

                else if (button.isPressed()) {
                    sendData(sendValue);
                }

                return true;
            }
        });
    }

    /**
     * Helper method to write the given data on the TCP/IP
     * socket. A Toast is created on the android screen if
     * the socket is connected.
     *
     * @param data - string value to be sent over the connection.
     */
	private void sendData(String data) {

        if (!socket.isConnected() || socket.isClosed()) {
            Toast.makeText(getApplicationContext(), "Joystick is disconnected...",
                    Toast.LENGTH_LONG).show();
            return;
        }

		try {
			OutputStream outputStream = socket.getOutputStream();

            byte [] arr = data.getBytes();
            byte [] cpy = ByteBuffer.allocate(arr.length+1).array();

            for (int i = 0; i < arr.length; i++) {
                cpy[i] = arr[i];
            }

            //Terminating the string with null character
            cpy[arr.length] = 0;

            outputStream.write(cpy);
			outputStream.flush();

            Log.d(TAG, "Sending data  " + data);
		} catch (IOException e) {
            Log.e(TAG, "IOException while sending data "
                    + e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
            Log.e(TAG, "NullPointerException while sending data "
                    + e.getMessage());
		}
	}
	
}
