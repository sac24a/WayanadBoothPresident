package com.candlestickschart.wayanadboothpresident;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class LocalityOptions extends AppCompatActivity {

    Button localityDetails;
    Button infoUpdates;
    Button upload;
    Button logout;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locality_options);
        localityDetails = findViewById(R.id.localityDetails);
        infoUpdates = findViewById(R.id.impUpdates);
        upload = findViewById(R.id.upload);
        logout = findViewById(R.id.logout);
        progressBar = findViewById(R.id.progressBar);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        localityDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LocalityOptions.this,Screen3.class);
                startActivity(intent);
            }
        });
        infoUpdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LocalityOptions.this,InfoUpdate.class);
                startActivity(intent);
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpUpload();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(LocalityOptions.this);
                builder1.setMessage("Do you want to logout? This will clear all the data.");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                editor.clear().apply();
                                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(LocalityOptions.this);
                                        pollFirstDataBase.clearAllTables();
                                        if (pollFirstDataBase.pollFirstDao().getPollfirstData().size()==0) {
                                            AppExecutors.getInstance().mainThread().execute(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Intent intent = new Intent(LocalityOptions.this,LoginScreen.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            });
                                        }

                                    }
                                });

                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

    }

    public void setUpUpload() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(LocalityOptions.this);
                List<PollFirstData> pollFirstData = pollFirstDataBase.pollFirstDao().getPollfirstData();
                List<SocialData> socialData = pollFirstDataBase.pollFirstDao().getSocialType();
                List<PoliticalWorkerData> politicalWorkerData = pollFirstDataBase.pollFirstDao().getPoliticalWorker();
                List<ImpPersonData> impPerson = pollFirstDataBase.pollFirstDao().getImpPerson();
                List<ReligionData> religionData = pollFirstDataBase.pollFirstDao().getReligion();
                List<SocialMediaData> socialMediaData = pollFirstDataBase.pollFirstDao().getSocialMedia();
                List<BoothAgent> boothAgentData = pollFirstDataBase.pollFirstDao().getBoothAgent();
                List<EmployeeData> employeeData = pollFirstDataBase.pollFirstDao().getEmployee();

                List<FestivalData> festivalData = pollFirstDataBase.pollFirstDao().getFestival();
                List<ChildBirthData> childBirthData = pollFirstDataBase.pollFirstDao().getChildBrithData();
                List<DeathData> deathData = pollFirstDataBase.pollFirstDao().getDeathData();
                List<SpecialLetterData> specialLetterData = pollFirstDataBase.pollFirstDao().getSpecialLetter();
                List<AchievementData> achievementData = pollFirstDataBase.pollFirstDao().getAchievement();
                List<WeddingData> weddingData = pollFirstDataBase.pollFirstDao().getWeddingData();

                JSONObject jsonObject = new JSONObject();
                JSONArray socialArray = new JSONArray();
                JSONArray politicalArray = new JSONArray();
                JSONArray impPersonArray = new JSONArray();
                JSONArray religionArray = new JSONArray();
                JSONArray socialMediaArray = new JSONArray();
                JSONArray boothAgentArray = new JSONArray();
                JSONArray employeeArray = new JSONArray();
                JSONArray festivalArray = new JSONArray();
                JSONArray childbirthArray = new JSONArray();
                JSONArray deathArray = new JSONArray();
                JSONArray specialLetterArray = new JSONArray();
                JSONArray achievementArray = new JSONArray();
                JSONArray weddingArray = new JSONArray();
                JSONObject jsonToUpload = new JSONObject();

                try {
                    jsonObject.put("VS_No",pollFirstData.get(0).VS_No);
//                    jsonObject.put("LS_No",pollFirstData.get(0).LS_No);
                    jsonObject.put("user_id",pollFirstData.get(0).user_id);
                    jsonObject.put("user_mobile_no",pollFirstData.get(0).user_mobile_no);
                    jsonObject.put("LOC_ID",pollFirstData.get(0).LOC_ID);
                    jsonObject.put("Locality_name",pollFirstData.get(0).Locality_name);
                    jsonObject.put("Area_type",pollFirstData.get(0).Locality_type);
                    jsonObject.put("Emp_Opportunity",pollFirstData.get(0).Employment);
                    jsonObject.put("Emp_Source",pollFirstData.get(0).Economic);
                    jsonObject.put("Main_Crop",pollFirstData.get(0).Economic);
                    jsonObject.put("Party_leading",pollFirstData.get(0).Party_leading);
                    jsonObject.put("Reason_leading",pollFirstData.get(0).Reason_leading);
                    jsonObject.put("Local_issue",pollFirstData.get(0).Local_issue);
                    jsonObject.put("Local_Groups",pollFirstData.get(0).Panchayat_analysis);
                    jsonObject.put("Cond_UDF",pollFirstData.get(0).Cond_UDF);
                    jsonObject.put("Cond_BJP",pollFirstData.get(0).Cond_BJP);
                    jsonObject.put("Cond_LDF",pollFirstData.get(0).Cond_LDF);
                    jsonObject.put("Cond_OTH",pollFirstData.get(0).Cond_OTH);
                    jsonObject.put("Elec_hrs",pollFirstData.get(0).Elec_hrs);
                    jsonObject.put("Roads",pollFirstData.get(0).Roads);
                    jsonObject.put("Water",pollFirstData.get(0).Water);
                    jsonObject.put("Hospital",pollFirstData.get(0).Hospital);
                    jsonObject.put("School",pollFirstData.get(0).School);
                    jsonObject.put("Emp_opps",pollFirstData.get(0).Emp_opps);
                    jsonObject.put("Main_probs",pollFirstData.get(0).Main_probs);
                    jsonObject.put("Dev_needs",pollFirstData.get(0).Dev_needs);
                    for (int i = 0;i<socialData.size();i++) {
                        JSONObject socialJson = new JSONObject();
                        socialJson.put("user_id",socialData.get(i).user_id);
                        socialJson.put("VS_No",pollFirstData.get(0).VS_No);
//                        socialJson.put("LS_No",pollFirstData.get(0).LS_No);
                        socialJson.put("user_mobile_no",socialData.get(i).user_mobile_no);
                        socialJson.put("LOC_ID",socialData.get(i).LOC_ID);
                        socialJson.put("Community",socialData.get(i).Social_Type);
                        socialJson.put("Name",socialData.get(i).Name);
                        socialJson.put("Gender",socialData.get(i).Gender);
                        socialJson.put("Age",socialData.get(i).Age);
                        socialJson.put("Mobile",socialData.get(i).Mobile);
                        socialJson.put("Party",socialData.get(i).Party);
                        socialArray.put(socialJson);
                    }
                    for (int i = 0;i<politicalWorkerData.size();i++) {
                        JSONObject politicalJson = new JSONObject();
                        politicalJson.put("user_id",politicalWorkerData.get(i).user_id);
                        politicalJson.put("VS_No",pollFirstData.get(0).VS_No);
//                        politicalJson.put("LS_No",pollFirstData.get(0).LS_No);
                        politicalJson.put("user_mobile_no",politicalWorkerData.get(i).user_mobile_no);
                        politicalJson.put("LOC_ID",politicalWorkerData.get(i).LOC_ID);
                        politicalJson.put("Community",politicalWorkerData.get(i).Social_Type);
                        politicalJson.put("Impression",politicalWorkerData.get(i).Impression);
                        politicalJson.put("Name",politicalWorkerData.get(i).Name);
                        politicalJson.put("Gender",politicalWorkerData.get(i).Gender);
                        politicalJson.put("Age",politicalWorkerData.get(i).Age);
                        politicalJson.put("Mobile",politicalWorkerData.get(i).Mobile);
                        politicalJson.put("Party_Name",politicalWorkerData.get(i).Party_Name);
                        politicalJson.put("Party",politicalWorkerData.get(i).Party);
                        politicalArray.put(politicalJson);
                    }
                    for (int i = 0;i<impPerson.size();i++) {
                        JSONObject impPersonJson = new JSONObject();
                        impPersonJson.put("user_id",impPerson.get(i).user_id);
                        impPersonJson.put("VS_No",pollFirstData.get(0).VS_No);
//                        impPersonJson.put("LS_No",pollFirstData.get(0).LS_No);
                        impPersonJson.put("user_mobile_no",impPerson.get(i).user_mobile_no);
                        impPersonJson.put("LOC_ID",impPerson.get(i).LOC_ID);
                        impPersonJson.put("Community",impPerson.get(i).Social_Type);
                        impPersonJson.put("Impression",impPerson.get(i).Impression);
                        impPersonJson.put("Name",impPerson.get(i).Name);
                        impPersonJson.put("Gender",impPerson.get(i).Gender);
                        impPersonJson.put("Age",impPerson.get(i).Age);
                        impPersonJson.put("Mobile",impPerson.get(i).Mobile);
                        impPersonJson.put("Reason",impPerson.get(i).Reason);
                        impPersonJson.put("Party",impPerson.get(i).Party);
                        impPersonArray.put(impPersonJson);
                    }
                    for (int i = 0;i<religionData.size();i++) {
                        JSONObject religionJson = new JSONObject();
                        religionJson.put("user_id",religionData.get(i).user_id);
                        religionJson.put("VS_No",pollFirstData.get(0).VS_No);
//                        religionJson.put("LS_No",pollFirstData.get(0).LS_No);
                        religionJson.put("user_mobile_no",religionData.get(i).user_mobile_no);
                        religionJson.put("LOC_ID",religionData.get(i).LOC_ID);
                        religionJson.put("Impact",religionData.get(i).Impression);
                        religionJson.put("Name",religionData.get(i).Name);
                        religionJson.put("Support",religionData.get(i).Connect);
                        religionJson.put("Person",religionData.get(i).Person);
                        religionJson.put("Mobile",religionData.get(i).Mobile);
                        religionArray.put(religionJson);
                    }
                    for (int i = 0;i<socialMediaData.size();i++) {
                        JSONObject socialMediaJson = new JSONObject();
                        socialMediaJson.put("user_id",socialMediaData.get(i).user_id);
                        socialMediaJson.put("VS_No",pollFirstData.get(0).VS_No);
//                        socialMediaJson.put("LS_No",pollFirstData.get(0).LS_No);
                        socialMediaJson.put("user_mobile_no",socialMediaData.get(i).user_mobile_no);
                        socialMediaJson.put("LOC_ID",socialMediaData.get(i).LOC_ID);
                        socialMediaJson.put("Community",socialMediaData.get(i).Social_Type);
                        socialMediaJson.put("Name",socialMediaData.get(i).Name);
                        socialMediaJson.put("Gender",socialMediaData.get(i).Gender);
                        socialMediaJson.put("Age",socialMediaData.get(i).Age);
                        socialMediaJson.put("Mobile",socialMediaData.get(i).Mobile);
                        socialMediaJson.put("Whatsapp",socialMediaData.get(i).Whatsapp);
                        socialMediaJson.put("FB",socialMediaData.get(i).Facebook);
                        socialMediaJson.put("Insta",socialMediaData.get(i).Instagram);
                        socialMediaJson.put("Twitter",socialMediaData.get(i).Twitter);
                        socialMediaArray.put(socialMediaJson);

                    }
                    for (int i = 0;i<boothAgentData.size();i++) {
                        JSONObject boothAgentJson = new JSONObject();
                        boothAgentJson.put("user_id",boothAgentData.get(i).user_id);
                        boothAgentJson.put("VS_No",pollFirstData.get(0).VS_No);
//                        boothAgentJson.put("LS_No",pollFirstData.get(0).LS_No);
                        boothAgentJson.put("user_mobile_no",boothAgentData.get(i).user_mobile_no);
                        boothAgentJson.put("LOC_ID",boothAgentData.get(i).LOC_ID);
                        boothAgentJson.put("Party_Leaning",boothAgentData.get(i).Social_Type);
                        boothAgentJson.put("BoothNumber",boothAgentData.get(i).BoothNumber);
                        boothAgentJson.put("Name",boothAgentData.get(i).Name);
                        boothAgentJson.put("Gender",boothAgentData.get(i).Gender);
                        boothAgentJson.put("Age",boothAgentData.get(i).Age);
                        boothAgentJson.put("Mobile",boothAgentData.get(i).Mobile);
                        boothAgentJson.put("Party",boothAgentData.get(i).Party);
                        boothAgentArray.put(boothAgentJson);
                    }
                    for (int i = 0;i<employeeData.size();i++) {
                        JSONObject employeeJson = new JSONObject();
                        employeeJson.put("user_id",employeeData.get(i).user_id);
                        employeeJson.put("VS_No",pollFirstData.get(0).VS_No);
//                        employeeJson.put("LS_No",pollFirstData.get(0).LS_No);
                        employeeJson.put("user_mobile_no",employeeData.get(i).user_mobile_no);
                        employeeJson.put("LOC_ID",employeeData.get(i).LOC_ID);
                        employeeJson.put("Community",employeeData.get(i).Social_Type);
                        employeeJson.put("Name",employeeData.get(i).Name);
                        employeeJson.put("Gender",employeeData.get(i).Gender);
                        employeeJson.put("Age",employeeData.get(i).Age);
                        employeeJson.put("Mobile",employeeData.get(i).Mobile);
                        employeeJson.put("Position",employeeData.get(i).Position);
                        employeeArray.put(employeeJson);
                    }
                    for (int i = 0;i<festivalData.size();i++) {
                        JSONObject festivalJson = new JSONObject();
                        festivalJson.put("user_id",pollFirstData.get(i).user_id);
                        festivalJson.put("VS_No",pollFirstData.get(0).VS_No);
//                        festivalJson.put("LS_No",pollFirstData.get(0).LS_No);
                        festivalJson.put("user_mobile_no",pollFirstData.get(i).user_mobile_no);
                        festivalJson.put("LOC_ID",pollFirstData.get(i).LOC_ID);
                        festivalJson.put("Community",festivalData.get(i).Community);
                        festivalJson.put("Festival",festivalData.get(i).Festival);
                        festivalJson.put("Date",festivalData.get(i).Date);
                        festivalArray.put(festivalJson);
                    }
                    for (int i = 0;i<childBirthData.size();i++) {
                        JSONObject birthJson = new JSONObject();
                        birthJson.put("user_id",pollFirstData.get(i).user_id);
                        birthJson.put("VS_No",pollFirstData.get(0).VS_No);
//                        birthJson.put("LS_No",pollFirstData.get(0).LS_No);
                        birthJson.put("user_mobile_no",pollFirstData.get(i).user_mobile_no);
                        birthJson.put("LOC_ID",pollFirstData.get(i).LOC_ID);
                        birthJson.put("Child_Type",childBirthData.get(i).ChildType);
                        birthJson.put("Mother_Name",childBirthData.get(i).Mother_Name);
                        birthJson.put("Father_Name",childBirthData.get(i).Father_Name);
                        birthJson.put("DOB",childBirthData.get(i).DOB);
                        birthJson.put("Address_To",childBirthData.get(i).AddressTo);
                        birthJson.put("Address",childBirthData.get(i).Address);
                        childbirthArray.put(birthJson);
                    }
                    for (int i = 0;i<weddingData.size();i++) {
                        JSONObject weddingJson = new JSONObject();
                        weddingJson.put("user_id",pollFirstData.get(i).user_id);
                        weddingJson.put("VS_No",pollFirstData.get(0).VS_No);
//                        weddingJson.put("LS_No",pollFirstData.get(0).LS_No);
                        weddingJson.put("user_mobile_no",pollFirstData.get(i).user_mobile_no);
                        weddingJson.put("LOC_ID",pollFirstData.get(i).LOC_ID);
                        weddingJson.put("Bride_Name",weddingData.get(i).Name);
                        weddingJson.put("BrideGroom_Name",weddingData.get(i).BrideGroom);
                        weddingJson.put("Rel_Bride",weddingData.get(i).Relation);
                        weddingJson.put("DOW",weddingData.get(i).DOW);
                        weddingJson.put("Address_To",weddingData.get(i).AddressTo);
                        weddingJson.put("Address",weddingData.get(i).Address);
                        weddingArray.put(weddingJson);
                    }
                    for (int i = 0;i<deathData.size();i++) {
                        JSONObject deathJson = new JSONObject();
                        deathJson.put("user_id",pollFirstData.get(i).user_id);
                        deathJson.put("VS_No",pollFirstData.get(0).VS_No);
//                        deathJson.put("LS_No",pollFirstData.get(0).LS_No);
                        deathJson.put("user_mobile_no",pollFirstData.get(i).user_mobile_no);
                        deathJson.put("LOC_ID",pollFirstData.get(i).LOC_ID);
                        deathJson.put("Deceased_Name",deathData.get(i).Name);
                        deathJson.put("Cause",deathData.get(i).Cause);
                        deathJson.put("Rel_Deceased",deathData.get(i).Relation);
                        deathJson.put("DOD",deathData.get(i).DOD);
                        deathJson.put("Address_To",deathData.get(i).AddressTo);
                        deathJson.put("Address",deathData.get(i).Address);
                        deathArray.put(deathJson);
                    }
                    for (int i = 0;i<specialLetterData.size();i++) {
                        JSONObject specialJson = new JSONObject();
                        specialJson.put("user_id",pollFirstData.get(i).user_id);
                        specialJson.put("VS_No",pollFirstData.get(0).VS_No);
//                        specialJson.put("LS_No",pollFirstData.get(0).LS_No);
                        specialJson.put("user_mobile_no",pollFirstData.get(i).user_mobile_no);
                        specialJson.put("LOC_ID",pollFirstData.get(i).LOC_ID);
                        specialJson.put("Address_To",specialLetterData.get(i).AddressTo);
                        specialJson.put("Address",specialLetterData.get(i).Address);
                        specialJson.put("Issue",specialLetterData.get(i).Issue);

                        specialLetterArray.put(specialJson);
                    }
                    for (int i = 0;i<achievementData.size();i++) {
                        JSONObject achievementJson = new JSONObject();
                        achievementJson.put("user_id",pollFirstData.get(i).user_id);
                        achievementJson.put("VS_No",pollFirstData.get(0).VS_No);
//                        achievementJson.put("LS_No",pollFirstData.get(0).LS_No);
                        achievementJson.put("user_mobile_no",pollFirstData.get(i).user_mobile_no);
                        achievementJson.put("LOC_ID",pollFirstData.get(i).LOC_ID);
                        achievementJson.put("Name",achievementData.get(i).Name);
                        achievementJson.put("Relation",achievementData.get(i).Relation);
                        achievementJson.put("Detail",achievementData.get(i).Detail);
                        achievementJson.put("Assistance",achievementData.get(i).Assistance);
                        achievementJson.put("Address_To",achievementData.get(i).AddressTo);
                        achievementJson.put("Address",achievementData.get(i).Address);
                        achievementArray.put(achievementJson);
                    }
                    jsonToUpload.put("tbl_base_info",jsonObject);
                    jsonToUpload.put("social",socialArray);
                    jsonToUpload.put("political_worker",politicalArray);
                    jsonToUpload.put("imp_person",impPersonArray);
                    jsonToUpload.put("religion",religionArray);
                    jsonToUpload.put("social_media",socialMediaArray);
                    jsonToUpload.put("booth_agent",boothAgentArray);
                    jsonToUpload.put("employee",employeeArray);
                    jsonToUpload.put("festival",festivalArray);
                    jsonToUpload.put("wedding",weddingArray);
                    jsonToUpload.put("childbirth",childbirthArray);
                    jsonToUpload.put("death",deathArray);
                    jsonToUpload.put("achievement",achievementArray);
                    jsonToUpload.put("specialletter",specialLetterArray);
                    Log.d("TAG", "run: "+jsonToUpload);
//                    sendData(jsonToUpload);
//                    sendDataJson(jsonToUpload);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void sendData(JSONObject jsonObject) throws JSONException {
        String url = "https://linier.in/UK/Rishikesh/LUP_API/Insert_UpLocalRecord.php";

        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url,jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // response
                        Log.e("Responselogin", response.toString());
                        progressBar.setVisibility(View.GONE);
                        try {

                            JSONObject jsonObject = new JSONObject(response.toString());
                            if (jsonObject.getBoolean("success"))
                            {
                                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(LocalityOptions.this);
                                        pollFirstDataBase.pollFirstDao().clearBoothAgentTable();
                                        pollFirstDataBase.pollFirstDao().clearEmployeeTable();
                                        pollFirstDataBase.pollFirstDao().clearPersonTable();
                                        pollFirstDataBase.pollFirstDao().clearPoliticalWorkerTable();
                                        pollFirstDataBase.pollFirstDao().clearPollfirstTable();
                                        pollFirstDataBase.pollFirstDao().clearSocialMediaTable();
                                        pollFirstDataBase.pollFirstDao().clearSocialTable();
                                        pollFirstDataBase.pollFirstDao().clearReligionTable();
                                        AppExecutors.getInstance().mainThread().execute(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    Toast.makeText(LocalityOptions.this,jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
//                                        Intent intent = new Intent(LocalityOptions.this, Screen_2.class);
//                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                        startActivity(intent);
//                                        LocalityOptions.this.finish();
                                    }
                                });
                            }
                            else  {
                                Toast.makeText(LocalityOptions.this,jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (JSONException e) {

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Its error === >", "onErrorResponse: "+error);
                        progressBar.setVisibility(View.GONE);

                    }
                }
        ) ;
        Mysingleton.getInstance(getApplicationContext()).addToRequestque(postRequest);
    }
}