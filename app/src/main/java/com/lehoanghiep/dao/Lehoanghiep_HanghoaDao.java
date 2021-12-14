package com.lehoanghiep.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lehoanghiep.database.Lehoanghiep_Database;
import com.lehoanghiep.model.Lehoanghiep_Hanghoa;

import java.util.ArrayList;
import java.util.List;

public class Lehoanghiep_HanghoaDao {
    private Lehoanghiep_Database database;

    public Lehoanghiep_HanghoaDao(Context context) {
        database = new Lehoanghiep_Database(context);
    }

    public List<Lehoanghiep_Hanghoa> DanhSachHangHoa(int maLHH) {
        List<Lehoanghiep_Hanghoa> NVLIST = new ArrayList<Lehoanghiep_Hanghoa>();
        String sql = "SELECT * FROM '"+database.TABLE_NAME_HANGHOA+"' where maLoaiHangHoa ='" + maLHH + "'";
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String maHangHoa = cursor.getString(0);
            String tenHangHoa = cursor.getString(1);
            String chatLieu  = cursor.getString(2);
            byte[] anhHangHoa = cursor.getBlob(3);
            int maLoaiHangHoa  = cursor.getInt(4);
            Lehoanghiep_Hanghoa hh = new Lehoanghiep_Hanghoa(maHangHoa, tenHangHoa, chatLieu,anhHangHoa, maLoaiHangHoa);
            NVLIST.add(hh);
            cursor.moveToNext();
        }
        return NVLIST;
    }

    public List<Lehoanghiep_Hanghoa> DanhSachHangHoaFull() {
        List<Lehoanghiep_Hanghoa> nvs = new ArrayList<Lehoanghiep_Hanghoa>();
        String sql = "SELECT * FROM '"+database.TABLE_NAME_HANGHOA+"'";
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String maHangHoa = cursor.getString(0);
            String tenHangHoa = cursor.getString(1);
            String chatLieu  = cursor.getString(2);
            byte[] anhHangHoa = cursor.getBlob(3);
            int maLoaiHangHoa  = cursor.getInt(4);
            Lehoanghiep_Hanghoa hh = new Lehoanghiep_Hanghoa(maHangHoa, tenHangHoa, chatLieu,anhHangHoa, maLoaiHangHoa);
            nvs.add(hh);
            cursor.moveToNext();
        }
        return nvs;
    }

    public void ThemHangHoa(Lehoanghiep_Hanghoa hh) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maHangHoa", hh.getMaHangHoa());
        values.put("tenHangHoa", hh.getTenHangHoa());
        values.put("chatLieu", hh.getChatLieu());
        values.put("anhHangHoa", hh.getAnhHangHoa());
        values.put("maLoaiHangHoa", hh.getMaLoaiHangHoa());
        db.insert("'"+database.TABLE_NAME_HANGHOA+"'", null, values);

    }

    public void SuaHangHoa(Lehoanghiep_Hanghoa hh) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenHangHoa", hh.getTenHangHoa());
        values.put("chatLieu", hh.getChatLieu());
        values.put("anhHangHoa", hh.getAnhHangHoa());
        values.put("maLoaiHangHoa", hh.getMaLoaiHangHoa());
        db.update("'"+database.TABLE_NAME_HANGHOA+"'", values, "maHangHoa=?", new String[]{String.valueOf(hh.getMaHangHoa())});
        db.close();

    }

    public void XoaHangHoa(String id) {
        SQLiteDatabase db = database.getWritableDatabase();
        String delete = "Delete from '"+database.TABLE_NAME_HANGHOA+"' where maHangHoa='" + id + "'";
        db.execSQL(delete);
        db.close();
    }

    public void XoaHangHoaTheoLoai(int mbp) {
        SQLiteDatabase db = database.getWritableDatabase();
        String delete = "Delete from '"+database.TABLE_NAME_HANGHOA+"' where maLoaiHangHOa='" + mbp + "'";
        db.execSQL(delete);
        db.close();
    }

    public Lehoanghiep_Hanghoa getHangHoa(String mahh) {
        String sql = "SELECT * FROM '"+database.TABLE_NAME_HANGHOA+"' where maHangHoa = '" + mahh + "'";
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        Lehoanghiep_Hanghoa hh = null;
        if (!cursor.isAfterLast()) {
            String maHangHoa = cursor.getString(0);
            String tenHangHOa = cursor.getString(1);
            String chatLieu  = cursor.getString(2);
            byte[] anhHangHoa = cursor.getBlob(3);
            int maLoaiHangHoa  = cursor.getInt(4);
            hh = new Lehoanghiep_Hanghoa(maHangHoa, tenHangHOa, chatLieu, anhHangHoa,maLoaiHangHoa);
            cursor.moveToNext();
        }
        return hh;
    }


}

