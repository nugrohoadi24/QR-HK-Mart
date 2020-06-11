package com.example.skripsiadi;

import android.os.Parcel;
import android.os.Parcelable;

public class DataTransaksi implements Parcelable {
    public String BRAND;
    public String COSTPRICE;
    public String IDTRANSAKSI;
    public String NETSALES;
    public String PLU;
    public String PROFIT;
    public String QTY_TERJUAL;
    public String TOTALITEM;

    @Override
    public String toString() {
        return "DataTransaksi{" +
                "BRAND='" + BRAND + '\'' +
                ", COSTPRICE='" + COSTPRICE + '\'' +
                ", IDTRANSAKSI='" + IDTRANSAKSI + '\'' +
                ", NETSALES='" + NETSALES + '\'' +
                ", PLU='" + PLU + '\'' +
                ", PROFIT='" + PROFIT + '\'' +
                ", QTY_TERJUAL='" + QTY_TERJUAL + '\'' +
                ", TOTALITEM='" + TOTALITEM + '\'' +
                '}';
    }

    public DataTransaksi(String BRAND, String COSTPRICE, String IDTRANSAKSI, String NETSALES, String PLU, String PROFIT, String QTY_TERJUAL, String TOTALITEM) {
        this.BRAND = BRAND;
        this.COSTPRICE = COSTPRICE;
        this.IDTRANSAKSI = IDTRANSAKSI;
        this.NETSALES = NETSALES;
        this.PLU = PLU;
        this.PROFIT = PROFIT;
        this.QTY_TERJUAL = QTY_TERJUAL;
        this.TOTALITEM = TOTALITEM;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.BRAND);
        dest.writeString(this.COSTPRICE);
        dest.writeString(this.IDTRANSAKSI);
        dest.writeString(this.NETSALES);
        dest.writeString(this.PLU);
        dest.writeString(this.PROFIT);
        dest.writeString(this.QTY_TERJUAL);
        dest.writeString(this.TOTALITEM);
    }

    public DataTransaksi() {
    }

    protected DataTransaksi(Parcel in) {
        this.BRAND = in.readString();
        this.COSTPRICE = in.readString();
        this.IDTRANSAKSI = in.readString();
        this.NETSALES = in.readString();
        this.PLU = in.readString();
        this.PROFIT = in.readString();
        this.QTY_TERJUAL = in.readString();
        this.TOTALITEM = in.readString();
    }

    public static final Parcelable.Creator<DataTransaksi> CREATOR = new Parcelable.Creator<DataTransaksi>() {
        @Override
        public DataTransaksi createFromParcel(Parcel source) {
            return new DataTransaksi(source);
        }

        @Override
        public DataTransaksi[] newArray(int size) {
            return new DataTransaksi[size];
        }
    };
}
