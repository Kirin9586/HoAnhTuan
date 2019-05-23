package com.example.androidnangcaocuoiki;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private int GroupView;
    private List<Hanghoa> models;
    public RecyclerViewAdapter(List<Hanghoa>model,int vg,Context context){
        this.context= context;
        this.models = model;
        this.GroupView = vg;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(GroupView,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
       final Hanghoa model = models.get(i);
        viewHolder.description.setText(model.getDescription());
        viewHolder.id.setText(String.valueOf(model.getId()));
        viewHolder.price.setText(String.valueOf(model.getPrice()));
        viewHolder.producer.setText(model.getProducer());
        viewHolder.product_name.setText(model.getProduct_name());
        final int position = i;
        viewHolder.liner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("data",model);
                context.startActivity(intent);
            }
        });
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.myRef.child(model.getKeyParent()).removeValue();
                models.clear();
            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView description;
        TextView id;
        TextView price;
        TextView producer;
        TextView product_name;
        LinearLayout liner;
        Button delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.description = itemView.findViewById(R.id.txt_description);
            this.id = itemView.findViewById(R.id.txt_id);
            this.price = itemView.findViewById(R.id.txt_price);
            this.producer = itemView.findViewById(R.id.txt_producer);
            this.product_name = itemView.findViewById(R.id.txt_product_name);
            this.liner = itemView.findViewById(R.id.liner);
            this.delete = itemView.findViewById(R.id.btn_delete);

        }
    }
}
