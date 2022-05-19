package com.example.evaluacionpractica2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.evaluacionpractica2.Entities.Anime;
import com.example.evaluacionpractica2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.StringViewHolder> {
    List<Anime> animes;
    public AnimeAdapter(List<Anime> animes){
        this.animes=animes;
    }

    @NonNull
    @Override
    public AnimeAdapter.StringViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.anime_item, parent,false);
        StringViewHolder vh = new StringViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeAdapter.StringViewHolder holder, int position) {
        View view = holder.itemView;

        Anime anime =animes.get(position);
        TextView tvAnimeName = view.findViewById(R.id.tvTitulo);
        TextView tvAnimeDescription = view.findViewById(R.id.tvDescription);
        ImageView imageView = view.findViewById(R.id.ivImg);

        View Ly = view.findViewById(R.id.llEsquema);

        tvAnimeName.setText(anime.name);
        tvAnimeDescription.setText(anime.description);

        Picasso.get()
                .load(anime.img)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.stat_notify_error)
                .into(imageView);
        Ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return animes.size();
    }

    class StringViewHolder extends RecyclerView.ViewHolder{

        public StringViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
