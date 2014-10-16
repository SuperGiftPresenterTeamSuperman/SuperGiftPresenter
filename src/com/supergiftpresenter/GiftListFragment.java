package com.supergiftpresenter;

import com.supergiftpresenter.categories.CategoriesContainer;
import com.supergiftpresenter.gifts.Gift;
import com.supergiftpresenter.gifts.GiftsContainer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GiftListFragment extends ListFragment {

	private GiftsContainer giftsContainer;
	private CategoriesContainer categoriesContainer;
	
	private static final String STATE_ACTIVATED_POSITION = "activated_position";

	private Callbacks mCallbacks = sGiftCallbacks;

	private int mActivatedPosition = ListView.INVALID_POSITION;

	public interface Callbacks {
		public void onItemSelected(String id);
	}

	private static Callbacks sGiftCallbacks = new Callbacks() {
		@Override
		public void onItemSelected(String id) {
		}
	};

	public GiftListFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		giftsContainer = GiftsContainer.getInstance();
		categoriesContainer = CategoriesContainer.getInstance();
		
		// TODO: replace with a real list adapter.
		setListAdapter(new ArrayAdapter<Gift>(getActivity(),
				android.R.layout.simple_list_item_activated_1,
				android.R.id.text1, giftsContainer.getAllGiftsInCategory(categoriesContainer.getCurrentCategory())));
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		// Restore the previously serialized activated item position.
		if (savedInstanceState != null
				&& savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
			setActivatedPosition(savedInstanceState
					.getInt(STATE_ACTIVATED_POSITION));
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// Activities containing this fragment must implement its callbacks.
		if (!(activity instanceof Callbacks)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}

		mCallbacks = (Callbacks) activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();

		// Reset the active callbacks interface to the gift implementation.
		mCallbacks = sGiftCallbacks;
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {
		super.onListItemClick(listView, view, position, id);

		// Notify the active callbacks interface (the activity, if the
		// fragment is attached to one) that an item has been selected.
		String selectedGiftId = giftsContainer.getAllGiftsInCategory(categoriesContainer.getCurrentCategory()).get(position).getId();
		mCallbacks.onItemSelected(selectedGiftId);
		// TODO check if needed
		giftsContainer.setCurrentGift(giftsContainer.getGiftById(selectedGiftId));
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mActivatedPosition != ListView.INVALID_POSITION) {
			// Serialize and persist the activated item position.
			outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
		}
	}

	public void setActivateOnItemClick(boolean activateOnItemClick) {
		// When setting CHOICE_MODE_SINGLE, ListView will automatically
		// give items the 'activated' state when touched.
		getListView().setChoiceMode(
				activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
						: ListView.CHOICE_MODE_NONE);
	}

	private void setActivatedPosition(int position) {
		if (position == ListView.INVALID_POSITION) {
			getListView().setItemChecked(mActivatedPosition, false);
		} else {
			getListView().setItemChecked(position, true);
		}

		mActivatedPosition = position;
	}
}
