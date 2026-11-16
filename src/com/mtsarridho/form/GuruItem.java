package com.mtsarridho.form;

public class GuruItem {
    private final String nik;
    private final String nama;

    public GuruItem(String nik, String nama) {
        this.nik = nik;
        this.nama = nama;
    }

    public String getNik() {
        return nik;
    }

    public String getNama() {
        return nama;
    }

    @Override
    public String toString() {
        return nama; // Supaya yang tampil di combobox adalah nama guru
    }
}

