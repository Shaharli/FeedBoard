package com.avigezerit.feedboard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shaharli on 28/06/2016.
 */
public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "RssFeeds";

    //name of table
    private static final String TABLE_FEEDS = "Feeds";

    //columns names
    private static final String KEY_ID = "id";
    private static final String KEY_FEED_TITLE = "title";

    //key of our feed
    private static final String KEY_ADDRESS = "address";

    public Database (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FEEDS_TABLE = "CREATE TABLE " + TABLE_FEEDS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FEED_TITLE + " TEXT," + KEY_ADDRESS + " TEXT" + ")";
        db.execSQL(CREATE_FEEDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_FEEDS);

        //CREATE TABLE AGAIN
        onCreate(db);
    }

    public void  addRssFeed (RssFeed feed){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FEED_TITLE, feed.rssFeedTitle);
        values.put(KEY_ADDRESS, feed.rssFeedAddress);

        db.insert(TABLE_FEEDS, null, values);
        db.close();
    }

    public List<RssFeed> getRssFeed (){
        List<RssFeed> results = new ArrayList<RssFeed>();

        String selectQuery = "SELECT * FROM " + TABLE_FEEDS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                RssFeed rssFeed = new RssFeed(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
                results.add(rssFeed);
            } while (cursor.moveToNext());
        }

        db.close();
        return results;
    }

    public void deleteRssFeed (RssFeed feed){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FEEDS, KEY_ID + " = ? ", new String[]{String.valueOf(feed.id)});
        db.close();

    }
}
