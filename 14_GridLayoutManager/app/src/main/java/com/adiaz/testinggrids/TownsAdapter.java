package com.adiaz.testinggrids;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adiaz.testinggrids.retrofit.entities.Town;

import java.util.List;

/**
 * Created by adiaz on 30/12/17.
 */

public class TownsAdapter extends RecyclerView.Adapter<TownsAdapter.TownsViewHolder> {

    List<Town> towns;
    private final ListItemClickListener mOnClickListener;

    public TownsAdapter(List<Town> towns, ListItemClickListener mOnClickListener) {
        this.towns = towns;
        this.mOnClickListener = mOnClickListener;
    }

    public List<Town> getTowns() {
        return towns;
    }

    public void setTowns(List<Town> towns) {
        this.towns = towns;
    }

    @Override
    public TownsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.town_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new TownsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TownsViewHolder holder, int position) {
        String name = towns.get(position).getName();
        holder.mTownName.setText(name);
    }

    @Override
    public int getItemCount() {
        return towns.size();
    }

    public class TownsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mTownName;

        public TownsViewHolder(View itemView) {
            super(itemView);
            mTownName = (TextView) itemView.findViewById(R.id.tv_town);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedItemIndex = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedItemIndex);
        }
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}
