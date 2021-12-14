package com.lehoanghiep.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lehoanghiep.model.Lehoanghiep_Hanghoa;
import com.lehoanghiep.qlhh.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Lehoanghiep_HanghoaAdapter extends BaseAdapter {
    private Context context;
    private List<Lehoanghiep_Hanghoa> HHList;
    private ArrayList<Lehoanghiep_Hanghoa> arl = new ArrayList<>();


    public Lehoanghiep_HanghoaAdapter(Context context, List<Lehoanghiep_Hanghoa> HHList) {
        this.context = context;
        this.HHList = HHList;
        this.arl.addAll(HHList);
    }

    @Override
    public int getCount() {
        return HHList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.itemhanghoa,null);
            // ánh xạ
            viewHolder.tvMaHangHoa=convertView.findViewById(R.id.tvMaHangHoa);
            viewHolder.tvTenHangHoa=convertView.findViewById(R.id.tvTenHangHoa);
            viewHolder.tvChatLieu=convertView.findViewById(R.id.tvChatLieu);
            viewHolder.tvMaLoaiHangHoa=convertView.findViewById(R.id.tvMaLoaiHangHoa);
            viewHolder.imgAnhHangHoaItem=convertView.findViewById(R.id.imgAnhHangHoaItem);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }

        Lehoanghiep_Hanghoa hh=HHList.get(position);
        viewHolder.tvMaHangHoa.setText("Mã hàng hóa : "+hh.getMaHangHoa());
        viewHolder.tvTenHangHoa.setText("Tên hàng hóa: "+ hh.getTenHangHoa());
        viewHolder.tvChatLieu.setText("Chất liệu : "+ hh.getChatLieu());
        viewHolder.tvMaLoaiHangHoa.setText("Mã loại hàng hóa : "+ hh.getMaLoaiHangHoa());
        Bitmap bitmap = BitmapFactory.decodeByteArray(hh.getAnhHangHoa(), 0, hh.getAnhHangHoa().length);
        viewHolder.imgAnhHangHoaItem.setImageBitmap(bitmap);
        return convertView;
    }
    class  ViewHolder{
        TextView tvMaHangHoa,tvTenHangHoa,tvChatLieu,tvMaLoaiHangHoa;
        ImageView imgAnhHangHoaItem;
    }
    public void filter(String text) {
        String textSearch = text.toLowerCase(Locale.getDefault());
        HHList.clear();
        if (textSearch.length() == 0) {
            HHList.clear();
            HHList.addAll(arl);
        } else {
            for (int i = 0; i < arl.size(); i++) {
                if (arl.get(i).getTenHangHoa().toLowerCase(Locale.getDefault()).contains(textSearch)) {
                    HHList.add(arl.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }
}


