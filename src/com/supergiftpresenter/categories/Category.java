package com.supergiftpresenter.categories;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import android.R;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Category implements Serializable{
	private static final long serialVersionUID = 1L;
	private String title;
	private String details;
	
	public Category(String title, String details){
		this.setTitle(title);
		this.setDetails(details);
	}

	public String getTitle() {
		return title;
	}

	private void setTitle(String title) {
		this.title = title;
	}

	public String getDetails() {
		return details;
	}

	private void setDetails(String details) {
		this.details = details;
	}
}
