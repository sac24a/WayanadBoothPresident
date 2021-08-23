package com.candlestickschart.wayanadboothpresident;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "DeathData")
public class DeathData {
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
    @ColumnInfo(name = "Relation")
    public String Relation;
    @ColumnInfo(name = "Name")
    public String Name;
    @ColumnInfo(name = "DOD")
    public String DOD;
    @ColumnInfo(name = "Cause")
    public String Cause;
    @ColumnInfo(name = "Address")
    public String Address;
    public DeathData(int ids,
                          String user_id,
                          String user_mobile_no,
                          String LOC_ID,
                          String AddressTo,
                          String Relation,
                          String Name,
                          String DOD,
                          String Cause,
                          String Address

    ){
        this.ids = ids;
        this.user_id = user_id;
        this.user_mobile_no = user_mobile_no;
        this.LOC_ID = LOC_ID;
        this.Relation = Relation;
        this.Name = Name;
        this.DOD = DOD;
        this.Cause = Cause;
        this.Address = Address;
        this.AddressTo = AddressTo;
    }

    @Ignore
    public DeathData(String user_id,
                          String user_mobile_no,
                          String LOC_ID,
                     String AddressTo,
                     String Relation,
                     String Name,
                     String DOD,
                     String Cause,
                     String Address){

        this.user_id = user_id;
        this.user_mobile_no = user_mobile_no;
        this.LOC_ID = LOC_ID;
        this.Relation = Relation;
        this.Name = Name;
        this.DOD = DOD;
        this.Cause = Cause;
        this.Address = Address;
        this.AddressTo = AddressTo;

    }
}