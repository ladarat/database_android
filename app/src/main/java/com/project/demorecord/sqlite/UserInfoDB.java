package com.project.demorecord.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.project.demorecord.UserInfoList;
import com.project.demorecord.model.UserInfo;

import java.util.ArrayList;

public class UserInfoDB extends SQLiteOpenHelper {


    private static final String DB_NAME = "DEMOINFO";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "UserInfo";
    public static final String COL_NAME = "name";
    public static final String COL_AGE = "age";
    private SQLiteDatabase sqlLiteDatabase;

    Cursor cursor;

    public UserInfoDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        sqlLiteDatabase = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " TEXT, " + COL_AGE + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(UserInfo userInfo) {
        sqlLiteDatabase.execSQL("INSERT INTO " + TABLE_NAME + " (" + COL_NAME + ", " + COL_AGE + ") " +
                "VALUES ('" + userInfo.getName() +"', " + "'"+userInfo.getAge() +"');");
    }

    public UserInfoList findAll() {
        cursor = sqlLiteDatabase.rawQuery("SELECT " + COL_NAME + ", " + COL_AGE
                + " FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        UserInfoList userList = new UserInfoList();
        userList.setUserInfoList(new ArrayList<UserInfo>());


        while (!cursor.isAfterLast()) {
            UserInfo info = new UserInfo();
            info.setAge(cursor.getString(cursor.getColumnIndex(COL_AGE)));
            info.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));

            userList.getUserInfoList().add(info);

            cursor.moveToNext();
        }

        return userList;
    }
}
