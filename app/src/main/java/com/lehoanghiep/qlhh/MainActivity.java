package com.lehoanghiep.qlhh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lehoanghiep.activity.Lehoanghiep_ActivityLoaihang;

public class MainActivity extends AppCompatActivity {
    private Button btnStartLoaiHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStartLoaiHang = findViewById(R.id.btnStartLoaiHang);
        btnStartLoaiHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Lehoanghiep_ActivityLoaihang.class);
                startActivity(intent);
            }
        });
    }
}