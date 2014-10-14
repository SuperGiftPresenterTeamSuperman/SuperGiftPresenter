package com.supergiftpresenter;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import com.supergiftpresenter.categories.CategoriesGridViewAdapter;
import com.supergiftpresenter.categories.Category;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ActionBarActivity {

	private String username;
	private Context context = this;
	private GridView grid;
	CategoriesGridViewAdapter adapter;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent intent = getIntent();
	    Bundle bundle = intent.getExtras();
	    if(bundle != null){
	        username = bundle.getString("username");
	    } 

	    if (username == null || username.isEmpty()) {
	    	username = "Unknown";
		}
	    
		Toast.makeText(context, username + " logged successfully", Toast.LENGTH_SHORT).show();
	
		grid = (GridView)this.findViewById(R.id.catalog_view);	
		adapter = new CategoriesGridViewAdapter(this, getAllCategories());
		grid.setAdapter(adapter);
		
		grid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String catalogTitle = String.valueOf(adapter.getItem(position).getTitle());
				Toast.makeText(context, "Category " + catalogTitle + " selected" , Toast.LENGTH_SHORT).show();
				
				Category category = (Category)adapter.getItem(position);
				Intent intent = new Intent(MainActivity.this, GiftsListActivity.class);
				intent.putExtra("category", (Serializable)category);
				startActivity(intent);
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
		if (id == R.id.sign_out) {
			Toast.makeText(context, username + " logged out successfully", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(MainActivity.this, LoginActivity.class);
			startActivity(intent);
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public Category[] getAllCategories(){
		return new Category[] {
			new Category("anniversary", "Being together for a long time"),
			new Category("birthday", "The day you were brought to life"),
			new Category("christening", "Introdusing a newborn to the lord"),
			new Category("christmas", "The birthday of the lord's son"),
			new Category("engagement", "Celibration event when someone decides to marry"),
			new Category("graduation", "Completion of some educational degree"),
			new Category("nameday", "Party in the name of the named ONE"),
			new Category("newyear", "The beginning of a new year celebration"),
			new Category("party", "Casual implovised party"),
			new Category("promotion", "Advancement of someone's rank or position"),
			new Category("wedding", "Two people binding in marriage")
	    };  
	}
}
