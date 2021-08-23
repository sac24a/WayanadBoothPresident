package com.candlestickschart.wayanadboothpresident;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "religion")
public class ReligionData {
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
    @ColumnInfo(name = "Impression")
    public String Impression;
    @ColumnInfo(name = "Connect")
    public String Connect;
    @ColumnInfo(name = "Person")
    public String Person;
    @ColumnInfo(name = "Mobile")
    public String Mobile;

    public ReligionData(int ids,
                         String user_id,
                         String user_mobile_no,
                         String LOC_ID,
                         String Name,
                         String Impression,
                         String Connect,
                         String Person,
                         String Mobile
                        ){
        this.ids = ids;
        this.user_id = user_id;
        this.user_mobile_no = user_mobile_no;
        this.LOC_ID = LOC_ID;
        this.Name = Name;
        this.Impression = Impression;
        this.Connect = Connect;
        this.Person = Person;
        this.Mobile = Mobile;
    }

    @Ignore
    public ReligionData(String user_id,
                        String user_mobile_no,
                        String LOC_ID,
                        String Name,
                        String Impression,
                        String Connect,
                        String Person,
                        String Mobile){
        this.user_id = user_id;
        this.user_mobile_no = user_mobile_no;
        this.LOC_ID = LOC_ID;
        this.Name = Name;
        this.Impression = Impression;
        this.Connect = Connect;
        this.Person = Person;
        this.Mobile = Mobile;

    }
}
