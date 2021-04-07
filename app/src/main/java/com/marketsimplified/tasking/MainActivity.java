package com.marketsimplified.tasking;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {

    RecyclerView recycle;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    String url = "https://cuglaemobapp.angelbroking.com/AngelService/MoversNews/Movers/1.0.0";
    JSONObject wholejsonobject,infojsonobject;
    JSONObject jsonObject1;
    JSONArray jsonArray;
    String first;
    String second;
    String third;
    Details details;
    DetailsAdapter detailsAdapter;
    List<Details> listdata;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycle = findViewById(R.id.recycler);
        listdata = new ArrayList<>();


        requestQueue = Volley.newRequestQueue(getApplicationContext());
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

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, request,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.i("dfghkmj",response.toString());

                        try {
                            wholejsonobject= response.getJSONObject("response");
                            infojsonobject = wholejsonobject.getJSONObject("data");
                            jsonArray= infojsonobject.getJSONArray("bse");
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                jsonObject1 = jsonArray.getJSONObject(i);
                                first = jsonObject1.getString("symbolName")+"\n"+ jsonObject1.getString("change");
                                second = jsonObject1.getString("changePer");
                                third = jsonObject1.getString("ltp");

                                Details details = new Details();
                                details.setId(second);
                                details.setName(first);
                                details.setEmail(third);
                                listdata.add(details);


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                            detailsAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);


        detailsAdapter = new DetailsAdapter(listdata, MainActivity.this);

        recycle.setHasFixedSize(true);

        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.setAdapter(detailsAdapter);
    }
}
