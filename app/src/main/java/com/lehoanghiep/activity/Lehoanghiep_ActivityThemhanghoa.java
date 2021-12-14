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

public class Lehoanghiep_ActivityThemhanghoa extends AppCompatActivity {
    private EditText edtMaHangHoaThem,edtTenHangHoaThem,edtChatLieuThem;
    private Button btnCameraAdd,btnLibraryAdd,btnThemHangHoa,btnThoatThemHangHoa;
    private ImageView imgAnhHangHoaThem;
    private int Resquet_code_camera=111;
    private int Resquet_code_library=222;
    private Lehoanghiep_HanghoaDao hanghoaDao;
    private Spinner spnTenLoaiHangHoa;
    private Lehoanghiep_LoaihanghoaDao loaihanghoaDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lehoanghiep__themhanghoa);
        anhxa();
        hanghoaDao = new Lehoanghiep_HanghoaDao(this);
        loaihanghoaDao = new Lehoanghiep_LoaihanghoaDao(this);
        Intent intent = getIntent();
        int mlhh = intent.getIntExtra("maLoaiHangHoa",0);
        ArrayList listtenLHH = new ArrayList();
        if (mlhh==-1) {

            listtenLHH = (ArrayList) loaihanghoaDao.getAllTenLoaiHangHoa();
        } else {
            listtenLHH.clear();
            listtenLHH.add(loaihanghoaDao.getTenLoaiHangHoa(mlhh));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                listtenLHH);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spnTenLoaiHangHoa.setAdapter(adapter);


        btnCameraAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,Resquet_code_camera);
            }
        });

        btnLibraryAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,Resquet_code_library);

            }
        });
        btnThemHangHoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maHangHoa =edtMaHangHoaThem.getText().toString();
                String tenHangHoa =edtTenHangHoaThem.getText().toString();
                String chatLieu =edtChatLieuThem.getText().toString();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgAnhHangHoaThem.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream bytearray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,bytearray);
                byte[] hinh = bytearray.toByteArray();
                String tlhh = spnTenLoaiHangHoa.getSelectedItem().toString();
                int maloaihanghoa = loaihanghoaDao.getMaLoaiHangHoa(tlhh);
                Lehoanghiep_Hanghoa hh = new Lehoanghiep_Hanghoa(maHangHoa,tenHangHoa,chatLieu,hinh,maloaihanghoa);
                hanghoaDao.ThemHangHoa(hh);
                finish();
            }
        });

        btnThoatThemHangHoa.setOnClickListener(new View.OnClickListener() {
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
            imgAnhHangHoaThem.setImageBitmap(bimap);
        }
        if (requestCode == Resquet_code_library && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgAnhHangHoaThem.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    private void anhxa() {
        edtMaHangHoaThem = findViewById(R.id.edtMaHangHoaThem);
        edtTenHangHoaThem = findViewById(R.id.edtTenHangHoaThem);
        edtChatLieuThem = findViewById(R.id.edtChatLieuThem);


        btnCameraAdd = findViewById(R.id.btnCameraAdd);
        btnLibraryAdd = findViewById(R.id.btnLibraryAdd);
        btnThemHangHoa = findViewById(R.id.btnThemHangHoa);
        btnThoatThemHangHoa = findViewById(R.id.btnThoatThemHangHoa);

        imgAnhHangHoaThem = findViewById(R.id.imgAnhHangHoaThem);

        spnTenLoaiHangHoa = findViewById(R.id.spnTenLoaiHangHoa);
    }
}