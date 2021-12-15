package com.lehoanghiep.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lehoanghiep.dao.Lehoanghiep_LoaihanghoaDao;
import com.lehoanghiep.model.Lehoanghiep_Loaihanghoa;
import com.lehoanghiep.qlhh.R;

import java.util.List;

public class Lehoanghiep_LoaihanghoaAdapter extends BaseAdapter {
    private Context context;
    private List<Lehoanghiep_Loaihanghoa> LHHList;
    private Lehoanghiep_LoaihanghoaDao loaihanghoaDao;

    public Lehoanghiep_LoaihanghoaAdapter(Context context, List<Lehoanghiep_Loaihanghoa> LHHList) {
        this.context = context;
        this.LHHList = LHHList;
    }

    @Override
    public int getCount() {
        return LHHList.size();
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
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.itemloaihanghoa, null);
            // ánh xạ
            viewHolder.tvTenLoaiHangItem = convertView.findViewById(R.id.tvTenLoaiHangItem);
            viewHolder.tvGhiChuItem = convertView.findViewById(R.id.tvGhiChuItem);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Lehoanghiep_Loaihanghoa bp = LHHList.get(position);
        viewHolder.tvTenLoaiHangItem.setText("Loại:" +bp.getTenLoaiHangHoa());
        viewHolder.tvGhiChuItem.setText("Ghi chú:" + bp.getGhiChu());
        return convertView;
    }
    class  ViewHolder{
        TextView tvTenLoaiHangItem,tvGhiChuItem;

    }
}
