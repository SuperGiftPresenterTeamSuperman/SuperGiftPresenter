package com.supergiftpresenter;

import com.supergiftpresenter.gifts.Gift;
import com.supergiftpresenter.gifts.GiftsContainer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A fragment representing a single Gift detail screen. This fragment is either
 * contained in a {@link GiftListActivity} in two-pane mode (on tablets) or a
 * {@link GiftDetailActivity} on handsets.
 */
public class GiftDetailFragment extends Fragment {
	// The fragment argument representing the item ID that this fragment represents.
	public static final String ARG_ITEM_ID = "item_id";

	// The dummy content this fragment is presenting.
	private Gift mItem;

	/** Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes). */
	public GiftDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = GiftsContainer.giftsMap.get(getArguments().getString(ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_gift_detail, container, false);

		if (mItem != null) {
			((TextView) rootView.findViewById(R.id.gift_detail)).setText(mItem.getTitle());
		}

		return rootView;
	}
}
