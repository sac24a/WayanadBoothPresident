package com.candlestickschart.wayanadboothpresident;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "social")
public class SocialData {
    @PrimaryKey(autoGenerate = true)
    public int ids;
    @ColumnInfo(name = "user_id")
    public String user_id;
    @ColumnInfo(name = "user_mobile_no")
    public String user_mobile_no;
    @ColumnInfo(name = "LOC_ID")
    public String LOC_ID;
    @ColumnInfo(name = "Social_Type")
    public String Social_Type;
    @ColumnInfo(name = "Name")
    public String Name;
    @ColumnInfo(name = "Gender")
    public String Gender;
    @ColumnInfo(name = "Age")
    public String Age;
    @ColumnInfo(name = "Mobile")
    public String Mobile;
    @ColumnInfo(name = "Party")
    public String Party;

    public SocialData(int ids,
                         String user_id,
                         String user_mobile_no,
                         String LOC_ID,
                         String Social_Type,
                         String Name,
                         String Gender,
                         String Age,
                         String Mobile,String Party){
        this.ids = ids;
        this.user_id = user_id;
        this.user_mobile_no = user_mobile_no;
        this.LOC_ID = LOC_ID;
        this.Social_Type = Social_Type;
        this.Age = Age;
        this.Gender = Gender;
        this.Name = Name;
        this.Mobile = Mobile;
        this.Party = Party;

    }

    @Ignore
    public SocialData(String user_id,
                      String user_mobile_no,
                      String LOC_ID,
                      String Social_Type,
                      String Name,
                      String Gender,
                      String Age,
                      String Mobile,String Party){
        this.user_id = user_id;
        this.user_mobile_no = user_mobile_no;
        this.LOC_ID = LOC_ID;
        this.Social_Type = Social_Type;
        this.Age = Age;
        this.Gender = Gender;
        this.Name = Name;
        this.Mobile = Mobile;
        this.Party = Party;

    }
    @Ignore
    public String getSocialType() {
        return this.Social_Type;
    }
}
