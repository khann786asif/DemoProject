package com.example.demoproject;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import java.util.List;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private final Context mContext;
    private final List<AsianCountries> mCountries;

    public ExampleAdapter(Context context, List<AsianCountries> countries){
        mContext = context;
        mCountries = countries;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.data_item, parent, false);
        return new ExampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        AsianCountries currentCountry = mCountries.get(position);

        Uri uri = Uri.parse(currentCountry.getFlag());

        if (uri != null) {
            GlideToVectorYou.justLoadImage((Activity) mContext, uri, holder.imageView);
        }

        StringBuilder data = new StringBuilder();
        data.append("Name : ").append(currentCountry.getName());
        data.append("\nCapital : ").append(currentCountry.getCapital());
        data.append("\nRegion : ").append(currentCountry.getRegion());
        data.append("\nSubRegion : ").append(currentCountry.getSubregion());
        data.append("\nPopulation : ").append(currentCountry.getPopulation());
        data.append("\nBorders : ");
        for(String border : currentCountry.getBorders()){
            data.append(border).append(", ");
        }
        data.append("\nLanguages : ");
        for(Language language : currentCountry.getLanguages()){
            data.append(language.getName()).append(", ");
        }
        holder.textView.setText(data.toString());
    }

    @Override
    public int getItemCount() {
        return mCountries.size();
    }

    static class ExampleViewHolder extends RecyclerView.ViewHolder{
        private final ImageView imageView;
        private final TextView textView;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            textView = itemView.findViewById(R.id.text_view_result);
        }
    }
}
