package com.lehoanghiep.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.lehoanghiep.adapter.Lehoanghiep_HanghoaAdapter;
import com.lehoanghiep.dao.Lehoanghiep_HanghoaDao;
import com.lehoanghiep.dao.Lehoanghiep_LoaihanghoaDao;
import com.lehoanghiep.model.Lehoanghiep_Hanghoa;
import com.lehoanghiep.qlhh.R;

import java.util.ArrayList;
import java.util.List;

public class Lehoanghiep_ActivityHanghoa extends AppCompatActivity {
    private SearchView svHangHoa;
    private ListView lvHangHoa;
    private TextView tvTieuDeHangHoa;
    private Lehoanghiep_HanghoaDao hhdao;
    private List<Lehoanghiep_Hanghoa> hhlist;
    private Lehoanghiep_HanghoaAdapter hanghoaAdapter;
    private String tenLoaiHangHoa;
    private Lehoanghiep_LoaihanghoaDao lhhdao;
    private int maloaihanghoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lehoanghiep__hanghoa);
        anhxa();
        lhhdao = new Lehoanghiep_LoaihanghoaDao(this);
        hhlist = new ArrayList<>();
        Intent intent = getIntent();
        maloaihanghoa = intent.getIntExtra("MaLoaiHangHoa", 0);
        tenLoaiHangHoa = lhhdao.getTenLoaiHangHoa(maloaihanghoa);
        tvTieuDeHangHoa.setText("Danh sách hàng hóa " + tenLoaiHangHoa);
        hhdao = new Lehoanghiep_HanghoaDao(this);
        hhlist = hhdao.DanhSachHangHoa(maloaihanghoa);
        hanghoaAdapter = new Lehoanghiep_HanghoaAdapter(this, hhlist);
        lvHangHoa.setAdapter(hanghoaAdapter);
        registerForContextMenu(lvHangHoa);
//        btnThemNhanVienBP.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent thisintent = new Intent(NhanvienbpActivity.this, AddnhanvienActivity.class);
//                thisintent.putExtra("maBoPhan", mabp);
//                startActivity(thisintent);
//            }
//        });

        lvHangHoa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Lehoanghiep_Hanghoa hh = hhlist.get(i);
                Intent inte = new Intent(Lehoanghiep_ActivityHanghoa.this, Lehoanghiep_ActivitySuahanghoa.class);
                inte.putExtra("maHangHoa", hh.getMaHangHoa());
                startActivity(inte);
            }
        });
        lvHangHoa.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Dialog dialog = new Dialog(Lehoanghiep_ActivityHanghoa.this);
                dialog.setContentView(R.layout.dialog_xoahanghoa);
                dialog.setCanceledOnTouchOutside(false);
                Button btnYes = dialog.findViewById(R.id.btnYes);
                Button btnNo = dialog.findViewById(R.id.btnNo);
                dialog.show();
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        hhdao.XoaHangHoa(hhlist.get(i).getMaHangHoa());
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
//        svHangHoa.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                String text = newText;
//
//                if (text != null) {
//                    hanghoaAdapter.filter(text);
//                }
//
//                return false;
//            }
//        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.mnThem:{
                Intent intent=new Intent(Lehoanghiep_ActivityHanghoa.this, Lehoanghiep_ActivityThemhanghoa.class);
                intent.putExtra("maLoaiHangHoa", maloaihanghoa);
                startActivity(intent);
                onResume();
                break;
            }
            case R.id.mnThoat:{
                finish();
            }


        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
        hhlist.clear();
        hhlist.addAll(hhdao.DanhSachHangHoa(maloaihanghoa));
        hanghoaAdapter.notifyDataSetChanged();
    }
    private void anhxa() {
       // svHangHoa = findViewById(R.id.svHangHoa);
        lvHangHoa = findViewById(R.id.lvHangHoa);
        tvTieuDeHangHoa = findViewById(R.id.tvTieuDeHangHoa);
    }

}