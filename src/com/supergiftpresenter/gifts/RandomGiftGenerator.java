package com.supergiftpresenter.gifts;

import java.util.Random;

import com.supergiftpresenter.categories.Category;

import android.R.integer;

public class RandomGiftGenerator {
	private static RandomGiftGenerator instance;
	private Random generator;
	private static String allPossibleChars = "qwertyuiop asdfghjklz- xcvbnmAQWER TYUIOPSDF GHJKLZ XCVBNM";
	
	private RandomGiftGenerator() {
		generator = new Random();
		
	}

	public static RandomGiftGenerator getInstance() {
        if (instance == null) {
            instance = new RandomGiftGenerator();
        }
        
        return instance;
    }
	
	public Gift generateGift(Category category) {
		String title = generateString(10);
		String description = generateString(35);;
		String author = generateString(10);;
		return new Gift(title, description, author, category);
	}
	
	private String generateString(int length) {
		char[] text = new char[length];
		int charsLength = allPossibleChars.length();
	    for (int i = 0; i < length; i++)
	    {
	        text[i] = allPossibleChars.charAt(generator.nextInt(charsLength));
	    }
	    
	    return new String(text);
	}
	
}
