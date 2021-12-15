package com.lehoanghiep.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.lehoanghiep.adapter.Lehoanghiep_HanghoaAdapter;
import com.lehoanghiep.dao.Lehoanghiep_HanghoaDao;
import com.lehoanghiep.model.Lehoanghiep_Hanghoa;
import com.lehoanghiep.qlhh.R;

import java.util.ArrayList;
import java.util.List;

public class Lehoanghiep_ActivityToanbohanghoa extends AppCompatActivity {
    private SearchView svHangHoaFull;
    private ListView lvHangHoaFull;
    private Button btnThemHangHoaFull;
    private List<Lehoanghiep_Hanghoa> hhlist;
    private Lehoanghiep_HanghoaAdapter hanghoaAdapter;
    private Lehoanghiep_HanghoaDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lehoanghiep_toanbohanghoa);
        anhxa();
        hhlist = new ArrayList<>();
        dao = new Lehoanghiep_HanghoaDao(this);
        hhlist = dao.DanhSachHangHoaFull();
        hanghoaAdapter = new Lehoanghiep_HanghoaAdapter(this, hhlist);
        lvHangHoaFull.setAdapter(hanghoaAdapter);

        btnThemHangHoaFull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent thisintent = new Intent(Lehoanghiep_ActivityToanbohanghoa.this, Lehoanghiep_ActivityThemhanghoa.class);
                thisintent.putExtra("maLoaiHangHoa", -1);
                startActivity(thisintent);
            }
        });
        lvHangHoaFull.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Lehoanghiep_Hanghoa hh = hhlist.get(i);
                Intent in = new Intent(Lehoanghiep_ActivityToanbohanghoa.this, Lehoanghiep_ActivitySuahanghoa.class);
                in.putExtra("maHangHoa", hh.getMaHangHoa());
                startActivity(in);
            }
        });
        lvHangHoaFull.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Dialog dialog = new Dialog(Lehoanghiep_ActivityToanbohanghoa.this);
                dialog.setContentView(R.layout.dialog_xoahanghoa);
                dialog.setCanceledOnTouchOutside(false);
                Button btnYes = dialog.findViewById(R.id.btnYes);
                Button btnNo = dialog.findViewById(R.id.btnNo);
                dialog.show();
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dao.XoaHangHoa(hhlist.get(i).getMaHangHoa());
                        hanghoaAdapter.notifyDataSetChanged();
                        onResume();
                        dialog.cancel();
                    }
                });
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                hanghoaAdapter.notifyDataSetChanged();
                onResume();
                return true;
            }
        });
        svHangHoaFull.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String text = newText;

                if (text != null) {
                    hanghoaAdapter.filter(text);
                }

                return false;
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        hhlist.clear();
        hhlist.addAll(dao.DanhSachHangHoaFull());
        hanghoaAdapter.notifyDataSetChanged();
    }

    private void anhxa() {
        svHangHoaFull = findViewById(R.id.svHangHoaFull);
        lvHangHoaFull = findViewById(R.id.lvHangHoaFull);
        btnThemHangHoaFull = findViewById(R.id.btnThemHangHoaFull);
    }
}