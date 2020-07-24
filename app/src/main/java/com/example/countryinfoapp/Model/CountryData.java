package com.example.countryinfoapp.Model;



import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;


public class CountryData implements Parcelable {


    private String name;


    private String capital;


    private String flag;


    private String region;


    private String subRegion;

    private int population;

    private List<String> borderList;


    private List<String> languageList;


    public CountryData(String name, String capital, String flag, String region, String subRegion,
                       int population, List<String> borderList, List<String> languageList) {

        this.name = name;
        this.capital = capital;
        this.flag = flag;
        this.region = region;
        this.subRegion = subRegion;
        this.population = population;
        this.borderList = borderList;
        this.languageList = languageList;

    }

    protected CountryData(Parcel in) {
        name = in.readString();
        capital = in.readString();
        flag = in.readString();
        region = in.readString();
        subRegion = in.readString();
        population = in.readInt();
        borderList = in.createStringArrayList();
        languageList = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(capital);
        dest.writeString(flag);
        dest.writeString(region);
        dest.writeString(subRegion);
        dest.writeInt(population);
        dest.writeStringList(borderList);
        dest.writeStringList(languageList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CountryData> CREATOR = new Creator<CountryData>() {
        @Override
        public CountryData createFromParcel(Parcel in) {
            return new CountryData(in);
        }

        @Override
        public CountryData[] newArray(int size) {
            return new CountryData[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getFlag() {
        return flag;
    }

    public String getRegion() {
        return region;
    }

    public String getSubRegion() {
        return subRegion;
    }

    public int getPopulation() {
        return population;
    }

    public List<String> getBorderList() {
        return borderList;
    }

    public List<String> getLanguageList() {
        return languageList;
    }

}

