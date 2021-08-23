package com.candlestickschart.wayanadboothpresident;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "ChildBirthData")
public class ChildBirthData {
    @PrimaryKey(autoGenerate = true)
    public int ids;
    @ColumnInfo(name = "user_id")
    public String user_id;
    @ColumnInfo(name = "user_mobile_no")
    public String user_mobile_no;
    @ColumnInfo(name = "LOC_ID")
    public String LOC_ID;

    @ColumnInfo(name = "ChildType")
    public String ChildType;
    @ColumnInfo(name = "Mother_Name")
    public String Mother_Name;
    @ColumnInfo(name = "Father_Name")
    public String Father_Name;
    @ColumnInfo(name = "DOB")
    public String DOB;
    @ColumnInfo(name = "AddressTo")
    public String AddressTo;
    @ColumnInfo(name = "Address")
    public String Address;
    public ChildBirthData(int ids,
                           String user_id,
                           String user_mobile_no,
                           String LOC_ID,
                           String ChildType,
                           String Mother_Name,
                           String Father_Name,
                           String DOB,
                           String AddressTo,
                           String Address

    ){
        this.ids = ids;
        this.user_id = user_id;
        this.user_mobile_no = user_mobile_no;
        this.LOC_ID = LOC_ID;
        this.ChildType = ChildType;
        this.Mother_Name = Mother_Name;
        this.Father_Name = Father_Name;
        this.DOB = DOB;
        this.Address = Address;
        this.AddressTo = AddressTo;
    }

    @Ignore
    public ChildBirthData(String user_id,
                           String user_mobile_no,
                           String LOC_ID,
                          String ChildType,
                          String Mother_Name,
                          String Father_Name,
                          String DOB,
                          String AddressTo,
                          String Address){

        this.user_id = user_id;
        this.user_mobile_no = user_mobile_no;
        this.LOC_ID = LOC_ID;
        this.ChildType = ChildType;
        this.Mother_Name = Mother_Name;
        this.Father_Name = Father_Name;
        this.DOB = DOB;
        this.Address = Address;
        this.AddressTo = AddressTo;

    }
}
