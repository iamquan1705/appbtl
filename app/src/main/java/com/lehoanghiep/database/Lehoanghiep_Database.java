package com.lehoanghiep.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Lehoanghiep_Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="QLHH.db";
    public static final String TABLE_NAME_HANGHOA="TB_HANGHOA";
    public static final String TABLE_NAME_LOAIHANGHOA="TB_LOAIHANGHOA";
    private static int version=4;
    private Context context;
    public Lehoanghiep_Database(Context context) {
        super(context, DATABASE_NAME, null, version);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="CREATE TABLE IF NOT EXISTS "+TABLE_NAME_LOAIHANGHOA+
                "(maLoaiHangHoa  integer primary key AUTOINCREMENT, "+
                "tenLoaiHangHoa  text, "+
                "ghiChu text)";
        String sql1="CREATE TABLE IF NOT EXISTS "+TABLE_NAME_HANGHOA+
                "(maHangHoa text primary key, "+
                "tenHangHoa text, "+
                "chatLieu  text," +
                "anhHangHoa blob," +
                "maLoaiHangHoa integer) ";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql1);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_LOAIHANGHOA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_HANGHOA);
        onCreate(sqLiteDatabase);
    }
}