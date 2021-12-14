package com.lehoanghiep.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.lehoanghiep.adapter.Lehoanghiep_LoaihanghoaAdapter;
import com.lehoanghiep.dao.Lehoanghiep_HanghoaDao;
import com.lehoanghiep.dao.Lehoanghiep_LoaihanghoaDao;
import com.lehoanghiep.model.Lehoanghiep_Loaihanghoa;
import com.lehoanghiep.qlhh.R;

import java.util.ArrayList;
import java.util.List;

public class Lehoanghiep_ActivityLoaihang extends AppCompatActivity {
    private Lehoanghiep_LoaihanghoaDao lhhDao;
    private ListView lvLoaiHangHoa;
    private List<Lehoanghiep_Loaihanghoa> loaihanghoaList;
    private Lehoanghiep_LoaihanghoaAdapter loaihanghoaAdapter;
    private Lehoanghiep_HanghoaDao hanghoaDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lehoanghiep__loaihang);
        anhxa();
        loaihanghoaList = new ArrayList<Lehoanghiep_Loaihanghoa>();
        lhhDao = new Lehoanghiep_LoaihanghoaDao(this);
        hanghoaDao = new Lehoanghiep_HanghoaDao(this);
        loaihanghoaList= lhhDao.DanhSachLoaiHangHoa();
        loaihanghoaAdapter=new Lehoanghiep_LoaihanghoaAdapter(this,loaihanghoaList);
        lvLoaiHangHoa.setAdapter(loaihanghoaAdapter);
        registerForContextMenu(lvLoaiHangHoa);

        lvLoaiHangHoa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Lehoanghiep_ActivityLoaihang.this, Lehoanghiep_ActivityHanghoa.class);
                intent.putExtra("MaLoaiHangHoa",loaihanghoaList.get(i).getMaLoaiHangHoa());
                startActivity(intent);
            }
        });


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
                Intent intent=new Intent(Lehoanghiep_ActivityLoaihang.this, Lehoanghiep_ActivityThemloaihang.class);
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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position=info.position;
        Lehoanghiep_Loaihanghoa lhanghoa= loaihanghoaList.get(position);
        switch (item.getItemId()){
            case R.id.mnSua:{
                Intent intent=new Intent(Lehoanghiep_ActivityLoaihang.this, Lehoanghiep_ActivitySualoaihang.class);
                intent.putExtra("DuLieu",lhanghoa);
                startActivity(intent);
                onResume();
                break;
            }
            case R.id.mnXoa:{
                Dialog dialog = new Dialog(Lehoanghiep_ActivityLoaihang.this);
                dialog.setContentView(R.layout.dialog_xoaloaihanghoa);
                dialog.setCanceledOnTouchOutside(false);
                Button btnYes = dialog.findViewById(R.id.btnYes);
                Button btnNo = dialog.findViewById(R.id.btnNo);
                dialog.show();
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        hanghoaDao.XoaHangHoaTheoLoai(lhanghoa.getMaLoaiHangHoa());
                        lhhDao.XoaLoaiHangHoa(lhanghoa.getMaLoaiHangHoa());
                        loaihanghoaAdapter.notifyDataSetChanged();
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
                loaihanghoaAdapter.notifyDataSetChanged();
                onResume();
                break;
            }
        }
        return super.onContextItemSelected(item);
    }



    private void anhxa() {
        lvLoaiHangHoa = findViewById(R.id.lvLoaiHangHoa);
    }
    @Override
    protected void onResume() {
        super.onResume();
        loaihanghoaList.clear();
        loaihanghoaList.addAll(lhhDao.DanhSachLoaiHangHoa());
        loaihanghoaAdapter.notifyDataSetChanged();
    }
}