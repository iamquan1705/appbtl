package com.lehoanghiep.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lehoanghiep.dao.Lehoanghiep_LoaihanghoaDao;
import com.lehoanghiep.model.Lehoanghiep_Loaihanghoa;
import com.lehoanghiep.qlhh.R;

public class Lehoanghiep_ActivityThemloaihang extends AppCompatActivity {
    private EditText edtTenLoaiHangThem,edtGhiChuThem;
    private Button btnLoaiHangThem,btnThoatLoaiHangThem;
    private Lehoanghiep_LoaihanghoaDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lehoanghiep__themloaihang);
        anhxa();
        dao = new Lehoanghiep_LoaihanghoaDao(this);
        btnLoaiHangThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lehoanghiep_Loaihanghoa lhh = new Lehoanghiep_Loaihanghoa(edtTenLoaiHangThem.getText().toString(),edtGhiChuThem.getText().toString());
                dao.ThemLoaiHangHoa(lhh);
                finish();
            }
        });
        btnThoatLoaiHangThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void anhxa() {
        edtTenLoaiHangThem = findViewById(R.id.edtTenLoaiHangThem);
        edtGhiChuThem = findViewById(R.id.edtGhiChuThem);
        btnLoaiHangThem = findViewById(R.id.btnLoaiHangThem);
        btnThoatLoaiHangThem = findViewById(R.id.btnThoatLoaiHangThem);
    }
}