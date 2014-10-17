package com.supergiftpresenter;

import com.supergiftpresenter.categories.CategoriesContainer;
import com.supergiftpresenter.categories.Category;
import com.supergiftpresenter.gifts.Gift;
import com.supergiftpresenter.gifts.GiftsContainer;

import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class AddGiftActivity extends ActionBarActivity implements OnClickListener{
	private Context context = this;
	private EditText inputGiftTitle;
	private EditText inputGiftDescription;
	private EditText inputGiftAddress;
	private Button addImageButton;
	private Button addLocationButton;
	private Button addNewGift;
	private GiftsContainer giftsContainer;
	private CategoriesContainer categoriesContainer;
	
	private LocationManager locationManager = null;
	private LocationListener locationListener = null;
	private TextView editLocation = null;
	private ProgressBar progressBar = null;
	private static final String TAG = "Debug";
	private Boolean flag = false;
	private Location currentLocation = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_gift);
		giftsContainer = GiftsContainer.getInstance();
		categoriesContainer = CategoriesContainer.getInstance();
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		inputGiftTitle = (EditText)findViewById(R.id.add_gift_title);
		inputGiftDescription = (EditText)findViewById(R.id.add_gift_description);
		inputGiftAddress = (EditText)findViewById(R.id.add_gift_address);
		
		addImageButton = (Button)findViewById(R.id.add_image_button);
		addLocationButton = (Button)findViewById(R.id.add_location_button);
		addNewGift = (Button)findViewById(R.id.add_new_gift_button);
		
		progressBar = (ProgressBar) findViewById(R.id.get_location_progress);
		progressBar.setVisibility(View.INVISIBLE);

		editLocation = (TextView) findViewById(R.id.location_info);
		
		addImageButton.setOnClickListener(this);
		addLocationButton.setOnClickListener(this);
		addNewGift.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_gift, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.return_button) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		int viewId = v.getId();
		
		switch (viewId) {
			case R.id.add_new_gift_button:
				// TODO validate imputs
				String giftTitle = inputGiftTitle.getText().toString();
				String giftDescription = inputGiftDescription.getText().toString();
				String giftAddress = inputGiftAddress.getText().toString();
				String giftAuthor = categoriesContainer.getUsername();
				
				if (validateInput(giftTitle, "Title") && validateInput(giftDescription, "Description") && validateInput(giftAuthor, "Author")) {
					Category currentCategory = categoriesContainer.getCurrentCategory();
					Gift gift = new Gift(giftTitle, giftDescription, giftAuthor, currentCategory);
					if (currentLocation != null) {
						gift.setLocation(currentLocation);
					}
					
					giftsContainer.addGift(gift);
					Intent intent = new Intent(this, MainActivity.class);
					this.sendMessage("Gift added");
					this.Notify("SuperGift", "A new message has been added", 100);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.putExtra("username", giftAuthor);
					startActivity(intent);
					finish();
				}		
				
				return;
			case R.id.add_image_button:
				// TODO implement getting image
				this.sendMessage("Image added");
				return;
			case R.id.add_location_button:
				flag = displayGpsStatus();
				if (flag) {
					Log.v(TAG, "getLocationCoordinates");
					editLocation.setText("Please!! move your device to"
							+ " see the changes in coordinates." + "\n Wait..");
					progressBar.setVisibility(View.VISIBLE);
					locationListener = new MyLocationListener();

					locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
					this.sendMessage("Location added");
				} else {
					alertbox("Gps Status!!", "Your GPS is: OFF");
				}
				return;
			default: return;
		}
	}
	
	private void sendMessage(String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
	
	private void Notify(String title, String message, int id) {
		NotificationCompat.Builder mBuilder =
		        new NotificationCompat.Builder(this)
		        .setSmallIcon(R.drawable.supergift_icon)
		        .setContentTitle(title)
		        .setContentText(message);
		NotificationManager mNotificationManager =
			    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			// mId allows you to update the notification later on.
		mNotificationManager.notify(id, mBuilder.build());
	}
	
	private boolean validateInput(String input, String attribute) {
		if (input == null || input == "") {
			sendMessage(attribute + " is required");
			return false;
		} else if (input.trim().length() < 4) {
			sendMessage(attribute + " should be atleast 3 symbols");
			return false;
		} 
		
		return true;
	}
	
	private Boolean displayGpsStatus() {
		if (locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER )) {
			return true;
		} else {
			return false;
		}
	}
	
	protected void alertbox(String title, String mymessage) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Your Device's GPS is Disable")
				.setCancelable(false)
				.setTitle("** Gps Status **")
				.setPositiveButton("Gps On",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Intent myIntent = new Intent(
										Settings.ACTION_SECURITY_SETTINGS);
								startActivity(myIntent);
								dialog.cancel();
							}
						})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// cancel the dialog box
								dialog.cancel();
							}
						});
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	private class MyLocationListener implements LocationListener {
		@Override
		public void onLocationChanged(Location loc) {
			currentLocation = loc;
			editLocation.setText("");
			progressBar.setVisibility(View.INVISIBLE);
			Toast.makeText(
					getBaseContext(),
					"Location change: Latitude " + loc.getLatitude() + 
					" Longitude " + loc.getLongitude(), Toast.LENGTH_SHORT).show();
			String longitude = "Longitude: " + loc.getLongitude();
			Log.v(TAG, longitude);
			String latitude = "Latitude: " + loc.getLatitude();
			Log.v(TAG, latitude);

			String s = longitude + "\n" + latitude	+ "\n";
			editLocation.setText(s);
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
		}
	}
}
