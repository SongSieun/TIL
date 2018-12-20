package com.example.dsm2016.chatbox_theme;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TODOLIST (_id INTEGER PRIMARY KEY AUTOINCREMENT, item TEXT, create_at TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String create_at, String item){
        //읽고 쓰기가 가능하게 DB열기
        SQLiteDatabase db = getWritableDatabase();
        //DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO TODOLIST VALUES(null, '" + item + "', " + create_at + "');");
        db.close();
    }
/*
    public void update(String item){
        SQLiteDatabase db = getWritableDatabase();
        //입력한 항목과 일치하는 행의 가격 정보 수정
        db.execSQL("UPDATE TODOLIST SET item=" + item + " WHERE item='" + item + "';");
        db.close();
    }
*/
/*
    public void delete(String item){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM TODOLIST WHERE item='" + item + "';");
        db.close();
    }*/

    public String getResult(){
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        Cursor cursor = db.rawQuery("SELECT * FROM TODOLIST", null);
        while(cursor.moveToNext()){
            result += cursor.getString(0) + ", " + cursor.getString(1);
        }
        return result;
    }
}
