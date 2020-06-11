package com.example.skripsiadi;

import android.os.Parcel;
import android.os.Parcelable;

public class DataSiswa implements Parcelable {
    public String ID;
    public String NAMA;
    public String PASSWORD;
    public String ALAMAT;
    public String SALDO;

    public String toString() {
        return "DataSiswa{" +
                "ID='" + ID + '\'' +
                ", NAMA='" + NAMA + '\'' +
                ", PASSWORD='" + PASSWORD + '\'' +
                ", ALAMAT='" + ALAMAT + '\'' +
                ", SALDO='" + SALDO + '\'' +
                '}';
    }

    public DataSiswa() {
    }

    public DataSiswa(String ID, String NAMA, String PASSWORD, String ALAMAT, String SALDO) {
        this.ID = ID;
        this.NAMA = NAMA;
        this.PASSWORD = PASSWORD;
        this.ALAMAT = ALAMAT;
        this.SALDO = SALDO;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ID);
        dest.writeString(this.NAMA);
        dest.writeString(this.PASSWORD);
        dest.writeString(this.ALAMAT);
        dest.writeString(this.SALDO);
    }

    protected DataSiswa(Parcel in) {
        this.ID = in.readString();
        this.NAMA = in.readString();
        this.PASSWORD = in.readString();
        this.ALAMAT = in.readString();
        this.SALDO = in.readString();
    }

    public static final Parcelable.Creator<DataSiswa> CREATOR = new Parcelable.Creator<DataSiswa>() {
        @Override
        public DataSiswa createFromParcel(Parcel source) {
            return new DataSiswa(source);
        }

        @Override
        public DataSiswa[] newArray(int size) {
            return new DataSiswa[size];
        }
    };
}
