package com.supergiftpresenter.gifts;

import com.supergiftpresenter.categories.Category;

import android.R.integer;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.location.Location;

@SuppressLint("NewApi")
public class Gift {
	private static long picturesCount = 0;
	private String id;
	private String title;
	private String description;
	private String author;
	private Category category;
	private Bitmap picture;
	private Location location;
	
	public Gift(String title, String description, String author, Category category) {
		this.setId(String.valueOf(picturesCount));
		picturesCount++;
		this.setTitle(title);
		this.setDescription(description);
		this.setAuthor(author);
		this.setCategory(category);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title == null || title.isEmpty()) {
			title = "Not specified";
		}
		
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description == null || description.isEmpty()) {
			description = "No description provided";
		}
		
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		if (author == null || author.isEmpty()) {
			author = "Anonymous";
		}
		
		this.author = author;
	}

	public Bitmap getPicture() {
		return picture;
	}

	public void setPicture(Bitmap picture) {
		this.picture = picture;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		if (category == null) {
			category = new Category("other");
		}
		
		this.category = category;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String toString() {
		return String.format(" Id: %9s /n Title: %s /n Description: %s /n ", this.getId(), this.getTitle(), this.getDescription());
	}
}
