package com.example.e_survey.DatabaseLokal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DataHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "survey";

    private static final String TABLE_HISTORY = "history";

    private static final String KEY_TANGGAL = "tanggal";
    private static final String KEY_JAM = "jam";
    private static final String KEY_KATEGORI = "kategori";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_DESA = "desa";
    private History history;

    public DataHelper(Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_HISTORY_TABLE = "CREATE TABLE " + TABLE_HISTORY + "(" + KEY_TANGGAL + "STRING," + KEY_JAM + "INTEGER," + KEY_KATEGORI + "STRING," + KEY_NAMA + "STRING," +KEY_DESA + "STRING" + "NULL" + ")";
        db.execSQL (CREATE_HISTORY_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        onCreate(db);
    }

    public void addRecord(History history) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TANGGAL, history.getTanggal());
        values.put(KEY_JAM, history.getJam());
        values.put(KEY_KATEGORI, history.getKategori());
        values.put(KEY_NAMA, history.getNama());
        values.put(KEY_DESA, history.getDesa());


        db.insert(TABLE_HISTORY, null, values);
        db.close();
    }

    // get All Record
    public List<History> getAllRecord() {
        List<History> historyList = new ArrayList<History>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_HISTORY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                History history = new History();
                history.setTanggal(cursor.getString(0));
                history.setJam(Integer.parseInt(cursor.getString(1)));
                history.setKategori(cursor.getString(2));
                history.setNama(cursor.getString(3));
                history.setDesa(cursor.getString(4));

                historyList.add(history);
            } while (cursor.moveToNext());
        }

        // return contact list
        return historyList;
    }


    // static variable
//    private static final int DATABASE_VERSION = 1;
//
//    // Database name
//    private static final String DATABASE_NAME = "TallManager";
//
//    // table name
//    private static final String TABLE_TALL = "talls";
//
//    // column tables
//    private static final String KEY_ID = "id";
//    private static final String KEY_NAME = "user";
//    private static final String KEY_TALL = "tall";
//
//    public DataHelper(Context context){
////        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//
//    }
//
//    //Create table
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_TALL + "("
//                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
//                + KEY_TALL + " TEXT" + ")";
//        db.execSQL(CREATE_CONTACTS_TABLE);
//    }
//
//    // on Upgrade database
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TALL);
//        onCreate(db);
//    }

}
