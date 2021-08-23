package com.candlestickschart.wayanadboothpresident;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "socialmedia")
public class SocialMediaData {
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
    @ColumnInfo(name = "Gender")
    public String Gender;
    @ColumnInfo(name = "Age")
    public String Age;
    @ColumnInfo(name = "Social_Type")
    public String Social_Type;
    @ColumnInfo(name = "Mobile")
    public String Mobile;
    @ColumnInfo(name = "ActiveOn")
    public String ActiveOn;

    @ColumnInfo(name = "Whatsapp")
    public String Whatsapp;
    @ColumnInfo(name = "Facebook")
    public String Facebook;
    @ColumnInfo(name = "Instagram")
    public String Instagram;
    @ColumnInfo(name = "Twitter")
    public String Twitter;

    public SocialMediaData(int ids,
                         String user_id,
                         String user_mobile_no,
                         String LOC_ID,
                         String Name,
                         String Gender,
                         String Age,
                         String Social_Type,
                         String Mobile,
                           String Whatsapp,
                           String Facebook,
                           String Instagram,
                           String Twitter){
        this.ids = ids;
        this.user_id = user_id;
        this.user_mobile_no = user_mobile_no;
        this.LOC_ID = LOC_ID;
        this.Social_Type = Social_Type;
        this.Age = Age;
        this.Gender = Gender;
        this.Name = Name;
        this.Mobile = Mobile;
        this.Whatsapp = Whatsapp;
        this.Facebook = Facebook;
        this.Instagram = Instagram;
        this.Twitter = Twitter;


    }

    @Ignore
    public SocialMediaData(String user_id,
                           String user_mobile_no,
                           String LOC_ID,
                           String Name,
                           String Gender,
                           String Age,
                           String Social_Type,
                           String Mobile,
                           String Whatsapp,
                           String Facebook,
                           String Instagram,
                           String Twitter){
        this.user_id = user_id;
        this.user_mobile_no = user_mobile_no;
        this.LOC_ID = LOC_ID;
        this.Social_Type = Social_Type;
        this.Age = Age;
        this.Gender = Gender;
        this.Name = Name;
        this.Mobile = Mobile;
        this.Whatsapp = Whatsapp;
        this.Facebook = Facebook;
        this.Instagram = Instagram;
        this.Twitter = Twitter;

    }
}
