package com.lehoanghiep.model;

import java.io.Serializable;
import java.util.Arrays;

public class Lehoanghiep_Hanghoa implements Serializable {
    private String maHangHoa;
    private String tenHangHoa;
    private String chatLieu;
    private byte[] anhHangHoa;
    private int maLoaiHangHoa;

    public Lehoanghiep_Hanghoa() {
    }

    public Lehoanghiep_Hanghoa(String maHangHoa, String tenHangHoa, String chatLieu, byte[] anhHangHoa, int maLoaiHangHoa) {
        this.maHangHoa = maHangHoa;
        this.tenHangHoa = tenHangHoa;
        this.chatLieu = chatLieu;
        this.anhHangHoa = anhHangHoa;
        this.maLoaiHangHoa = maLoaiHangHoa;
    }

    public String getMaHangHoa() {
        return maHangHoa;
    }

    public void setMaHangHoa(String maHangHoa) {
        this.maHangHoa = maHangHoa;
    }

    public String getTenHangHoa() {
        return tenHangHoa;
    }

    public void setTenHangHoa(String tenHangHoa) {
        this.tenHangHoa = tenHangHoa;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public byte[] getAnhHangHoa() {
        return anhHangHoa;
    }

    public void setAnhHangHoa(byte[] anhHangHoa) {
        this.anhHangHoa = anhHangHoa;
    }

    public int getMaLoaiHangHoa() {
        return maLoaiHangHoa;
    }

    public void setMaLoaiHangHoa(int maLoaiHangHoa) {
        this.maLoaiHangHoa = maLoaiHangHoa;
    }

    @Override
    public String toString() {
        return "Lehoanghiep_Hanghoa{" +
                "maHangHoa='" + maHangHoa + '\'' +
                ", tenHangHoa='" + tenHangHoa + '\'' +
                ", chatLieu='" + chatLieu + '\'' +
                ", anhHangHoa=" + Arrays.toString(anhHangHoa) +
                ", maLoaiHangHoa=" + maLoaiHangHoa +
                '}';
    }
}
