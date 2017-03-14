package com.example.anhch_000.sqlphpasnytaskandroid;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by anhch_000 on 04/03/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<Banana> bananas;


    public Adapter(ArrayList<Banana> bananas) {
        this.bananas = bananas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Banana banana = bananas.get(position);
        holder.id.setText(String.valueOf(banana.getId()));
        holder.name.setText(banana.getName());
        holder.content.setText(banana.getContent());
        holder.imageView.setImageBitmap(banana.getIamge());
    }

    @Override
    public int getItemCount() {
        if (!bananas.isEmpty()) return bananas.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView id;
        TextView name;
        TextView content;

        public ViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.id);
            name = (TextView) itemView.findViewById(R.id.name);
            content = (TextView) itemView.findViewById(R.id.content);
            imageView = (ImageView) itemView.findViewById(R.id.img);
        }
    }

}
