package com.candlestickschart.wayanadboothpresident;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "pollfirst")
public class PollFirstData {
    @PrimaryKey(autoGenerate = true)
    public int ids;
    @ColumnInfo(name = "user_id")
    public String user_id;
    @ColumnInfo(name = "user_mobile_no")
    public String user_mobile_no;
    @ColumnInfo(name = "LOC_ID")
    public String LOC_ID;
    @ColumnInfo(name = "VS_No")
    public String VS_No;
    @ColumnInfo(name = "LS_No")
    public String LS_No;
    @ColumnInfo(name = "Locality_name")
    public String Locality_name;
    @ColumnInfo(name = "Locality_type")
    public String Locality_type;
    @ColumnInfo(name = "Economic")
    public String Economic;
    @ColumnInfo(name = "Employment")
    public String Employment;
    @ColumnInfo(name = "MainCrop")
    public String MainCrop;
    @ColumnInfo(name = "Party_leading")
    public String Party_leading;
    @ColumnInfo(name = "Reason_leading")
    public String Reason_leading;
    @ColumnInfo(name = "Local_issue")
    public String Local_issue;
    @ColumnInfo(name = "Panchayat_analysis")
    public String Panchayat_analysis;
    @ColumnInfo(name = "Cond_UDF")
    public String Cond_UDF;
    @ColumnInfo(name = "Cond_BJP")
    public String Cond_BJP;
    @ColumnInfo(name = "Cond_LDF")
    public String Cond_LDF;
    @ColumnInfo(name = "Cond_OTH")
    public String Cond_OTH;
    @ColumnInfo(name = "Elec_hrs")
    public String Elec_hrs;
    @ColumnInfo(name = "Roads")
    public String Roads;
    @ColumnInfo(name = "Water")
    public String Water;
    @ColumnInfo(name = "Hospital")
    public String Hospital;
    @ColumnInfo(name = "School")
    public String School;
    @ColumnInfo(name = "Emp_opps")
    public String Emp_opps;
    @ColumnInfo(name = "Main_probs")
    public String Main_probs;
    @ColumnInfo(name = "Dev_needs")
    public String Dev_needs;
    public PollFirstData(int ids,
                         String user_id,
                         String user_mobile_no,
                         String LOC_ID,
                         String VS_No,
                         String LS_No,
                         String Locality_name,
                         String Locality_type,
                         String Economic,
                         String Employment,
                         String MainCrop,
                         String Party_leading,
                         String Reason_leading,
                         String Local_issue,
                         String Cond_LDF,
                         String Cond_BJP,
                         String Cond_UDF,
                         String Cond_OTH,
                         String Panchayat_analysis,
                         String Elec_hrs,
                         String Roads,
                         String Water,
                         String Hospital,
                         String School,
                         String Emp_opps,
                         String Main_probs,
                         String Dev_needs){
        this.ids = ids;
        this.user_id = user_id;
        this.user_mobile_no = user_mobile_no;
        this.LOC_ID = LOC_ID;
        this.Dev_needs = Dev_needs;
        this.Main_probs = Main_probs;
        this.Emp_opps = Emp_opps;
        this.School = School;
        this.Hospital = Hospital;
        this.Water = Water;
        this.Roads = Roads;
        this.Elec_hrs = Elec_hrs;
        this.VS_No = VS_No;
        this.Locality_name = Locality_name;
        this.Locality_type = Locality_type;
        this.Economic = Economic;
        this.Employment = Employment;
        this.MainCrop = MainCrop;
        this.Party_leading = Party_leading;
        this.Reason_leading = Reason_leading;
        this.Local_issue = Local_issue;
        this.Panchayat_analysis = Panchayat_analysis;
        this.Cond_UDF= Cond_UDF;
        this.Cond_LDF = Cond_LDF;
        this.Cond_BJP = Cond_BJP;
        this.Cond_OTH = Cond_OTH;
        this.LS_No = LS_No;


    }

    @Ignore
    public PollFirstData(String user_id,
                         String user_mobile_no,
                         String LOC_ID,
                         String VS_No,
                         String LS_No,
                         String Locality_name,
                         String Locality_type,
                         String Economic,
                         String Employment,
                         String MainCrop,
                         String Party_leading,
                         String Reason_leading,
                         String Local_issue,
                         String Panchayat_analysis,
                         String Cond_LDF,
                         String Cond_BJP,
                         String Cond_UDF,
                         String Cond_OTH,
                         String Elec_hrs,
                         String Roads,
                         String Water,
                         String Hospital,
                         String School,
                         String Emp_opps,
                         String Main_probs,
                         String Dev_needs){
        this.user_id = user_id;
        this.user_mobile_no = user_mobile_no;
        this.LOC_ID = LOC_ID;
        this.Dev_needs = Dev_needs;
        this.Main_probs = Main_probs;
        this.Emp_opps = Emp_opps;
        this.School = School;
        this.Hospital = Hospital;
        this.Water = Water;
        this.Roads = Roads;
        this.Elec_hrs = Elec_hrs;
        this.VS_No = VS_No;
        this.LS_No = LS_No;
        this.Locality_name = Locality_name;
        this.Locality_type = Locality_type;
        this.Economic = Economic;
        this.Employment = Employment;
        this.MainCrop = MainCrop;
        this.Party_leading = Party_leading;
        this.Reason_leading = Reason_leading;
        this.Local_issue = Local_issue;
        this.Panchayat_analysis = Panchayat_analysis;
        this.Cond_UDF= Cond_UDF;
        this.Cond_LDF = Cond_LDF;
        this.Cond_BJP = Cond_BJP;
        this.Cond_OTH = Cond_OTH;

    }
}
