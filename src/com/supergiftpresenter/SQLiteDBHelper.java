package com.supergiftpresenter;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteDBHelper extends SQLiteOpenHelper {

	private final static String DB_NAME = "mDB";
	private final static int DB_VERSION = 1;
	protected SQLiteDatabase db;

	public SQLiteDBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		open();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table USERS (_id integer primary key autoincrement, " + "USERNAME text not null, " + "PASSWORD text not null);");
		Log.d("D1", "CREATED");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public void open() throws SQLException {
		db = getWritableDatabase();
		
	}

	public void close() {
		db.close();
	}
}