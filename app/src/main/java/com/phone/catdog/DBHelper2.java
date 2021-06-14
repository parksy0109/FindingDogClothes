package com.phone.catdog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper2 extends SQLiteOpenHelper {

    //database 파일을 생성
    public DBHelper2(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //실행할 때 DB 최초 생성
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE t4 (kind TEXT, size TEXT, path TEXT, price TEXT, url TEXT, isFav INTEGER, content TEXT);");
        //result.append("\nt3 테이블 생성 완료.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS t4");
        onCreate(db);
    }

}
