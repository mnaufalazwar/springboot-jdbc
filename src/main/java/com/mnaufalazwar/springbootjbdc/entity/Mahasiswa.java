package com.mnaufalazwar.springbootjbdc.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Mahasiswa {

    @NonNull
    private String nim;

    @NonNull
    private String nama;

    @NonNull
    private float ipk;

    @NonNull
    private String jurusan;

    public Mahasiswa(){

    }

    public Mahasiswa(String nim, String nama, float ipk, String jurusan){
        this.nim = nim;
        this.nama = nama;
        this.ipk = ipk;
        this.jurusan = jurusan;
    }

}
