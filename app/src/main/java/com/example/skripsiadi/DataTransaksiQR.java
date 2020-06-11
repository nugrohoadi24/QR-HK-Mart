package com.example.skripsiadi;

import android.os.Parcel;
import android.os.Parcelable;

public class DataTransaksiQR implements Parcelable {
    public String BRAND;
    public int COSTPRICE;
    public String IDTRANSAKSI;
    public int NETSALES;
    public int PLU;
    public int PROFIT;
    public String QTY_TERJUAL;
    public int TOTALITEM;

    @Override
    public String toString() {
        return "DataTransaksiQR{" +
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

    public DataTransaksiQR(String BRAND, int COSTPRICE, String IDTRANSAKSI, int NETSALES, int PLU, int PROFIT, String QTY_TERJUAL, int TOTALITEM) {
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
        dest.writeInt(this.COSTPRICE);
        dest.writeString(this.IDTRANSAKSI);
        dest.writeInt(this.NETSALES);
        dest.writeInt(this.PLU);
        dest.writeInt(this.PROFIT);
        dest.writeString(this.QTY_TERJUAL);
        dest.writeInt(this.TOTALITEM);
    }

    public DataTransaksiQR() {
    }

    protected DataTransaksiQR(Parcel in) {
        this.BRAND = in.readString();
        this.COSTPRICE = in.readInt();
        this.IDTRANSAKSI = in.readString();
        this.NETSALES = in.readInt();
        this.PLU = in.readInt();
        this.PROFIT = in.readInt();
        this.QTY_TERJUAL = in.readString();
        this.TOTALITEM = in.readInt();
    }

    public static final Creator<DataTransaksiQR> CREATOR = new Creator<DataTransaksiQR>() {
        @Override
        public DataTransaksiQR createFromParcel(Parcel source) {
            return new DataTransaksiQR(source);
        }

        @Override
        public DataTransaksiQR[] newArray(int size) {
            return new DataTransaksiQR[size];
        }
    };
}