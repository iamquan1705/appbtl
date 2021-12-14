package com.lehoanghiep.model;

import java.io.Serializable;

public class Lehoanghiep_Loaihanghoa implements Serializable {
    private int maLoaiHangHoa;
    private String tenLoaiHangHoa;
    private String ghiChu;

    public Lehoanghiep_Loaihanghoa() {
    }

    public Lehoanghiep_Loaihanghoa(String tenLoaiHangHoa, String ghiChu) {
        this.tenLoaiHangHoa = tenLoaiHangHoa;
        this.ghiChu = ghiChu;
    }

    public Lehoanghiep_Loaihanghoa(int maLoaiHangHoa, String tenLoaiHangHoa, String ghiChu) {
        this.maLoaiHangHoa = maLoaiHangHoa;
        this.tenLoaiHangHoa = tenLoaiHangHoa;
        this.ghiChu = ghiChu;
    }

    public int getMaLoaiHangHoa() {
        return maLoaiHangHoa;
    }

    public void setMaLoaiHangHoa(int maLoaiHangHoa) {
        this.maLoaiHangHoa = maLoaiHangHoa;
    }

    public String getTenLoaiHangHoa() {
        return tenLoaiHangHoa;
    }

    public void setTenLoaiHangHoa(String tenLoaiHangHoa) {
        this.tenLoaiHangHoa = tenLoaiHangHoa;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "Lehoanghiep_Loaihanghoa{" +
                "maLoaiHangHoa=" + maLoaiHangHoa +
                ", tenLoaiHangHoa='" + tenLoaiHangHoa + '\'' +
                ", ghiChu='" + ghiChu + '\'' +
                '}';
    }
}
