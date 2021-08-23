package com.candlestickschart.wayanadboothpresident;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PollFirstDao {
    @Query("Select * from pollfirst")
    List<PollFirstData> getPollfirstData();
    @Query("Select Locality_type from pollfirst")
    List<String> getlocalityType();
    @Query("Select Panchayat_analysis from pollfirst")
    List<String> checkLocalGroup();
    @Query("Select Dev_needs from pollfirst")
    List<String> checkDevNeeds();

    @Query("Select Cond_OTH from pollfirst")
    List<String> checkOtherCond();


    @Query("DELETE FROM pollfirst")
    void clearPollfirstTable();
    @Insert
    void insertPollFirst(PollFirstData pollFirstData);

    @Query("UPDATE pollfirst SET Locality_type=:locality_type,Employment=:employment, Economic=:economic, MainCrop=:maincrop WHERE LOC_ID = :locId")
    void updateScreen3(String locality_type,String employment,String economic,String maincrop,String locId);

    @Query("UPDATE pollfirst SET Party_leading=:leading, Reason_leading=:rason, Local_issue=:facts WHERE LOC_ID = :locId")
    void updateScreen4(String leading,String rason,String facts,String locId);

    @Query("UPDATE pollfirst SET Cond_UDF=:udp, Cond_LDF=:ldp,Cond_BJP=:bjp, Cond_OTH=:other WHERE LOC_ID = :locId")
    void updateScreen4_1(String udp,String ldp,String bjp,String other,String locId);

    @Query("UPDATE pollfirst SET Panchayat_analysis=:reason WHERE LOC_ID = :locId")
    void updateScreen5(String reason,String locId);

    @Query("UPDATE pollfirst SET Elec_hrs=:bijli, Roads=:road, Water=:water, Hospital=:hospital, School=:school WHERE LOC_ID = :locId")
    void updateScreen6(String bijli,String road,String water,String hospital,String school,String locId);

    @Query("UPDATE pollfirst SET Emp_opps=:emp_opps, Main_probs=:mainprob, Dev_needs=:devneed WHERE LOC_ID = :locId")
    void updateScreen6_1(String emp_opps,String mainprob,String devneed,String locId);


    /* Social Table*/
    @Insert
    void insertSocialImpPersion(SocialData pollFirstData);
    @Query("Select * From social")
    List<SocialData> getSocialType();
    @Query("Select Name From social")
    List<String> getSocialPersonName();
    @Query("Select Social_Type From social Where Social_Type Like:socialType")
    List<String> checkSocialData( String socialType);
    @Query("DELETE FROM social")
    void clearSocialTable();

    /* Validate mobile*/
    @Query("Select Mobile From social Where Mobile Like:searchType")
    List<String> checkSocialPersonMobile(String searchType);
    /* Update Data*/
    @Query("UPDATE social SET Social_Type=:social, Name=:name, Gender=:gender, Age=:age, Mobile=:mobile,Party=:party  WHERE Mobile = :mobile")
    void updateSocialPerson(String social,String name,String gender,String age,String mobile,String party);
    /* Person Data*/
    @Query("Select * From social")
    List<SocialData> getSocialPerson();



    /* Political Worker Table*/
    /* Insert Query*/
    @Insert
    void insertPoliticalWorker(PoliticalWorkerData pollFirstData);
    /* Worker Data*/
    @Query("Select * From politicalworker")
    List<PoliticalWorkerData> getPoliticalWorker();
    /* Worker Data Partywise*/
    @Query("Select Party_Name From politicalworker Where Party_Name Like:searchType")
    List<String> checkPoliticalWorker( String searchType);
    /* Delete All Political Worker*/
    @Query("DELETE FROM politicalworker")
    void clearPoliticalWorkerTable();
    /* Worker Name Partywise*/
    @Query("Select Name From politicalworker Where Party_Name Like:searchType")
    List<String> getPoliticalWorkerName(String searchType);
    /*Worker Data Partywise*/
    @Query("Select * From politicalworker Where Party_Name Like:searchType")
    List<PoliticalWorkerData> getPoliticalWorkerData( String searchType);
    /* Validate Mobile Number*/
    @Query("Select Mobile From politicalworker Where Mobile Like:searchType")
    List<String> checkPoliticalWorkerMobile(String searchType);
    /* Update Data*/
    @Query("UPDATE politicalworker SET Party_Name=:party, Name=:name, Gender=:gender, Age=:age, Mobile=:mobile, Social_Type=:social, Party=:favparty, Impression=:impression WHERE Mobile = :mobile")
    void updatePoliticalWorker(String party,String name,String gender,String age,String mobile,String social,String favparty,String impression);


    /* Imp PersonTable*/
    /* Insert*/
    @Insert
    void insertImpPerson(ImpPersonData pollFirstData);
    /* Person Name*/
    @Query("Select Name From person")
    List<String> getImpPersonName();
    /* Person Data*/
    @Query("Select * From person")
    List<ImpPersonData> getImpPerson();
    /* Delete Person Data*/
    @Query("DELETE FROM person")
    void clearPersonTable();
    /* Validate mobile*/
    @Query("Select Mobile From person Where Mobile Like:searchType")
    List<String> checkImpPersonMobile(String searchType);
    /* Update Data*/
    @Query("UPDATE person SET Party=:party, Name=:name, Gender=:gender, Age=:age, Mobile=:mobile, Social_Type=:social, Impression=:impression, Reason=:reason WHERE Mobile = :mobile")
    void updateImpWorker(String party,String name,String gender,String age,String mobile,String social,String impression,String reason);

    /* Religion Table*/
    @Insert
    void insertReligion(ReligionData pollFirstData);
    @Query("Select Name From religion")
    List<String> getReligionName();
    @Query("Select * From religion")
    List<ReligionData> getReligion();
    @Query("DELETE FROM religion")
    void clearReligionTable();
    /* Validate mobile*/
    @Query("Select Mobile From religion Where Mobile Like:searchType")
    List<String> checkReligionMobile(String searchType);
    /* Update Data*/
    @Query("UPDATE religion SET Name=:sanstha, Impression=:impression, Connect=:connect, Person=:person, Mobile=:mobile WHERE Mobile = :mobile")
    void updateReligion(String sanstha,String impression,String connect,String person,String mobile);


    /* Social media Table*/
    @Insert
    void insertSocialMedia(SocialMediaData pollFirstData);
    @Query("Select Name From socialmedia")
    List<String> getSocialMediaName();
    @Query("Select * From socialmedia")
    List<SocialMediaData> getSocialMedia();
    @Query("DELETE FROM socialmedia")
    void clearSocialMediaTable();
    /* Validate mobile*/
    @Query("Select Mobile From socialmedia Where Mobile Like:searchType")
    List<String> checkSocialMediaMobile(String searchType);
    /* Update Data*/
    @Query("UPDATE socialmedia SET Name=:name, Gender=:gender, Age=:age, Social_Type=:social, Mobile=:mobile, ActiveOn=:active, Whatsapp=:whatsapp, Facebook=:facebook, Twitter=:twitter, Instagram=:insta WHERE Mobile = :mobile")
    void updateSocialMedia(String name,String gender,String age,String social,String mobile,String active,String whatsapp,String facebook,String twitter,String insta);

    /* Booth Agent Table*/
    @Insert
    void insertBoothAgent(BoothAgent pollFirstData);
    @Query("Select Party From boothagent Where Party Like:searchType")
    List<String> checkBoothAgent( String searchType);
    @Query("Select * From boothagent")
    List<BoothAgent> getBoothAgent();
    @Query("DELETE FROM boothagent")
    void clearBoothAgentTable();
    @Query("Select Name From boothagent Where Party Like:searchType" )
    List<String> getBoothAgentName(String searchType);
    /*Worker Data Partywise*/
    @Query("Select * From boothagent Where Party Like:searchType")
    List<BoothAgent> getBoothAgentData( String searchType);
    /* Validate Mobile Number*/
    @Query("Select Mobile From boothagent Where Mobile Like:searchType")
    List<String> checkBoothAgentMobile(String searchType);
    /* Update Data*/
    @Query("UPDATE boothagent SET BoothNumber=:booth, Party=:party, Name=:name, Gender=:gender, Age=:age, Mobile=:mobile, Social_Type=:favparty WHERE Mobile = :mobile")
    void updateBoothAgent(String booth,String party,String name,String gender,String age,String mobile,String favparty);



    /* Employee Table*/
    @Insert
    void insertEmployee(EmployeeData pollFirstData);
    @Query("Select Name From employee")
    List<String> getEmployeeName();
    @Query("Select * From employee")
    List<EmployeeData> getEmployee();
    @Query("DELETE FROM employee")
    void clearEmployeeTable();
    /* Validate mobile*/
    @Query("Select Mobile From employee Where Mobile Like:searchType")
    List<String> checkEmployeeMobile(String searchType);
    /* Update Data*/
    @Query("UPDATE employee SET Name=:name, Gender=:gender, Age=:age, Social_Type=:social, Mobile=:mobile, Position=:position WHERE Mobile = :mobile")
    void updateEmployee(String name,String gender,String age,String social,String mobile,String position);

    /* Achievement Table*/
    @Insert
    void insertAchievement(AchievementData pollFirstData);
    @Query("Select Name From achievement")
    List<String> getAchievementName();
    @Query("Select * From achievement")
    List<AchievementData> getAchievement();
    @Query("DELETE FROM achievement")
    void clearAchievementTable();
    /* Update Data*/
//    @Query("UPDATE achievement SET Name=:name, Gender=:gender, Age=:age, Social_Type=:social, Mobile=:mobile, Position=:position WHERE Mobile = :mobile")
//    void updateEmployee(String addressto,String name,String relation,String detail,String assistance,String address);

    /* SpecialLetter Table*/
    @Insert
    void insertSpecialLetter(SpecialLetterData pollFirstData);
    @Query("Select AddressTo From specialletter")
    List<String> getSpecialLetterName();
    @Query("Select * From specialletter")
    List<SpecialLetterData> getSpecialLetter();
    @Query("DELETE FROM specialletter")
    void clearSpecialLetterTable();

    /* ChildBirth Table*/
    @Insert
    void insertChildBirth(ChildBirthData pollFirstData);
    @Query("Select Father_Name From ChildBirthData")
    List<String> getChildBrithName();
    @Query("Select * From ChildBirthData")
    List<ChildBirthData> getChildBrithData();
    @Query("DELETE FROM ChildBirthData")
    void clearChildBirthTable();

    /* Death Table*/
    @Insert
    void insertDeath(DeathData pollFirstData);
    @Query("Select Name From DeathData")
    List<String> getDeathName();
    @Query("Select * From DeathData")
    List<DeathData> getDeathData();
    @Query("DELETE FROM DeathData")
    void clearDeathData();

    /* Festival Table*/
    @Insert
    void insertFestival(FestivalData pollFirstData);
    @Query("Select Festival From FestivalData")
    List<String> getFestivalName();
    @Query("Select * From FestivalData")
    List<FestivalData> getFestival();
    @Query("DELETE FROM FestivalData")
    void clearFestivalData();

    /* Wedding Table*/
    @Insert
    void insertWedding(WeddingData pollFirstData);
    @Query("Select Name From WeddingData")
    List<String> getWeddingName();
    @Query("Select * From WeddingData")
    List<WeddingData> getWeddingData();
    @Query("DELETE FROM WeddingData")
    void clearWeddingData();

    @Delete
    void deletePollFirst(PollFirstData pollFirstData);
}
