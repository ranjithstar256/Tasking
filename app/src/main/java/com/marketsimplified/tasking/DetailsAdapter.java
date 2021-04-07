package com.marketsimplified.tasking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter <DetailsAdapter.ViewHolder>{
    List<Details> q;
    Context c;

    public DetailsAdapter(List<Details> q, Context c) {
        this.q = q;
        this.c = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Details  details = q.get(position);
        holder.tid.setText(details.getId());
        holder.tnamer.setText(details.getName());
        holder.tmail.setText(details.getEmail());

    }

    @Override
    public int getItemCount() {

        return q.size();


    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tid,tnamer,tmail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tid=itemView.findViewById(R.id.textview1);
            tnamer=itemView.findViewById(R.id.textview2);
            tmail=itemView.findViewById(R.id.textview3);
        }
    }
}
