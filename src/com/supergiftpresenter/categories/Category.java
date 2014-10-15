package com.supergiftpresenter.categories;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import android.R;
import android.R.string;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Category implements Serializable{
	private static long categoriesCount = 0;
	
	private String id;
	private String title;
	private Bitmap picture;
	
	public Category(String title){
		this.setId(String.valueOf(categoriesCount));
		categoriesCount++;
		this.setTitle(title);
	}

	public String getTitle() {
		return title;
	}

	private void setTitle(String title) {
		this.title = title;
	}

	public Bitmap getPicture() {
		return picture;
	}

	public void setPicture(Bitmap picture) {
		this.picture = picture;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
