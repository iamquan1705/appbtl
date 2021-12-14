package com.lehoanghiep.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lehoanghiep.database.Lehoanghiep_Database;
import com.lehoanghiep.model.Lehoanghiep_Loaihanghoa;

import java.util.ArrayList;
import java.util.List;

public class Lehoanghiep_LoaihanghoaDao {
    private Lehoanghiep_Database database;

    public Lehoanghiep_LoaihanghoaDao(Context context) {
        database = new Lehoanghiep_Database(context);
    }
    public List<Lehoanghiep_Loaihanghoa> DanhSachLoaiHangHoa() {
        List<Lehoanghiep_Loaihanghoa> BPLIST = new ArrayList<Lehoanghiep_Loaihanghoa>();
        String sql = "SELECT * FROM '"+database.TABLE_NAME_LOAIHANGHOA+"'";
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int maLoaiHangHoa = cursor.getInt(0);
            String tenLoaiHangHoa = cursor.getString(1);
            String ghiChu = cursor.getString(2);
            Lehoanghiep_Loaihanghoa lhh = new Lehoanghiep_Loaihanghoa(maLoaiHangHoa, tenLoaiHangHoa, ghiChu);
            BPLIST.add(lhh);
            cursor.moveToNext();
        }
        return BPLIST;
    }

    public void ThemLoaiHangHoa(Lehoanghiep_Loaihanghoa loaihanghoa) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenLoaiHangHoa",loaihanghoa.getTenLoaiHangHoa());
        values.put("ghiChu", loaihanghoa.getGhiChu());
        db.insert("'"+ database.TABLE_NAME_LOAIHANGHOA +"'", null, values);

    }

    public void SuaLoaiHangHoa(Lehoanghiep_Loaihanghoa loaihanghoa) {
        SQLiteDatabase db = database.getWritableDatabase();
        String sql = "UPDATE '"+database.TABLE_NAME_LOAIHANGHOA+"' SET tenLoaiHangHoa='" + loaihanghoa.getTenLoaiHangHoa() + "', ghiChu='" + loaihanghoa.getGhiChu() + "' WHERE maLoaiHangHoa= '" + loaihanghoa.getMaLoaiHangHoa() + "' ";
        db.execSQL(sql);
        db.close();

    }

    public int XoaLoaiHangHoa(int id) {
        SQLiteDatabase db = database.getWritableDatabase();
        int kq = db.delete("'"+database.TABLE_NAME_LOAIHANGHOA+"'", "maLoaiHangHoa=?", new String[]{String.valueOf(id)});
        db.close();
        return kq;
    }

    public List<String> getAllTenLoaiHangHoa() {
        List<String> HHList = new ArrayList<>();
        String sql = "SELECT tenLoaiHangHoa FROM '"+database.TABLE_NAME_LOAIHANGHOA+"'";
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor!=null && cursor.getCount() > 0)
        {
            if (cursor.moveToFirst())
            {
                do {
                    HHList.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
        }
        return HHList;
    }
    public String getTenLoaiHangHoa(int maLoaiHangHoa) {
        String sql = "SELECT tenLoaiHangHoa FROM '"+database.TABLE_NAME_LOAIHANGHOA+"' where maLoaiHangHoa = "+maLoaiHangHoa;
        String tenLoaiHangHoa = "";
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor!=null && cursor.getCount() > 0)
        {
            if (cursor.moveToFirst())
            {
                tenLoaiHangHoa = cursor.getString(0);
            }
        }
        return tenLoaiHangHoa;
    }
    public int getMaLoaiHangHoa(String tenLoaiHangHoa) {
        String sql = "SELECT maLoaiHangHoa FROM '"+database.TABLE_NAME_LOAIHANGHOA+"' where tenLoaiHangHoa = '"+tenLoaiHangHoa+"'";
        int maLoaiHangHoa = 0;
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor!=null && cursor.getCount() > 0)
        {
            if (cursor.moveToFirst())
            {
                maLoaiHangHoa = cursor.getInt(0);
            }
        }
        return maLoaiHangHoa;
    }

}

