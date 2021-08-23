package com.candlestickschart.wayanadboothpresident;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "specialletter")
public class SpecialLetterData {
    @PrimaryKey(autoGenerate = true)
    public int ids;
    @ColumnInfo(name = "user_id")
    public String user_id;
    @ColumnInfo(name = "user_mobile_no")
    public String user_mobile_no;
    @ColumnInfo(name = "LOC_ID")
    public String LOC_ID;

    @ColumnInfo(name = "AddressTo")
    public String AddressTo;
    @ColumnInfo(name = "Issue")
    public String Issue;
    @ColumnInfo(name = "Address")
    public String Address;
    public SpecialLetterData(int ids,
                           String user_id,
                           String user_mobile_no,
                           String LOC_ID,
                           String AddressTo,
                           String Issue,
                           String Address

    ){
        this.ids = ids;
        this.user_id = user_id;
        this.user_mobile_no = user_mobile_no;
        this.LOC_ID = LOC_ID;
        this.AddressTo = AddressTo;
        this.Issue = Issue;
        this.Address = Address;
    }

    @Ignore
    public SpecialLetterData(String user_id,
                             String user_mobile_no,
                             String LOC_ID,
                             String AddressTo,
                             String Issue,
                             String Address){

        this.user_id = user_id;
        this.user_mobile_no = user_mobile_no;
        this.LOC_ID = LOC_ID;
        this.AddressTo = AddressTo;
        this.Issue = Issue;
        this.Address = Address;

    }
}