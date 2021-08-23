package com.candlestickschart.wayanadboothpresident;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "FestivalData")
public class FestivalData {
    @PrimaryKey(autoGenerate = true)
    public int ids;
    @ColumnInfo(name = "user_id")
    public String user_id;
    @ColumnInfo(name = "user_mobile_no")
    public String user_mobile_no;
    @ColumnInfo(name = "LOC_ID")
    public String LOC_ID;

    @ColumnInfo(name = "Community")
    public String Community;
    @ColumnInfo(name = "Festival")
    public String Festival;
    @ColumnInfo(name = "Date")
    public String Date;
    public FestivalData(int ids,
                             String user_id,
                             String user_mobile_no,
                             String LOC_ID,
                             String Community,
                             String Festival,
                             String Date

    ){
        this.ids = ids;
        this.user_id = user_id;
        this.user_mobile_no = user_mobile_no;
        this.LOC_ID = LOC_ID;
        this.Community = Community;
        this.Festival = Festival;
        this.Date = Date;
    }

    @Ignore
    public FestivalData(String user_id,
                             String user_mobile_no,
                             String LOC_ID,
                        String Community,
                        String Festival,
                        String Date){

        this.user_id = user_id;
        this.user_mobile_no = user_mobile_no;
        this.LOC_ID = LOC_ID;
        this.Community = Community;
        this.Festival = Festival;
        this.Date = Date;

    }
}