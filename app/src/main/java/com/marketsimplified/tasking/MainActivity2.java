package com.marketsimplified.tasking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity2 extends AppCompatActivity {

    private Button getApiBtn, postApiBtn;
    private TextView resultTextView;
    RequestQueue requestQueue;
    private static final String TAG = MainActivity.class.getSimpleName();
    String url = "https://cuglaemobapp.angelbroking.com/AngelService/MoversNews/Movers/1.0.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        resultTextView = findViewById(R.id.resultTextView);
        getApiBtn = findViewById(R.id.getApiBtn);
        postApiBtn = findViewById(R.id.postApiBtn);

        getApiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
        //Click Listner for POST JSONObject
        postApiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData();
            }
        });
    }

    // Post Request For JSONObject
    public void postData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject requestBody=new JSONObject();
        JSONObject dataBody=new JSONObject();
        JSONObject request=new JSONObject();

        try {
            requestBody.put("appID","f363c1745f5f63433a57e369a01c5752");
            requestBody.put("formFactor","M");
            requestBody.put("futures","0");
            requestBody.put("response_format", "json");
            dataBody.put("category","TOPGAINER");
            dataBody.put("sessionID", "guest");
            dataBody.put("usrID","guest");
            requestBody.put("data",dataBody);
            request.put("request",requestBody);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        // Enter the correct url for your api service site

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, request,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        resultTextView.setText("String Response : "+ response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultTextView.setText("Error getting response");
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    // Get Request For JSONObject
    public void getData(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        try {

            JSONObject object = new JSONObject();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    resultTextView.setText("Resposne : " + response.toString());
                    Toast.makeText(getApplicationContext(), "I am OK !" + response.toString(), Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}