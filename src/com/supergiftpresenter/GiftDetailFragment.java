package com.supergiftpresenter;

import com.supergiftpresenter.categories.CategoriesContainer;
import com.supergiftpresenter.gifts.Gift;
import com.supergiftpresenter.gifts.GiftsContainer;

import android.R.bool;
import android.R.integer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.location.Location;
import android.media.Image;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A fragment representing a single Gift detail screen. This fragment is either
 * contained in a {@link GiftListActivity} in two-pane mode (on tablets) or a
 * {@link GiftDetailActivity} on handsets.
 */
public class GiftDetailFragment extends Fragment implements OnClickListener {
	// The fragment argument representing the item ID that this fragment represents.
	public static final String ARG_ITEM_ID = "item_id";

	// The dummy content this fragment is presenting.
	private GiftsContainer giftsContainer;
	private CategoriesContainer categoriesContainer;
	private Gift mItem;
	private TextView noInfoView;
	private TextView authorTextView;
	private TextView titleTextView;
	private TextView descriptionTextView;
	private TextView addressTextView;
	private TextView noImageView;
	private TextView noLocationView;
	private Button loadImage;
	private Button loadLocation;
	private Context context;
	private View rootView;

	/** Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes). */
	public GiftDetailFragment(Context context) {
		this.context = context;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		giftsContainer = GiftsContainer.getInstance();
		categoriesContainer = categoriesContainer.getInstance();

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			//mItem = GiftsContainer.giftsMap.get(getArguments().getString(ARG_ITEM_ID));
			mItem = giftsContainer.getCurrentGift();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_gift_detail, container, false);
		initializeViews(rootView);
		if (mItem != null) {
			// TODO change if has picture
			mItem.setPicture(categoriesContainer.getCurrentCategory().getPicture());
			Location location = mItem.getLocation();
			Bitmap image = mItem.getPicture();
			setViewsVisibility(true, image != null, location != null);
		} else {
			setViewsVisibility(false, false, false);
		}

		return rootView;
	}
	
	private void setViewsVisibility(boolean hasGift, boolean hasImage, boolean hasLocation) {
		int visible = View.VISIBLE;
		int gone = View.GONE;
		
		if (hasGift) {
			noInfoView.setVisibility(gone);
			
			authorTextView.setVisibility(visible);
			authorTextView.setText("Suggested by: " + mItem.getAuthor());
			
			titleTextView.setVisibility(visible);
			titleTextView.setText("Name : " + mItem.getTitle());
			
			descriptionTextView.setVisibility(visible);
			descriptionTextView.setText("Description: " + mItem.getDescription());
			
			String address = mItem.getAddress();
			addressTextView.setVisibility(visible);
			if (address == null) {
				addressTextView.setText(com.supergiftpresenter.R.string.details_no_address_text);
			} else {
				addressTextView.setText("Address: " + address);
			}
			
			if (hasImage) {
				noImageView.setVisibility(gone);
				loadImage.setVisibility(visible);
			} else {
				noImageView.setVisibility(visible);
				loadImage.setVisibility(gone);
			}
			
			if (hasLocation) {
				noLocationView.setVisibility(gone);
				loadLocation.setVisibility(visible);
			} else {
				noLocationView.setVisibility(visible);
				loadLocation.setVisibility(gone);
			}
		} else {
			noInfoView.setVisibility(visible);
			authorTextView.setVisibility(gone);
			titleTextView.setVisibility(gone);
			descriptionTextView.setVisibility(gone);
			addressTextView.setVisibility(gone);
			addressTextView.setText(com.supergiftpresenter.R.string.details_no_address_text);
			noImageView.setVisibility(gone);
			noLocationView.setVisibility(gone);
			loadImage.setVisibility(gone);
			loadLocation.setVisibility(gone);
		}
	}

	private void initializeViews(View parentView) {
		noInfoView = (TextView) parentView.findViewById(R.id.gift_detail_info);
		authorTextView = (TextView) parentView.findViewById(R.id.gift_detail_author);
		titleTextView = (TextView) parentView.findViewById(R.id.gift_detail_title);
		descriptionTextView = (TextView) parentView.findViewById(R.id.gift_detail_description);
		addressTextView = (TextView) parentView.findViewById(R.id.gift_detail_address);
		noImageView = (TextView) parentView.findViewById(R.id.details_no_image);
		noLocationView = (TextView) parentView.findViewById(R.id.details_no_location);
		loadImage = (Button) parentView.findViewById(R.id.detail_image_button);
		loadImage.setOnClickListener(this);
		loadLocation = (Button) parentView.findViewById(R.id.detail_location_button);
		loadLocation.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.detail_image_button) {
			Intent intent = new Intent(context, ImageTouchActivity.class);
			Toast.makeText(context, "show picture", Toast.LENGTH_SHORT).show();
			startActivity(intent);
		}
		if (v.getId() == R.id.detail_location_button) {
			Location locate = mItem.getLocation();
			
			Toast.makeText(context,
					String.format("Longitude: %f /n Latitude: %f", locate.getLongitude(), locate.getLatitude() ), 
					Toast.LENGTH_SHORT).show();
		}
	}	
}
