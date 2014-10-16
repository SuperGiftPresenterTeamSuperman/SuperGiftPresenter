package com.supergiftpresenter.categories;

import java.util.ArrayList;
import java.util.Arrays;

public class CategoriesContainer {
	private static CategoriesContainer instance;
	private String username;
	private Category currentCategory;
	
//    private Hashtable<String, Bitmap> giftPictures = new Hashtable<String, Bitmap>();
//    private Hashtable<String, Bitmap> categoryPictures = new Hashtable<String, Bitmap>();
//	private Hashtable<Category, List<Gift>> allGifts = new Hashtable<Category, List<Gift>>();
	
	private static ArrayList<Category> categoriesList = new ArrayList<Category>();
	
	private CategoriesContainer() {
		GenerateAllCategories();
	}

	public static CategoriesContainer getInstance() {
        if (instance == null) {
            instance = new CategoriesContainer();
        }
        
        return instance;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}    

    // CATEGORIES
	public Category getCurrentCategory() {
		return this.currentCategory;
	}
	
	public void setCurrentCategory(Category category) {
		this.currentCategory = category;
	}
	
	public Category getCategoryById(String id) {
		for (Category item : categoriesList) {
			if (item.getId() == id) {
				return item;
			}
		}
		
		return null;
	}
	
	public String addCategory(Category category) {
		if (categoriesList.contains(category)) {
			return "Same category already exists";
		}
		
		categoriesList.add(category);
		return "OK";
		
	}
    public String removeCategory(Category category) {
    	if (!categoriesList.contains(category)) {
			return "Category does not exsist";
		}
    	
    	categoriesList.remove(category);
    	return "OK";
    }
    
	public ArrayList<Category> getAllCategories() {
    	return this.categoriesList;
    }
	
	public Category[] getCategoriesAsArray() {
    	return (Category[])categoriesList.toArray(new Category[categoriesList.size()]);
    }

	private void GenerateAllCategories() {
		categoriesList = new ArrayList<Category>(Arrays.asList(
			new Category("anniversary"),
			new Category("birthday"),
			new Category("christening"),
			new Category("christmas"),
			new Category("engagement"),
			new Category("graduation"),
			new Category("nameday"),
			new Category("newyear"),
			new Category("party"),
			new Category("promotion"),
			new Category("wedding"),
			new Category("other")
	    ));  
	}
}
