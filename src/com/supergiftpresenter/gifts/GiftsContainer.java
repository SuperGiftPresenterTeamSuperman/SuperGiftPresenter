package com.supergiftpresenter.gifts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import android.graphics.BitmapFactory;

import com.supergiftpresenter.R;
import com.supergiftpresenter.categories.CategoriesContainer;
import com.supergiftpresenter.categories.Category;


public class GiftsContainer {
	private static GiftsContainer instance;

	private CategoriesContainer categoriesContainer;
	private RandomGiftGenerator generator;
	private String username;
	private Gift currentGift;
	
//    private Hashtable<String, Bitmap> giftPictures = new Hashtable<String, Bitmap>();
//    private Hashtable<String, Bitmap> categoryPictures = new Hashtable<String, Bitmap>();
	//private Hashtable<String, Gift> allGifts = new Hashtable<String, Gift>();
	public static Map<String, Gift> giftsMap = new HashMap<String, Gift>();
	private static ArrayList<Gift> giftsList = new ArrayList<Gift>();
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
	
	public Gift getCurrentGift() {
		return currentGift;
	}

	public void setCurrentGift(Gift currentGift) {
		this.currentGift = currentGift;
	}
	
	// GIFTS
	public String addGift(Gift gift) {
		if (giftsList.contains(gift)) {
			return "Same gift already exists";
		}
		
		giftsList.add(gift);
		giftsMap.put(gift.getId(), gift);
		return "OK";
		
	}
    public String removeGift(Gift gift) {
    	if (!giftsList.contains(gift)) {
			return "Gift does not exsist";
		}
    	
    	giftsList.remove(gift);
    	return "OK";
    }
    
    public Gift getGiftById(String id) {
    	return giftsMap.get(id);
    }
    
    public ArrayList<Gift> getGifts () {
    	return giftsList;
    }
    
    public Gift[] getAllGifts() {
    	return (Gift[])giftsList.toArray(new Gift[giftsList.size()]);
    }
    
    public ArrayList<Gift> getAllGiftsInCategory(Category category) {
    	ArrayList<Gift> list = new ArrayList<Gift>();
    	for (Gift giftItem : giftsList) {
			if (giftItem.getCategory() == category) {
				list.add(giftItem);
			}
		}
    	
    	return list;
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
		ArrayList<Category> categories = categoriesContainer.getAllCategories();
		int categoriesCount = categories.size();
		for (int i = 0; i < GIFTS_COUNT; i++) {
			int index = randomGen.nextInt(categoriesCount);
			Category category = categories.get(index);
			Gift gift = generator.generateGift(category);
			
			giftsList.add(gift);
			// TODO refactor
			giftsMap.put(gift.getId(), gift);
		}
	}
}
