package com.example.countryinfoapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countryinfoapp.Model.CountryData;
import com.example.countryinfoapp.R;
import com.example.countryinfoapp.displayInfoActivity;
import com.google.android.material.textview.MaterialTextView;
import com.guardanis.imageloader.ImageRequest;


import java.util.List;

public class RegionAdapter extends RecyclerView.Adapter<RegionAdapter.DataViewHolder> {

    private List<CountryData> countryDataList;
    private Context context;

    public RegionAdapter(Context context) {
        this.context = context;
    }

    public void setDataList(List<CountryData> countryDataList)
    {
        this.countryDataList = countryDataList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public RegionAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_row_item , parent , false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegionAdapter.DataViewHolder holder, int position) {
        CountryData data = countryDataList.get(position);
        if(data != null)
        {
            ImageRequest.create(holder.flagImg).setTargetUrl(data.getFlag()).execute();
            holder.textView.setText(data.getName());
            Log.d("flag", "onBindViewHolder: "+data.getFlag());


            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context , displayInfoActivity.class);
                intent.putExtra("data" ,data);
                context.startActivity(intent);
            });

        }
    }

    @Override
    public int getItemCount() {
        if(countryDataList != null)
        {
            return countryDataList.size();
        }
        return 0;
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder   {

        ImageView flagImg;
        MaterialTextView textView;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            flagImg = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);

        }

    }
}
