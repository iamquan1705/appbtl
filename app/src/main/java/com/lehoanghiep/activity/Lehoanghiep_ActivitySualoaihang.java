package com.lehoanghiep.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lehoanghiep.dao.Lehoanghiep_LoaihanghoaDao;
import com.lehoanghiep.model.Lehoanghiep_Loaihanghoa;
import com.lehoanghiep.qlhh.R;

public class Lehoanghiep_ActivitySualoaihang extends AppCompatActivity {
    private EditText edtTenLoaiHangSua,edtGhiChuSua,edtMaLoaiHangSua;
    private Button btnLoaiHangSua,btnThoatLoaiHangSua;
    private Lehoanghiep_LoaihanghoaDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lehoanghiep__sualoaihang);
        anhxa();
        dao = new Lehoanghiep_LoaihanghoaDao(this);
        Intent intent= getIntent();
        final Lehoanghiep_Loaihanghoa lhh=(Lehoanghiep_Loaihanghoa) intent.getSerializableExtra("DuLieu");
        edtMaLoaiHangSua.setText(String.valueOf(lhh.getMaLoaiHangHoa()) );
        edtTenLoaiHangSua.setText(lhh.getTenLoaiHangHoa());
        edtGhiChuSua.setText(lhh.getGhiChu());
        btnLoaiHangSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lehoanghiep_Loaihanghoa bp = new Lehoanghiep_Loaihanghoa(Integer.parseInt(edtMaLoaiHangSua.getText().toString()),edtTenLoaiHangSua.getText().toString(),edtGhiChuSua.getText().toString());
                dao.SuaLoaiHangHoa(bp);
                finish();
            }
        });
        btnThoatLoaiHangSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void anhxa() {
        edtTenLoaiHangSua = findViewById(R.id.edtTenLoaiHangSua);
        edtGhiChuSua = findViewById(R.id.edtGhiChuSua);
        edtMaLoaiHangSua = findViewById(R.id.edtMaLoaiHangSua);
        btnLoaiHangSua = findViewById(R.id.btnLoaiHangSua);
        btnThoatLoaiHangSua = findViewById(R.id.btnThoatLoaiHangSua);
    }
}