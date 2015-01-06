package com.example.myapp;


import java.util.StringTokenizer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBWords extends SQLiteOpenHelper{

	private static final String DATABASE_NAME = "MYWORDS";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "WORDS";
	private static final String ID = "ID";
	private static final String VOCABULARY_ENG = "VOCABULARY_ENG";
	private static final String VOCABULARY_GER = "VOCABULARY_GER";
	private static final String CATEGORY = "CATEGORY";
	private static final String AUDIO_ENG = "AUDIO_ENG";
	private static final String AUDIO_GER = "AUDIO_GER";
	private static final String LAST_UPDATE = "LAST_UPDATE";
	
	public DBWords(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION); 
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("DBWords", "Creating table " + TABLE_NAME);
		String sqlCreateTable = "CREATE TABLE IF NOT EXISTS "
				+ TABLE_NAME
				+ "("
				+ ID
				+ " INT NOT NULL PRIMARY KEY UNIQUE, "
				+ VOCABULARY_ENG
				+ " VARCHAR(255) NOT NULL,"
				+ VOCABULARY_GER
				+ " VARCHAR(255) NOT NULL,"
				+ CATEGORY
				+ " INT DEFAULT NULL,"
				+ AUDIO_ENG
				+ " VARCHAR(255) DEFAULT NULL,"
				+ AUDIO_GER
				+ " VARCHAR(255) DEFAULT NULL,"
				+ LAST_UPDATE
				+ " DATETIME NOT NULL"
				+ ");";
		db.execSQL(sqlCreateTable);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
	    onCreate(db);
		
	}
	
	public Cursor getVocable(int id) {
		String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE ID=" + id +"";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		return cursor;
	}

	public boolean insert(String lines){
		int counter;
		String line,word;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues initialValues = new ContentValues();
		
		StringTokenizer stringTokenizerLines = new StringTokenizer(lines,"|");
		while (stringTokenizerLines.hasMoreTokens()) {
			line = stringTokenizerLines.nextToken();
			counter = 0;
			StringTokenizer stringTokenizerLine = new StringTokenizer(line,";");
			
			while (stringTokenizerLine.hasMoreTokens()) {
				word = stringTokenizerLine.nextToken();
				switch(counter){
					case 0: initialValues.put(ID,
							Integer.decode(word));
						break;
					case 1: initialValues.put(VOCABULARY_ENG,
							word);
						break;
					case 2: initialValues.put(VOCABULARY_GER,
							word);
						break;
					case 3: initialValues.put(CATEGORY,
							Integer.decode(word));
						break;
					case 4: initialValues.put(AUDIO_ENG,
							word);
						break;
					case 5: initialValues.put(AUDIO_GER,
							word);
						break;
					case 6: initialValues.put(LAST_UPDATE,
							word);
						break;
				}
				counter++;
			}
			if (initialValues.containsKey(ID)) {
			db.insertWithOnConflict(TABLE_NAME,	null,initialValues,SQLiteDatabase.CONFLICT_IGNORE);
			//	db.insert(DBWords.TABLE_NAME,null,initialValues);
			}
		} 
		/*
		SQLiteDatabase db = this.getWritableDatabase();
	    ContentValues contentValues = new ContentValues();
	      contentValues.put("ID", Integer.decode("10"));
	      contentValues.put("VOCABULARY_ENG", "sadad");
	      contentValues.put("VOCABULARY_GER", "saddsad");	
	      contentValues.put("CATEGORY", Integer.decode("4"));
	      contentValues.put("AUDIO_ENG", "saddad");
	      contentValues.put("AUDIO_GER", "sadad");
	      contentValues.put("LAST_UPDATE", "2014-12-28 23:21:40");
	      //db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES(1,'admin','admin',1,'admin','admin','2014-12-28 23:21:40');");
	      if (contentValues.containsKey(ID)) {
	    	  db.insertWithOnConflict(TABLE_NAME,null,contentValues,SQLiteDatabase.CONFLICT_IGNORE);
	      } */
		return true;
	}


}
	

	

