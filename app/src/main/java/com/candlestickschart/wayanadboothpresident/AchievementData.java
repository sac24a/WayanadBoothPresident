package com.candlestickschart.wayanadboothpresident;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "achievement")
public class AchievementData {
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
    @ColumnInfo(name = "Name")
    public String Name;
    @ColumnInfo(name = "Relation")
    public String Relation;
    @ColumnInfo(name = "Detail")
    public String Detail;
    @ColumnInfo(name = "Assistance")
    public String Assistance;
    @ColumnInfo(name = "Address")
    public String Address;
    public AchievementData(int ids,
                      String user_id,
                      String user_mobile_no,
                      String LOC_ID,
                      String AddressTo,
                      String Name,
                      String Relation,
                      String Detail,
                      String Assistance,
                      String Address

    ){
        this.ids = ids;
        this.user_id = user_id;
        this.user_mobile_no = user_mobile_no;
        this.LOC_ID = LOC_ID;
        this.AddressTo = AddressTo;
        this.Relation = Relation;
        this.Detail = Detail;
        this.Assistance = Assistance;
        this.Address = Address;
        this.Name = Name;
    }

    @Ignore
    public AchievementData(String user_id,
                           String user_mobile_no,
                           String LOC_ID,
                           String AddressTo,
                           String Name,
                           String Relation,
                           String Detail,
                           String Assistance,
                           String Address){

        this.user_id = user_id;
        this.user_mobile_no = user_mobile_no;
        this.LOC_ID = LOC_ID;
        this.AddressTo = AddressTo;
        this.Relation = Relation;
        this.Detail = Detail;
        this.Assistance = Assistance;
        this.Address = Address;
        this.Name = Name;

    }
}