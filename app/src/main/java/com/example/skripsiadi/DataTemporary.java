package com.example.skripsiadi;

import android.os.Parcel;
import android.os.Parcelable;

public class DataTemporary implements Parcelable {
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
        return "DataTemporary{" +
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

    public DataTemporary(String BRAND, int COSTPRICE, String IDTRANSAKSI, int NETSALES, int PLU, int PROFIT, String QTY_TERJUAL, int TOTALITEM) {
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

    protected DataTemporary(Parcel in) {
        this.BRAND = in.readString();
        this.COSTPRICE = in.readInt();
        this.IDTRANSAKSI = in.readString();
        this.NETSALES = in.readInt();
        this.PLU = in.readInt();
        this.PROFIT = in.readInt();
        this.QTY_TERJUAL = in.readString();
        this.TOTALITEM = in.readInt();
    }

    public DataTemporary() {
    }

    public static final Creator<DataTemporary> CREATOR = new Creator<DataTemporary>() {
        @Override
        public DataTemporary createFromParcel(Parcel source) {
            return new DataTemporary(source);
        }

        @Override
        public DataTemporary[] newArray(int size) {
            return new DataTemporary[size];
        }
    };
}
