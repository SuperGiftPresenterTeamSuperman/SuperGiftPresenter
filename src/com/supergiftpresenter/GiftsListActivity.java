package com.supergiftpresenter;

import com.supergiftpresenter.categories.CategoriesContainer;
import com.supergiftpresenter.categories.Category;
import com.supergiftpresenter.gifts.GiftsContainer;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GiftsListActivity extends ActionBarActivity {
	private Context context = this;
	private Category currentCategory;
	private ImageView categoryAvatar;
	private TextView categoryTitle;
	
	public GiftsContainer giftsContainer;
	public CategoriesContainer categoriesContainer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gifts_list);
		categoriesContainer = CategoriesContainer.getInstance();
		giftsContainer = GiftsContainer.getInstance();
		currentCategory = categoriesContainer.getCategory();
		categoryTitle = (TextView) findViewById(R.id.category_title_input);
		categoryTitle.setText(" Category " + currentCategory.getTitle());
		categoryAvatar = (ImageView) findViewById(R.id.category_avatar);
		categoryAvatar.setImageBitmap(currentCategory.getPicture());
		Toast.makeText(context, currentCategory.getTitle() + "  "+ categoriesContainer.getUsername(), Toast.LENGTH_LONG).show();
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
