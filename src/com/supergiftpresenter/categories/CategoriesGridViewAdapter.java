package com.supergiftpresenter.categories;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class CategoriesGridViewAdapter extends BaseAdapter{

	public Category[] categoriesData;
	private Context mContext;
	
	public CategoriesGridViewAdapter(Context context, Category[] data) {
		this.mContext = context;
		this.categoriesData = data;
	}
	
	@Override
	public int getCount() {
		return categoriesData.length;
	}

	@Override
	public Category getItem(int position) {
		// TODO Auto-generated method stub
		return categoriesData[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		Category category = categoriesData[position];
		Log.d("CATEGORIES VIEW", category.getTitle());
		Bitmap categoryAvatar = this.getBitmapFromAssests(category.getTitle() + ".jpg");
		
		ImageView imageView;
        if (view == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(300,250));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) view;
        }

        imageView.setImageBitmap(categoryAvatar);
        return imageView;
	}
	
	private Bitmap getBitmapFromAssests(String filename) {
		AssetManager assetManager = mContext.getAssets();
		InputStream inputStream = null;
		
		try {
			inputStream = assetManager.open(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Bitmap fromAsset = BitmapFactory.decodeStream(inputStream);
		return fromAsset;
	}
}
