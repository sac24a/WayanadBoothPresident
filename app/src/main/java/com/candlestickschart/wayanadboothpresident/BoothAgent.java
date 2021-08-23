package com.candlestickschart.wayanadboothpresident;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "boothagent")
public class BoothAgent {
    @PrimaryKey(autoGenerate = true)
    public int ids;
    @ColumnInfo(name = "user_id")
    public String user_id;
    @ColumnInfo(name = "user_mobile_no")
    public String user_mobile_no;
    @ColumnInfo(name = "LOC_ID")
    public String LOC_ID;

    @ColumnInfo(name = "BoothNumber")
    public String BoothNumber;
    @ColumnInfo(name = "Party")
    public String Party;
    @ColumnInfo(name = "Name")
    public String Name;
    @ColumnInfo(name = "Social_Type")
    public String Social_Type;
    @ColumnInfo(name = "Gender")
    public String Gender;
    @ColumnInfo(name = "Age")
    public String Age;

    @ColumnInfo(name = "Mobile")
    public String Mobile;

    public BoothAgent(int ids,
                         String user_id,
                         String user_mobile_no,
                         String LOC_ID,
                         String BoothNumber,
                         String Party,
                         String Name,
                         String Social_Type,
                         String Gender,
                         String Age,
                         String Mobile
                         ){
        this.ids = ids;
        this.user_id = user_id;
        this.user_mobile_no = user_mobile_no;
        this.LOC_ID = LOC_ID;
        this.Social_Type = Social_Type;
        this.BoothNumber = BoothNumber;
        this.Party = Party;
        this.Age = Age;
        this.Gender = Gender;
        this.Name = Name;
        this.Mobile = Mobile;
    }

    @Ignore
    public BoothAgent(String user_id,
                      String user_mobile_no,
                      String LOC_ID,
                      String BoothNumber,
                      String Party,
                      String Name,
                      String Social_Type,
                      String Gender,
                      String Age,
                      String Mobile){
        this.user_id = user_id;
        this.user_id = user_id;
        this.user_mobile_no = user_mobile_no;
        this.LOC_ID = LOC_ID;
        this.Social_Type = Social_Type;
        this.BoothNumber = BoothNumber;
        this.Party = Party;
        this.Age = Age;
        this.Gender = Gender;
        this.Name = Name;
        this.Mobile = Mobile;

    }
}
