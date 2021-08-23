package com.candlestickschart.wayanadboothpresident;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginScreen extends AppCompatActivity {

    EditText editText;
    Button login;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        editText = findViewById(R.id.mobile);
        login = findViewById(R.id.login);
        progressBar = findViewById(R.id.progressBar);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editText.getText().toString().equals("")) {
                    if (editText.getText().toString().length() == 10) {
                        progressBar.setVisibility(View.VISIBLE);
                        apicallToLogin();
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (!sharedPreferences.getString("user_id","").equals("")) {
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(LoginScreen.this);
                        Integer size = pollFirstDataBase.pollFirstDao().getPollfirstData().size();
                        if (size == 0) {
                            PollFirstData pollFirstData = new PollFirstData("","",sharedPreferences.getString("LOC_ID",""),"","","","","","","","","","","","","","","","","","","","","","","");
                            pollFirstDataBase.pollFirstDao().insertPollFirst(pollFirstData);
                        }
                        else {
                            AppExecutors.getInstance().mainThread().execute(new Runnable() {
                                @Override
                                public void run() {
                                    if (size==0) {
                                        Intent intent = new Intent(LoginScreen.this,LocalityOptions.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else  {
                                        Intent intent = new Intent(LoginScreen.this,LocalityOptions.class);
                                        startActivity(intent);
                                        finish();
                                    }


                                }
                            });
                        }


                    }
                    catch (Exception e ) {

                    }
                }
            });

        }
    }

    public void apicallToLogin(){
        try {
            String url = "http://ourwayanad.in/WY_API/MemberLoginRecord.php";
            Map<String, String> postParam= new HashMap<String, String>();
            postParam.put("user_mobile_no", editText.getText().toString());
            JSONObject jsonObject = new JSONObject(postParam);
            Log.d("TAG", "apicallToLogin: "+ jsonObject);
            JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url,new JSONObject(postParam),
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

                                    Intent intent = new Intent(LoginScreen.this,MainActivity.class);
                                    editor.putString("user_id",jsonObject.getString("user_id"));
                                    editor.putString("user_mobile_no",jsonObject.getString("user_mobile_no"));
                                    editor.putString("name",jsonObject.getString("name"));
                                    editor.putString("email",jsonObject.getString("email"));
                                    editor.putString("vs_no",jsonObject.getString("vs_no"));
                                    editor.putString("vs_name",jsonObject.getString("vs_name"));
                                    editor.putString("booth_no",jsonObject.getString("booth_no"));
                                    editor.putString("LOC_ID",jsonObject.getString("Booth_ID"));
                                    editor.commit();
                                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                PollFirstDataBase pollFirstDataBase = PollFirstDataBase.getInstance(LoginScreen.this);
                                                Integer size = pollFirstDataBase.pollFirstDao().getPollfirstData().size();
                                                if (size == 0) {
                                                    PollFirstData pollFirstData = new PollFirstData(jsonObject.getString("user_id"),jsonObject.getString("user_mobile_no"),sharedPreferences.getString("LOC_ID",""),jsonObject.getString("vs_no"),"","","","","","","","","","","","","","","","","","","","","","");
                                                    pollFirstDataBase.pollFirstDao().insertPollFirst(pollFirstData);
                                                }
                                            }
                                            catch (Exception e ) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });

                                    startActivity(intent);
                                }
                                else  {
                                    Toast.makeText(LoginScreen.this,jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
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
        catch (NullPointerException e) {

        }
    }
}