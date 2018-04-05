package com.example.magnus.locationnotes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.example.magnus.locationnotes.R;
import com.example.magnus.locationnotes.model.Kort;

public class NotatRecycleAdapter extends RecyclerView.Adapter<NotatRecycleAdapter.KortViewHolder> {
    private List<Kort> mData;
    private LayoutInflater mInflater;

    public NotatRecycleAdapter(Context context, List<Kort> data) {
        this.mData = Kort.getData();
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public KortViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.my_list_item, parent, false);
        KortViewHolder holder = new KortViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(KortViewHolder holder, int position) {
        Kort currentObj = mData.get(position);
        holder.setData(currentObj, position);
        holder.setListeners();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void removeItem(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mData.size());
        notifyDataSetChanged();
    }

    class KortViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView teksten;
        ImageView sletteKnapp;
        int position;

        public KortViewHolder(View itemView) {
            super(itemView);
            teksten = (TextView) itemView.findViewById(R.id.notat_text);
        }

        public void setData(Kort current, int position) {
            this.teksten.setText(current.getTeksten());
            this.position = position;
            sletteKnapp = (ImageView) itemView.findViewById(R.id.img_slettKnapp);
        }

        public void setListeners() {
            sletteKnapp.setOnClickListener(KortViewHolder.this);
        }

        @Override
        public void onClick(View v) {
            Log.i("onClick before operatio", position + " " + mData.size());
            switch (v.getId()) {
                case R.id.img_slettKnapp:
                    removeItem(position);
                    break;
            }
            Log.i("onClick after operation", mData.size() + "");
        }
    }


}