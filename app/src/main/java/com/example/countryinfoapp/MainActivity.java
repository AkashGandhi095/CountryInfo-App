package com.example.countryinfoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.countryinfoapp.Adapter.RegionAdapter;
import com.example.countryinfoapp.Model.CountryData;
import com.example.countryinfoapp.Model.Language;
import com.example.countryinfoapp.Utils.ApiSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    private static final String URL = "https://restcountries.eu/rest/v2/region/asia";

    private List<CountryData> countryDataList;

    private RecyclerView recyclerView;
    private RegionAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        GetApiResponse();
    }

    private void initViews() {

        countryDataList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new RegionAdapter(this);
        recyclerView.setAdapter(adapter);

    }


    private void GetApiResponse() {



        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null,

                response -> {

                    for (int i = 0; i < response.length(); i++) {

                        try {
                            List<String> languageList = new ArrayList<>();
                            List<String> borderList = new ArrayList<>();

                            JSONObject object = response.getJSONObject(i);
                            JSONArray borderArray = object.getJSONArray("borders");
                            JSONArray languageArray = object.getJSONArray("languages");

                            for (int j = 0; j < borderArray.length(); j++) {

                                borderList.add(borderArray.getString(j));

                            }


                            for (int j = 0; j < languageArray.length(); j++) {

                                JSONObject lanObject = languageArray.getJSONObject(j);
                                languageList.add(lanObject.getString("name"));

                            }

                            countryDataList.add(new CountryData(
                               object.getString("name"),
                               object.getString("capital"),
                               object.getString("flag"),
                               object.getString("region"),
                               object.getString("subregion"),
                               object.getInt("population"),
                               borderList,
                               languageList
                            ));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    adapter.setDataList(countryDataList);

                    Log.d("GetApi", "GetApiResponse: " +response.length());
                },

                error -> Log.d("GetApi", "onErrorResponse: " +error.getLocalizedMessage()));
        ApiSingleton.getInstance(this).getRequestQueue().add(request);
    }

}