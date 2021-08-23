package com.candlestickschart.wayanadboothpresident;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "WeddingData")
public class WeddingData {
    @PrimaryKey(autoGenerate = true)
    public int ids;
    @ColumnInfo(name = "user_id")
    public String user_id;
    @ColumnInfo(name = "user_mobile_no")
    public String user_mobile_no;
    @ColumnInfo(name = "LOC_ID")
    public String LOC_ID;

    @ColumnInfo(name = "Name")
    public String Name;
    @ColumnInfo(name = "BrideGroom")
    public String BrideGroom;
    @ColumnInfo(name = "DOW")
    public String DOW;
    @ColumnInfo(name = "AddressTo")
    public String AddressTo;
    @ColumnInfo(name = "Relation")
    public String Relation;
    @ColumnInfo(name = "Address")
    public String Address;
    public WeddingData(int ids,
                     String user_id,
                     String user_mobile_no,
                     String LOC_ID,
                     String Name,
                     String BrideGroom,
                     String DOW,
                     String AddressTo,
                     String Relation,
                     String Address

    ){
        this.ids = ids;
        this.user_id = user_id;
        this.user_mobile_no = user_mobile_no;
        this.LOC_ID = LOC_ID;
        this.Relation = Relation;
        this.Name = Name;
        this.BrideGroom = BrideGroom;
        this.DOW = DOW;
        this.Address = Address;
        this.AddressTo = AddressTo;
    }

    @Ignore
    public WeddingData(String user_id,
                     String user_mobile_no,
                     String LOC_ID,
                       String Name,
                       String BrideGroom,
                       String DOW,
                       String AddressTo,
                       String Relation,
                       String Address){

        this.user_id = user_id;
        this.user_mobile_no = user_mobile_no;
        this.LOC_ID = LOC_ID;
        this.Relation = Relation;
        this.Name = Name;
        this.DOW = DOW;
        this.BrideGroom = BrideGroom;
        this.Address = Address;
        this.AddressTo = AddressTo;

    }
}