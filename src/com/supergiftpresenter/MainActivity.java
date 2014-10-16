package com.supergiftpresenter;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

import com.supergiftpresenter.categories.CategoriesContainer;
import com.supergiftpresenter.categories.CategoriesGridViewAdapter;
import com.supergiftpresenter.categories.Category;
import com.supergiftpresenter.gifts.GiftsContainer;

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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ActionBarActivity {
	public GiftsContainer giftsContainer;
	public CategoriesContainer categoriesContainer;
	
	private String username;
	private Context context = this;
	private GridView grid;
	private TextView welcome;
	CategoriesGridViewAdapter adapter;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		categoriesContainer = CategoriesContainer.getInstance();
		giftsContainer = GiftsContainer.getInstance();
		
		Intent intent = getIntent();
	    Bundle bundle = intent.getExtras();
	    if(bundle != null){
	        username = bundle.getString("username");
	    } 
	    // TODO remove when validation for login is ok
	    if (username == null || username.isEmpty()) {
	    	username = "Unknown";
		}
	    
	    categoriesContainer.setUsername(username);
		Toast.makeText(context, categoriesContainer.getUsername() + " logged successfully", Toast.LENGTH_SHORT).show();
	
		welcome = (TextView) this.findViewById(R.id.welcome_sign);
		welcome.setText("Welcome to SuperGift " + username);
		
		grid = (GridView)this.findViewById(R.id.catalog_view);	
		ArrayList<Category> array = categoriesContainer.getAllCategories();
		adapter = new CategoriesGridViewAdapter(this, array);
		grid.setAdapter(adapter);
		
		grid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String catalogTitle = String.valueOf(adapter.getItem(position).getTitle());
				Toast.makeText(context, "Category " + catalogTitle + " selected" , Toast.LENGTH_SHORT).show();
				
				Category category = (Category)adapter.getItem(position);
				Intent intent = new Intent(MainActivity.this, GiftListActivity.class);
				categoriesContainer.setCurrentCategory(category);
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
}
