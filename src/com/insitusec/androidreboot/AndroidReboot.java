package com.insitusec.androidreboot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.PriorityQueue;
import java.util.Queue;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AndroidReboot extends Activity {
	private static final String DTAG = "AndroidReboot";
	private static final String baseDirectory = "/sys/kernel/debug";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_reboot);
		final Button btnLaunch = (Button) findViewById(R.id.button1);

		/* specific files that have caused crashes. (credit in comment)
		 * final Map<String, String> files;
		 * files.put("gnex-cdma", "/sys/kernel/debug/usb/ehci/ehci-omap.0/registers"); // @insitusec
		 * files.put("gnex", "/sys/kernel/debug/ion/1"); // @marcograss
		 * files.put("nexus7", "/sys/devices/tegradc.0/nvdps"); // @thuxnder
		 */
		btnLaunch.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// first loop through known files

				Queue<String> fileQueue = new PriorityQueue<String>();
				fileQueue.add(baseDirectory);
				do { 
					String curPath = fileQueue.remove();

					File file = new File( curPath );	
					File[] fileList = file.listFiles();

					for ( File f : fileList ) {
						if ( f.isDirectory() ) {
							fileQueue.add(f.getAbsolutePath());
						}

						else {
							FileInputStream ohCrapFis = null;
							try {
								Log.d(DTAG, String.format("Testing file: %s (take note if we crash)", f));
								byte[] buffer = new byte[1024];
								ohCrapFis = new FileInputStream(f);
								ohCrapFis.read(buffer);
							} catch (FileNotFoundException e) {

								// TODO Auto-generated catch block
								

							} catch (IOException e) {
								// TODO Auto-generated catch block
								//Log.d(DTAG, "File " + ohCrap.getAbsolutePath() + "had an IO Exception");
								//e.printStackTrace();
							}
						}

					} // for
				} while (!fileQueue.isEmpty());
				Toast.makeText(getApplicationContext(), "You're ok!", Toast.LENGTH_LONG).show();
			} // onClick
		}); // setOnClickListner
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_android_reboot, menu);
		return true;
	}

}
