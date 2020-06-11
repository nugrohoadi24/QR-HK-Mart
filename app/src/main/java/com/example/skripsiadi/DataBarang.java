package com.example.skripsiadi;

import android.os.Parcel;
import android.os.Parcelable;

public class DataBarang implements Parcelable {
    public String BRAND;
    public String COSTPRICE;
    public String NETSALES;
    public String PLU;
    public String PROFIT;
    public String QTY_TERJUAL;



    public DataBarang(String BRAND, String COSTPRICE, String NETSALES, String PLU, String PROFIT, String QTY_TERJUAL) {
        this.BRAND = BRAND;
        this.COSTPRICE = COSTPRICE;
        this.NETSALES = NETSALES;
        this.PLU = PLU;
        this.PROFIT = PROFIT;
        this.QTY_TERJUAL = QTY_TERJUAL;
    }

    @Override
    public String toString() {
        return "DataBarang{" +
                "BRAND='" + BRAND + '\'' +
                ", COSTPRICE='" + COSTPRICE + '\'' +
                ", NETSALES='" + NETSALES + '\'' +
                ", PLU='" + PLU + '\'' +
                ", PROFIT='" + PROFIT + '\'' +
                ", QTY_TERJUAL='" + QTY_TERJUAL + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.BRAND);
        dest.writeString(this.COSTPRICE);
        dest.writeString(this.NETSALES);
        dest.writeString(this.PLU);
        dest.writeString(this.PROFIT);
        dest.writeString(this.QTY_TERJUAL);
    }

    public DataBarang() {
    }

    protected DataBarang(Parcel in) {
        this.BRAND = in.readString();
        this.COSTPRICE = in.readString();
        this.NETSALES = in.readString();
        this.PLU = in.readString();
        this.PROFIT = in.readString();
        this.QTY_TERJUAL = in.readString();
    }

    public static final Parcelable.Creator<DataBarang> CREATOR = new Parcelable.Creator<DataBarang>() {
        @Override
        public DataBarang createFromParcel(Parcel source) {
            return new DataBarang(source);
        }

        @Override
        public DataBarang[] newArray(int size) {
            return new DataBarang[size];
        }
    };
}
