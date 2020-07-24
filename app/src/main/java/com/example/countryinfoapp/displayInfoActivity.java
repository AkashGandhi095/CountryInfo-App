package com.example.countryinfoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.countryinfoapp.Model.CountryData;
import com.google.android.material.textview.MaterialTextView;
import com.guardanis.imageloader.ImageRequest;

public class displayInfoActivity extends AppCompatActivity {

    private ImageView flagImgView;
    private MaterialTextView name , capital , region , subRegion , population ,
    borders , languages;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_info);
        initViews();

        Intent intent = getIntent();
        CountryData data = intent.getParcelableExtra("data");


        if(data != null)
        {
            StringBuilder builder = new StringBuilder();
            ImageRequest.create(flagImgView).setTargetUrl(data.getFlag()).execute();
            name.setText("Name : " +data.getName());
            capital.setText("Capital : " +data.getCapital());
            region.setText("Region : " +data.getRegion());
            subRegion.setText("SubRegion : " +data.getSubRegion());
            population.setText("Population : " + data.getPopulation());




            for (int i = 0; i <data.getLanguageList().size() ; i++) {

                builder.append(data.getLanguageList().get(i)).append(" , ");

            }

            languages.setText("Languages : " +builder.toString());

            for (int i = 0; i <data.getBorderList().size() ; i++) {

                builder.append(data.getBorderList().get(i)).append(" , ");

            }

            borders.setText("Borders : " +builder.toString());


        }


    }

    private void initViews() {

        flagImgView = findViewById(R.id.flagImg);
        name = findViewById(R.id.name);
        capital = findViewById(R.id.capital);
        region = findViewById(R.id.region);
        subRegion = findViewById(R.id.subRegion);
        population = findViewById(R.id.population);
        borders = findViewById(R.id.borders);
        languages = findViewById(R.id.languages);

    }
}