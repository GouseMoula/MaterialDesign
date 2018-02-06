package com.materialdesign.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.materialdesign.R;
import com.materialdesign.ThemesData;

import java.util.ArrayList;

/**
 * Created by compindia-fujitsu on 12-11-2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.VHolder> {
    ArrayList<ThemesData> themesData;
    Context context;
    LayoutInflater inflater;
    private ItemClickListener mClickListener;

    public RecyclerAdapter(Context context, ArrayList<ThemesData> themesData2) {
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.themesData = themesData2;
    }

    @Override
    public VHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        VHolder vHolder = new VHolder(itemView);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(VHolder holder, int position) {
        holder.textView.setText(themesData.get(position).getItemText());
        holder.textView.setBackgroundColor(context.getResources().getColor(themesData.get(position).getItemBackgroundColor()));
    }

    @Override
    public int getItemCount() {
        return themesData.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class VHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;

        public VHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_list_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}

