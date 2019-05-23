package com.example.hoanhtuan_1706020096_11th1c;

public class DsMon extends MainActivity {
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTinchi() {
        return tinchi;
    }

    public void setTinchi(String tinchi) {
        this.tinchi = tinchi;
    }

    private String ten;
    private String ma;
    private String tinchi;
    public DsMon(String mten,String mma,String mtinchi){
        this.ten = mten;
        this.ma = mma;
        this.tinchi = mtinchi;
    }
}
