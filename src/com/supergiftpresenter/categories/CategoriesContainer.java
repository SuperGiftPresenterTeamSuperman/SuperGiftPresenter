package com.supergiftpresenter.categories;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class CategoriesContainer {
	private static CategoriesContainer instance;
	private String username;
	private Category category;
	
//    private Hashtable<String, Bitmap> giftPictures = new Hashtable<String, Bitmap>();
//    private Hashtable<String, Bitmap> categoryPictures = new Hashtable<String, Bitmap>();
//	private Hashtable<Category, List<Gift>> allGifts = new Hashtable<Category, List<Gift>>();
	
	private static HashSet<Category> categoriesList = new HashSet<Category>();
	
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
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
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
    
	public Category[] getAllCategories() {
    	return (Category[])categoriesList.toArray(new Category[categoriesList.size()]);
    }

	private void GenerateAllCategories() {
		categoriesList = new HashSet<Category>(Arrays.asList(
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
