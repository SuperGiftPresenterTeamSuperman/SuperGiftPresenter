package com.supergiftpresenter;

import com.supergiftpresenter.categories.CategoriesContainer;
import com.supergiftpresenter.categories.Category;
import com.supergiftpresenter.gifts.GiftsContainer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * An activity representing a list of Gifts. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link GiftDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link GiftListFragment} and the item details (if present) is a
 * {@link GiftDetailFragment}.
 * <p>
 * This activity also implements the required {@link GiftListFragment.Callbacks}
 * interface to listen for item selections.
 */
public class GiftListActivity extends FragmentActivity implements
		GiftListFragment.Callbacks {

	private Context context = this;
	private Category currentCategory;
	private ImageView categoryAvatar;
	private TextView categoryTitle;
	public GiftsContainer giftsContainer;
	public CategoriesContainer categoriesContainer;
	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gift_list);
		categoriesContainer = CategoriesContainer.getInstance();
		giftsContainer = GiftsContainer.getInstance();
		currentCategory = categoriesContainer.getCurrentCategory();
		Toast.makeText(context, "Category " + currentCategory.getTitle() + "  selected", Toast.LENGTH_LONG).show();

		if (findViewById(R.id.gift_detail_container) != null) {
			mTwoPane = true;

			categoryTitle = (TextView) findViewById(R.id.category_title_input);
			categoryTitle.setText(" Category " + currentCategory.getTitle());
			categoryAvatar = (ImageView) findViewById(R.id.category_avatar);
			categoryAvatar.setImageBitmap(currentCategory.getPicture());
			((GiftListFragment) getSupportFragmentManager().findFragmentById(
					R.id.gift_list)).setActivateOnItemClick(true);
		}

		// TODO: If exposing deep links into your app, handle intents here.
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.gifts_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.add_gift_menu_button) {
			Toast.makeText(context, "Add new gift", Toast.LENGTH_SHORT).show();
			
			Intent intent = new Intent(GiftListActivity.this, AddGiftActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Callback method from {@link GiftListFragment.Callbacks} indicating that
	 * the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(GiftDetailFragment.ARG_ITEM_ID, id);
			GiftDetailFragment fragment = new GiftDetailFragment(context);
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.gift_detail_container, fragment).commit();

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this, GiftDetailActivity.class);
			detailIntent.putExtra(GiftDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}
}
