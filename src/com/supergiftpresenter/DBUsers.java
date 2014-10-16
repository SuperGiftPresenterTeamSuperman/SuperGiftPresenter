package com.supergiftpresenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

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
	
	public String getPassword(String userName)
	{
		Cursor cursor=db.query("USERS", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
        	cursor.close();
        	return "NOT EXIST";
        }
	    cursor.moveToFirst();
		String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
		cursor.close();
		return password;				
	}
	
	public Boolean userExists(String username){
		 Cursor cursor = db.rawQuery("SELECT 1 FROM "+ "USERS"+" WHERE "+ "USERNAME" +"=?", new String[] {username});
		    boolean exists = cursor.moveToFirst();
		    cursor.close();
		    return exists;
	}
	
	public void  updateEntry(String userName,String password)
	{
		// Define the updated row content.
		ContentValues updatedValues = new ContentValues();
		// Assign values for each row.
		updatedValues.put("USERNAME", userName);
		updatedValues.put("PASSWORD",password);

        String where="USERNAME = ?";
	    db.update("USERS",updatedValues, where, new String[]{userName});			   
	}	
}