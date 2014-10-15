package com.supergiftpresenter;

import android.content.ContentValues;
import android.content.Context;

public class DBUsers extends SQLiteDBHelper{

	public DBUsers(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public void addUser(String username, String password){
		ContentValues cv = new ContentValues();
		cv.put("username", username);
		cv.put("password",	password);
		this.db.insert("users", null, cv);
	}
}