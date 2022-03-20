package com.potato.weatherapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherRVAdapter extends RecyclerView.Adapter<WeatherRVAdapter.ViewHolder> {

    private Context context;
    private ArrayList<WeatherRVModel> weatherRVModels;


    public WeatherRVAdapter(Context context, ArrayList<WeatherRVModel> weatherRVModels) {
        this.context = context;
        this.weatherRVModels = weatherRVModels;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weather_rv_item, parent, false);
        return  new ViewHolder(view);

    }

    @Override
    @SuppressLint("SimpleDateFormat")
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        WeatherRVModel weatherRVModel = weatherRVModels.get(position);

        Picasso.get().load("http:".concat(weatherRVModel.getIcon())).into(holder.conditionIV);

        holder.tempTV.setText(weatherRVModel.getTemperature().concat("°c"));
        holder.windTV.setText(weatherRVModel.getWindSpeed().concat("Km/h"));

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat output = new SimpleDateFormat("hh:mm aa");

        try{
            Date t = input.parse(weatherRVModel.getTime());
            holder.timeTV.setText(output.format(t));

        } catch (ParseException e){
            e.printStackTrace();
        }


    }


    @Override
    public int getItemCount() {
        return weatherRVModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView windTV, tempTV, timeTV;
        private ImageView conditionIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            windTV = itemView.findViewById(R.id.idTVWindSpeed);
            timeTV = itemView.findViewById(R.id.idTVTime);
            tempTV = itemView.findViewById(R.id.idTVCardTemprature);
            conditionIV = itemView.findViewById(R.id.idIVCardCondition);

        }
    }
}
