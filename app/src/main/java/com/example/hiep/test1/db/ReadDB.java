package com.example.hiep.test1.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class ReadDB extends SQLiteOpenHelper {
	
	static final String SMSObjectReadDb = "chuctet.db";
	
	private static String DB_PATH;
	
	private final Context myContext;


	public static final int DATABASE_VERSION = 1;
	SQLiteDatabase mDatabase;
	
	public ReadDB(Context context) {
		super(context, SMSObjectReadDb, null, DATABASE_VERSION);
		this.myContext = context;
		DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
	}
	
	public void createDatabase() throws IOException {
		boolean dbExist = checkDatabase();
		if (dbExist) {
			
		} else {
			this.getReadableDatabase();
		}
		try {
			copyDatabase();
		} catch (IOException e) {

		}
	}


	
	private boolean checkDatabase() {
		// TODO Auto-generated method stub
		SQLiteDatabase checkDB = null;
		try {
			String myPath = DB_PATH + SMSObjectReadDb;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READWRITE);
		} catch (SQLiteException e) {

		}
		if (checkDB != null) {
			checkDB.close();
		}
		return false;
	}
	
	private void copyDatabase() throws IOException {
		// TODO Auto-generated method stub
		InputStream myInput = myContext.getAssets().open(SMSObjectReadDb);
		String outFile = DB_PATH + SMSObjectReadDb;
		OutputStream myOutput = new FileOutputStream(outFile);
		byte[] buffer = new byte[1024];
		int lenght;
		while ((lenght = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, lenght);
		}
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}
	public void openDatabase() throws SQLiteException {
		String myPath = DB_PATH + SMSObjectReadDb;
		mDatabase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READWRITE);
	}
	
	public synchronized void closeDatabase() {
		if (mDatabase != null)
			mDatabase.close();
		super.close();
	}
	
	//Truy van tro
		public Cursor getCursorQuery(String table, String[] columns,
                                     String selection, String[] selectionArgs, String groupBy,
                                     String having, String orderBy) {
			return mDatabase.query(table, columns, selection, selectionArgs,
					groupBy, having, orderBy);
		}
	//Lấy list card
	
	public ArrayList<CategoryObject> getListTheloai(){
			
			ArrayList<CategoryObject> loaiCategoryObject = new ArrayList<CategoryObject>();
			Cursor smsCursor = getCursorQuery("tbl_Category", null, null, null, "id", null, null);
			if (smsCursor != null){
				while (smsCursor.moveToNext()) {
					loaiCategoryObject.add(new CategoryObject(smsCursor.getInt(smsCursor.getColumnIndex("id")), smsCursor.getString(smsCursor.getColumnIndex("name")), smsCursor.getString(smsCursor.getColumnIndex("image_name"))));
				}
			}
			
			return loaiCategoryObject;
		}
	
	// Lấy list sms theo category ID
	public ArrayList<SMSObject> getlistSMSObject(int cats_id){
		
		ArrayList<SMSObject> lstSMSObject = new ArrayList<SMSObject>();
		Cursor smsCursor = getCursorQuery("tbl_template", null, "category_id = " + cats_id,null,null, null, null);
		if (smsCursor != null){
			while (smsCursor.moveToNext()) {
				lstSMSObject.add(new SMSObject(smsCursor.getInt(smsCursor.getColumnIndex("id")),  
						smsCursor.getString(smsCursor.getColumnIndex("content")), 
						smsCursor.getInt(smsCursor.getColumnIndex("favorited")), 
						smsCursor.getString(smsCursor.getColumnIndex("content_non_accent")) , 
						smsCursor.getString(smsCursor.getColumnIndex("category_id")),smsCursor.getInt(smsCursor.getColumnIndex("populated"))));
			}
		}
		return lstSMSObject;
	}
	
	public ArrayList<SMSObject> getlistSMSObjectPopulate(){
			
			ArrayList<SMSObject> lstSMSObject = new ArrayList<SMSObject>();
			Cursor smsCursor = getCursorQuery("tbl_template", null, "populated = " + 1,null,null, null, null);
			if (smsCursor != null){
				while (smsCursor.moveToNext()) {
					lstSMSObject.add(new SMSObject(smsCursor.getInt(smsCursor.getColumnIndex("id")),  
							smsCursor.getString(smsCursor.getColumnIndex("content")), 
							smsCursor.getInt(smsCursor.getColumnIndex("favorited")), 
							smsCursor.getString(smsCursor.getColumnIndex("content_non_accent")) , 
							smsCursor.getString(smsCursor.getColumnIndex("category_id")),smsCursor.getInt(smsCursor.getColumnIndex("populated"))));
				}
			}
			return lstSMSObject;
		}
	
	public ArrayList<SMSObject> getlistSMSObjectFavorite(){
		
		ArrayList<SMSObject> lstSMSObject = new ArrayList<SMSObject>();
		Cursor smsCursor = getCursorQuery("tbl_template", null, "favorited = " + 1,null,null, null, null);
		if (smsCursor != null){
			while (smsCursor.moveToNext()) {
				lstSMSObject.add(new SMSObject(smsCursor.getInt(smsCursor.getColumnIndex("id")),  
						smsCursor.getString(smsCursor.getColumnIndex("content")), 
						smsCursor.getInt(smsCursor.getColumnIndex("favorited")), 
						smsCursor.getString(smsCursor.getColumnIndex("content_non_accent")) , 
						smsCursor.getString(smsCursor.getColumnIndex("category_id")),smsCursor.getInt(smsCursor.getColumnIndex("populated"))));
			}
		}
		return lstSMSObject;
	}
	//GetTile
	public String getTitleCates(int cates_id){
		
		String lstSMSObject = "";
		Cursor smsCursor = getCursorQuery("tbl_Category", null, "id = " + cates_id,null,null, null, null);
		if (smsCursor != null){
			while (smsCursor.moveToNext()) {
				lstSMSObject = smsCursor.getString(smsCursor.getColumnIndex("name"));
			}
		}
		return lstSMSObject;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	

	
	

}
