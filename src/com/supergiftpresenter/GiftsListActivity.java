package com.supergiftpresenter;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class GiftsListActivity extends ActionBarActivity {
	private Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gifts_list);
		
		Toast.makeText(context, "All activities listed", Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gifts_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.add_gift_menu_button) {
			Toast.makeText(context, "Add new gift", Toast.LENGTH_SHORT).show();
			// TODO implement add new gift
//			Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
