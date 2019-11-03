package com.example.e_survey.DatabaseLokal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.e_survey.Model.Cache.Login;

import java.util.ArrayList;
import java.util.List;

public class DataHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ekartutani";

    //Table Login
    private static final String TABLE_LOGIN = "tableLogin";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    //Table Kuesioner
    private static final String TABLE_KUESIONER = "tableKuesioner";
    private static final String KEY_KUESIONER = "kuesioner";

    //Table Log
    private static final String TABLE_LOG = "tableLog";
    private static final String KEY_NamaAksi = "namaAksi";
    private static final String KEY_TanggalAksi = "tanggalAksi";
    private static final String KEY_NamaDesa = "namaDesa";
    private static final String KEY_JamAksi = "jamAksi";

    //Table Draft
    private static final String TABLE_DRAFT = "tableDraft";
    private static final String KEY_JsonResponden = "jsonResponden";
    private static final String KEY_JsonJawaban = "jsonJawaban";

    //Table Kios
    private static final String TABLE_KIOS = "tableKios";
    private static final String KEY_KIOS = "listKios";

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_LOGIN = "CREATE TABLE " + TABLE_LOGIN + "(" + KEY_USERNAME + " TEXT," + KEY_PASSWORD + " TEXT" + ")";
        String CREATE_TABLE_KUESIONER = "CREATE TABLE "+ TABLE_KUESIONER+ "("+KEY_KUESIONER+" TEXT)";
        String CREATE_TABLE_LOG = "CREATE TABLE "+TABLE_LOG +" ("+KEY_NamaAksi+" TEXT, "+KEY_NamaDesa+ " TEXT,"+KEY_TanggalAksi+" TEXT,"+KEY_USERNAME+" TEXT)";
        String CREATE_TABLE_DRAFT = "CREATE TABLE " + TABLE_DRAFT + "("+KEY_JsonResponden+" TEXT,"+KEY_JsonJawaban+" TEXT)";
        String CREATE_TABLE_KIOS = "CREATE TABLE "+ TABLE_KIOS + "("+KEY_KIOS+" TEXT)";

        db.execSQL(CREATE_TABLE_LOGIN);
        db.execSQL(CREATE_TABLE_KUESIONER);
        db.execSQL(CREATE_TABLE_LOG);
        db.execSQL(CREATE_TABLE_DRAFT);
        db.execSQL(CREATE_TABLE_KIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);

        onCreate(db);
    }

    public void save(Login login) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, login.getUsername());
        values.put(KEY_PASSWORD, login.getPassword());

        db.insert(TABLE_LOGIN, null, values);
        db.close();
    }

    public List<Login> findAll() {
        List<Login> listLogin = new ArrayList<Login>();
        String query = "SELECT * FROM " + TABLE_LOGIN;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Login buku = new Login();
                buku.setUsername((cursor.getString(0)));
                buku.setPassword(cursor.getString(1));
                listLogin.add(buku);
            } while (cursor.moveToNext());
        }

        return listLogin;
    }

    public Login findOne(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_LOGIN, new String[]{KEY_USERNAME, KEY_PASSWORD},
                KEY_USERNAME + "=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor.moveToNext()) {
            cursor.moveToFirst();
            return new Login((cursor.getString(0)), cursor.getString(1));
        } else {
            return new Login("username", "haduh");
        }

    }

    public String cekLogin() {
        String hasil = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_LOGIN, new String[]{"*"},
                null,
                null, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                hasil = cursor.getString(0);
            } else {
                hasil = "none";
            }
            cursor.close();
        }

        db.close();
        return hasil;
    }

    public void clearLogin(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_LOGIN, null, null);
        db.close();
    }
}
