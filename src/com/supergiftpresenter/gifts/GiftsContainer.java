package com.supergiftpresenter.gifts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import android.R.integer;
import android.R.string;
import android.graphics.Bitmap;

import com.supergiftpresenter.categories.CategoriesContainer;
import com.supergiftpresenter.categories.Category;


public class GiftsContainer {
	private static GiftsContainer instance;

	private CategoriesContainer categoriesContainer;
	private RandomGiftGenerator generator;
	private String username;
	private Category category;
	
//    private Hashtable<String, Bitmap> giftPictures = new Hashtable<String, Bitmap>();
//    private Hashtable<String, Bitmap> categoryPictures = new Hashtable<String, Bitmap>();
//	private Hashtable<Category, List<Gift>> allGifts = new Hashtable<Category, List<Gift>>();
	private static HashSet<Gift> giftsList = new HashSet<Gift>();
	private static final int GIFTS_COUNT = 50;
	
	private GiftsContainer() {
		categoriesContainer = CategoriesContainer.getInstance();
		generator = RandomGiftGenerator.getInstance();
		GenerateAllGifts();
	}

	public static GiftsContainer getInstance() {
        if (instance == null) {
            instance = new GiftsContainer();
        }
        
        return instance;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	// GIFTS
	public String addGift(Gift gift) {
		if (giftsList.contains(gift)) {
			return "Same gift already exists";
		}
		
		giftsList.add(gift);
		return "OK";
		
	}
    public String removeGift(Gift gift) {
    	if (!giftsList.contains(gift)) {
			return "Gift does not exsist";
		}
    	
    	giftsList.remove(gift);
    	return "OK";
    }
    public Gift[] getAllGifts() {
    	return (Gift[])giftsList.toArray(new Gift[giftsList.size()]);
    }
    public Gift[] getAllGiftsInCategory(Category category) {
    	ArrayList<Gift> list = new ArrayList<Gift>();
    	for (Gift giftItem : giftsList) {
			if (giftItem.getCategory() == category) {
				list.add(giftItem);
			}
		}
    	
    	return (Gift[])list.toArray(new Gift[list.size()]);
    }
    public boolean removeAllGiftsInCategory(Category category) {
    	ArrayList<Gift> giftsToRemove = new ArrayList<Gift>();
    	for (Gift giftItem : giftsList) {
			if (giftItem.getCategory() == category) {
				giftsToRemove.add(giftItem);
			}
		}
    	return giftsList.removeAll(giftsToRemove);
    }
    
    // TODO IMPLEMENT GIFTS PICTURES
//    public void addGiftPicture(String giftId, Bitmap image) {
//        this.giftPictures.put(giftId, image);
//    }
//    public Bitmap getGiftPictureById(String giftId) {
//        return this.giftPictures.get(giftId);
//    }
	
	// STATIC GENERATION OF DATA
	// TODO IMPLEMENT DATABASE USEAGE FOR THIS
	private void GenerateAllGifts() {
		Random randomGen = new Random();
		Category[] categories = categoriesContainer.getAllCategories();
		int categoriesCount = categories.length;
		for (int i = 0; i < GIFTS_COUNT; i++) {
			int index = randomGen.nextInt(categoriesCount);
			Category category = categories[index];
			Gift gift = generator.generateGift(category);
			giftsList.add(gift);
		}
	}
}
