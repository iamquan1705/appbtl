package com.lehoanghiep.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.lehoanghiep.dao.Lehoanghiep_HanghoaDao;
import com.lehoanghiep.dao.Lehoanghiep_LoaihanghoaDao;
import com.lehoanghiep.model.Lehoanghiep_Hanghoa;
import com.lehoanghiep.qlhh.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class Lehoanghiep_ActivitySuahanghoa extends AppCompatActivity {
    private EditText edtMaHangHoaSua, edtTenHangHoaSua, edtChatLieuSua;
    private Button btnCameraSua, btnLibrarySua, btnSuaHangHoa, btnThoatSuaHangHoa;
    private ImageView imgAnhHangHoaSua;
    private int Resquet_code_camera = 123;
    private int Resquet_code_library = 124;
    private Lehoanghiep_HanghoaDao hanghoaDao;
    private Lehoanghiep_LoaihanghoaDao lhhDao;
    private Spinner spnTenLoaiHangHoaSua;
    private ArrayList listtenbophan;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lehoanghiep__suahanghoa);
        anhxa();
        hanghoaDao = new Lehoanghiep_HanghoaDao(this);
        lhhDao = new Lehoanghiep_LoaihanghoaDao(this);
        Intent intee = getIntent();
        String mahh = intee.getStringExtra("maHangHoa");
        Lehoanghiep_Hanghoa hanghoa = hanghoaDao.getHangHoa(mahh);

        edtMaHangHoaSua.setText(hanghoa.getMaHangHoa());
        edtTenHangHoaSua.setText(hanghoa.getTenHangHoa());
        edtChatLieuSua.setText(hanghoa.getChatLieu());

        Bitmap bitmap = BitmapFactory.decodeByteArray(hanghoa.getAnhHangHoa(), 0, hanghoa.getAnhHangHoa().length);
        imgAnhHangHoaSua.setImageBitmap(bitmap);

        listtenbophan = new ArrayList();
        String ten = lhhDao.getTenLoaiHangHoa(hanghoa.getMaLoaiHangHoa());
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                listtenbophan);
        listtenbophan.addAll(lhhDao.getAllTenLoaiHangHoa()) ;
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spnTenLoaiHangHoaSua.setAdapter(adapter);
        for (int i=0;i<listtenbophan.size();i++) {
            if (listtenbophan.get(i).toString().equals(ten)) {
                spnTenLoaiHangHoaSua.setSelection(i);
            }
        }

        btnSuaHangHoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mahanghoa = edtMaHangHoaSua.getText().toString();
                String tenhanghoa = edtTenHangHoaSua.getText().toString();
                String chatlieu = edtChatLieuSua.getText().toString();

                String loaihanghoa = spnTenLoaiHangHoaSua.getSelectedItem().toString();
                int mlhh = lhhDao.getMaLoaiHangHoa(loaihanghoa);
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgAnhHangHoaSua.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream bytearray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bytearray);
                byte[] anh = bytearray.toByteArray();

                Lehoanghiep_Hanghoa hh = new Lehoanghiep_Hanghoa(mahanghoa, tenhanghoa, chatlieu, anh, mlhh);
                hanghoaDao.SuaHangHoa(hh);
                finish();
            }
        });
        btnCameraSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,Resquet_code_camera);
            }
        });

        btnLibrarySua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,Resquet_code_library);

            }
        });
        btnThoatSuaHangHoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Resquet_code_camera && resultCode == RESULT_OK && data != null) {
            Bitmap bimap = (Bitmap) data.getExtras().get("data");
            imgAnhHangHoaSua.setImageBitmap(bimap);
        }
        if (requestCode == Resquet_code_library && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgAnhHangHoaSua.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void anhxa() {
        edtMaHangHoaSua = findViewById(R.id.edtMaHangHoaSua);
        edtTenHangHoaSua = findViewById(R.id.edtTenHangHoaSua);
        edtChatLieuSua = findViewById(R.id.edtChatLieuSua);

        imgAnhHangHoaSua = findViewById(R.id.imgAnhHangHoaSua);

        btnCameraSua = findViewById(R.id.btnCameraSua);
        btnLibrarySua = findViewById(R.id.btnLibrarySua);
        btnSuaHangHoa = findViewById(R.id.btnSuaHangHoa);
        btnThoatSuaHangHoa = findViewById(R.id.btnThoatSuaHangHoa);

        spnTenLoaiHangHoaSua = findViewById(R.id.spnTenLoaiHangHoaSua);
    }
}