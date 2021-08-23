package com.candlestickschart.wayanadboothpresident;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.candlestickschart.wayanadboothpresident.databinding.ActivityMainDashBoardBinding;
import com.candlestickschart.wayanadboothpresident.databinding.ActivityScreen3Binding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainDashBoard extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button10;
    Button upload;
    TextView name;
    TextView locname;
    TextView villName;
    SharedPreferences sharedPreferences;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dash_board);
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        ActivityMainDashBoardBinding searchVoterBinding = DataBindingUtil.setContentView(this,R.layout.activity_main_dash_board);
        User user = new User(sharedPreferences.getString("name",""),sharedPreferences.getString("vs_no","")+"-"+sharedPreferences.getString("vs_name",""),sharedPreferences.getString("booth_no","")+"-"+sharedPreferences.getString("booth_name",""),"");
        searchVoterBinding.setMainUser(user);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button10 = findViewById(R.id.button10);
        progressBar = findViewById(R.id.progressBar);
        upload = findViewById(R.id.upload);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainDashBoard.this, Screen_4.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainDashBoard.this,Screen_5.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainDashBoard.this,Screen_6.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainDashBoard.this,SocialPersonList.class);
                startActivity(intent);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainDashBoard.this,Political_Screen.class);
                startActivity(intent);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainDashBoard.this, ImpPersonScreen.class);
                startActivity(intent);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainDashBoard.this, ReligionScreen.class);
                startActivity(intent);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainDashBoard.this, SocialMediaScreen.class);
                startActivity(intent);

            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainDashBoard.this, BoothAgentScreen.class);
                startActivity(intent);

            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainDashBoard.this,EmployeScreen.class);
                startActivity(intent);
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                setUpUpload();
            }
        });
    }

    public void setUpUpload() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(MainDashBoard.this);
                List<PollFirstData> pollFirstData = pollFirstDataBase.pollFirstDao().getPollfirstData();
                List<SocialData> socialData = pollFirstDataBase.pollFirstDao().getSocialType();
                List<PoliticalWorkerData> politicalWorkerData = pollFirstDataBase.pollFirstDao().getPoliticalWorker();
                List<ImpPersonData> impPerson = pollFirstDataBase.pollFirstDao().getImpPerson();
                List<ReligionData> religionData = pollFirstDataBase.pollFirstDao().getReligion();
                List<SocialMediaData> socialMediaData = pollFirstDataBase.pollFirstDao().getSocialMedia();
                List<BoothAgent> boothAgentData = pollFirstDataBase.pollFirstDao().getBoothAgent();
                List<EmployeeData> employeeData = pollFirstDataBase.pollFirstDao().getEmployee();
                JSONObject jsonObject = new JSONObject();
                JSONArray socialArray = new JSONArray();
                JSONArray politicalArray = new JSONArray();
                JSONArray impPersonArray = new JSONArray();
                JSONArray religionArray = new JSONArray();
                JSONArray socialMediaArray = new JSONArray();
                JSONArray boothAgentArray = new JSONArray();
                JSONArray employeeArray = new JSONArray();
                JSONObject jsonToUpload = new JSONObject();

                try {
                    jsonObject.put("VS_No",pollFirstData.get(0).VS_No);
                    jsonObject.put("LS_No",pollFirstData.get(0).LS_No);
                    jsonObject.put("user_id",pollFirstData.get(0).user_id);
                    jsonObject.put("user_mobile_no",pollFirstData.get(0).user_mobile_no);
                    jsonObject.put("LOC_ID",pollFirstData.get(0).LOC_ID);
                    jsonObject.put("Locality_name",pollFirstData.get(0).Locality_name);
                    jsonObject.put("Locality_type",pollFirstData.get(0).Locality_type);
                    jsonObject.put("Economic",pollFirstData.get(0).Economic);
                    jsonObject.put("Party_leading",pollFirstData.get(0).Party_leading);
                    jsonObject.put("Reason_leading",pollFirstData.get(0).Reason_leading);
                    jsonObject.put("Local_issue",pollFirstData.get(0).Local_issue);
                    jsonObject.put("Panchayat_analysis",pollFirstData.get(0).Panchayat_analysis);
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
                        socialJson.put("LS_No",pollFirstData.get(0).LS_No);
                        socialJson.put("user_mobile_no",socialData.get(i).user_mobile_no);
                        socialJson.put("LOC_ID",socialData.get(i).LOC_ID);
                        socialJson.put("Social_Type",socialData.get(i).Social_Type);
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
                        politicalJson.put("LS_No",pollFirstData.get(0).LS_No);
                        politicalJson.put("user_mobile_no",politicalWorkerData.get(i).user_mobile_no);
                        politicalJson.put("LOC_ID",politicalWorkerData.get(i).LOC_ID);
                        politicalJson.put("Social_Type",politicalWorkerData.get(i).Social_Type);
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
                        impPersonJson.put("LS_No",pollFirstData.get(0).LS_No);
                        impPersonJson.put("user_mobile_no",impPerson.get(i).user_mobile_no);
                        impPersonJson.put("LOC_ID",impPerson.get(i).LOC_ID);
                        impPersonJson.put("Social_Type",impPerson.get(i).Social_Type);
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
                        religionJson.put("LS_No",pollFirstData.get(0).LS_No);
                        religionJson.put("user_mobile_no",religionData.get(i).user_mobile_no);
                        religionJson.put("LOC_ID",religionData.get(i).LOC_ID);
                        religionJson.put("Impression",religionData.get(i).Impression);
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
                        socialMediaJson.put("LS_No",pollFirstData.get(0).LS_No);
                        socialMediaJson.put("user_mobile_no",socialMediaData.get(i).user_mobile_no);
                        socialMediaJson.put("LOC_ID",socialMediaData.get(i).LOC_ID);
                        socialMediaJson.put("Social_Type",socialMediaData.get(i).Social_Type);
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
                        boothAgentJson.put("LS_No",pollFirstData.get(0).LS_No);
                        boothAgentJson.put("user_mobile_no",boothAgentData.get(i).user_mobile_no);
                        boothAgentJson.put("LOC_ID",boothAgentData.get(i).LOC_ID);
                        boothAgentJson.put("Social_Type",boothAgentData.get(i).Social_Type);
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
                        employeeJson.put("LS_No",pollFirstData.get(0).LS_No);
                        employeeJson.put("user_mobile_no",employeeData.get(i).user_mobile_no);
                        employeeJson.put("LOC_ID",employeeData.get(i).LOC_ID);
                        employeeJson.put("Social_Type",employeeData.get(i).Social_Type);
                        employeeJson.put("Name",employeeData.get(i).Name);
                        employeeJson.put("Gender",employeeData.get(i).Gender);
                        employeeJson.put("Age",employeeData.get(i).Age);
                        employeeJson.put("Mobile",employeeData.get(i).Mobile);
                        employeeJson.put("Position",employeeData.get(i).Position);
                        employeeArray.put(employeeJson);
                    }
                    jsonToUpload.put("tbl_base_info",jsonObject);
                    jsonToUpload.put("social",socialArray);
                    jsonToUpload.put("political_worker",politicalArray);
                    jsonToUpload.put("imp_person",impPersonArray);
                    jsonToUpload.put("religion",religionArray);
                    jsonToUpload.put("social_media",socialMediaArray);
                    jsonToUpload.put("booth_agent",boothAgentArray);
                    jsonToUpload.put("employee",employeeArray);
                    Log.d("TAG", "run: "+jsonToUpload);
                    sendData(jsonToUpload);
//                    sendDataJson(jsonToUpload);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void sendDataJson(JSONObject jsonBody){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "https://linier.in/UK/Rishikesh/LUP_API/Insert_UpLocalRecord.php";
        final String requestBody = jsonBody.toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Log.i("VOLLEY", response);

                progressBar.setVisibility(View.GONE);
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean("success"))
                    {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(MainDashBoard.this);
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
                                            Toast.makeText(MainDashBoard.this,jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
//                                Intent intent = new Intent(MainDashBoard.this, Screen_2.class);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                startActivity(intent);
//                                MainDashBoard.this.finish();
                            }
                        });
                    }
                    else  {
                        Toast.makeText(MainDashBoard.this,jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                    }
                }
                catch (JSONException e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
            }
        });
        requestQueue.add(stringRequest);
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
                                        PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(MainDashBoard.this);
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
                                                    Toast.makeText(MainDashBoard.this,jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
//                                        Intent intent = new Intent(MainDashBoard.this, Screen_2.class);
//                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                        startActivity(intent);
//                                        MainDashBoard.this.finish();
                                    }
                                });
                            }
                            else  {
                                Toast.makeText(MainDashBoard.this,jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onPostResume() {
        super.onPostResume();

        final boolean[] rajniti = {false};
        final boolean[] localGroup = {false};
        final boolean[] vikas = {false};
        final boolean[] samajik = {false};
        final boolean[] karya = {false};
        final boolean[] vyakti = {false};
        final boolean[] sanstha = {false};
        final boolean[] media = {false};
        final boolean[] agent = {false};
        final boolean[] sarkari = {false};

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(MainDashBoard.this);
                List<String> checkPolitics = pollFirstDataBase.pollFirstDao().checkOtherCond();
                List<String> checklocal = pollFirstDataBase.pollFirstDao().checkLocalGroup();
                List<String> checkvikas = pollFirstDataBase.pollFirstDao().checkDevNeeds();
                List<SocialData> socialData = pollFirstDataBase.pollFirstDao().getSocialType();
                List<PoliticalWorkerData> politicalWorkerData = pollFirstDataBase.pollFirstDao().getPoliticalWorker();
                List<ImpPersonData> impPerson = pollFirstDataBase.pollFirstDao().getImpPerson();
                List<ReligionData> religionData = pollFirstDataBase.pollFirstDao().getReligion();
                List<SocialMediaData> socialMediaData = pollFirstDataBase.pollFirstDao().getSocialMedia();
                List<BoothAgent> boothAgentData = pollFirstDataBase.pollFirstDao().getBoothAgent();
                List<EmployeeData> employeeData = pollFirstDataBase.pollFirstDao().getEmployee();
                Log.d("Size===>", "run: "+checkPolitics.size());
                AppExecutors.getInstance().mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (!checkPolitics.get(0).equals("")) {
                            rajniti[0] = true;
                            button1.setBackgroundResource(R.drawable.green);
                        }
                        else {
                            rajniti[0] = false;
                            button1.setBackgroundResource(R.drawable.newsample);
                        }
                        if (!checklocal.get(0).equals("")) {
                            localGroup[0] = true;
                            button2.setBackgroundResource(R.drawable.green);
                        }
                        else {
                            localGroup[0] = false;
                            button2.setBackgroundResource(R.drawable.newsample);
                        }
                        if (!checkvikas.get(0).equals("")) {
                            vikas[0] = true;
                            button3.setBackgroundResource(R.drawable.green);
                        }
                        else {
                            vikas[0] = false;
                            button3.setBackgroundResource(R.drawable.newsample);
                        }
                        if (socialData.size() != 0) {
                            samajik[0] = true;
                            button4.setBackgroundResource(R.drawable.green);
                        }
                        else {
                            samajik[0] = false;
                            button4.setBackgroundResource(R.drawable.newsample);
                        }
                        if (politicalWorkerData.size() != 0) {
                            karya[0] = true;
                            button5.setBackgroundResource(R.drawable.green);
                        }
                        else {
                            karya[0] = false;
                            button5.setBackgroundResource(R.drawable.newsample);
                        }
                        if (impPerson.size() != 0) {
                            vyakti[0] = true;
                            button6.setBackgroundResource(R.drawable.green);
                        }else {
                            vyakti[0] = false;
                            button6.setBackgroundResource(R.drawable.newsample);
                        }
                        if (religionData.size() != 0) {
                            sanstha[0] = true;
                            button7.setBackgroundResource(R.drawable.green);
                        }
                        else {
                            sanstha[0] = false;
                            button7.setBackgroundResource(R.drawable.newsample);
                        }
                        if (socialMediaData.size() != 0) {
                            media[0] = true;
                            button8.setBackgroundResource(R.drawable.green);
                        }
                        else {
                            media[0] = false;
                            button8.setBackgroundResource(R.drawable.newsample);
                        }
                        if (boothAgentData.size() != 0) {
                            agent[0] = true;
                            button9.setBackgroundResource(R.drawable.green);
                        }
                        else {
                            agent[0] = false;
                            button9.setBackgroundResource(R.drawable.newsample);
                        }
                        if (employeeData.size() != 0) {
                            sarkari[0] = true;
                            button10.setBackgroundResource(R.drawable.green);
                        }
                        else {
                            sarkari[0] = false;
                            button10.setBackgroundResource(R.drawable.newsample);
                        }
                        if (rajniti[0] && vikas[0] && samajik[0] && karya[0] && vyakti[0] && sanstha[0] && media[0] && agent[0] && sarkari[0]) {
//                            upload.setVisibility(View.VISIBLE);
                        }
                        else {
                            upload.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });

    }
}