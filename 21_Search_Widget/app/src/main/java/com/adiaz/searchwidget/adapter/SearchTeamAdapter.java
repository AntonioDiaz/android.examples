package com.adiaz.searchwidget.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.adiaz.searchwidget.R;
import com.adiaz.searchwidget.retrofit.entities.Team;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchTeamAdapter extends RecyclerView.Adapter<SearchTeamAdapter.ViewHolder>{

    List<Team> mTeamNames;
    Context mContext;

    public SearchTeamAdapter(List<Team> mTeamNames, Context mContext) {
        this.mTeamNames = mTeamNames;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.listitem_team, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTeamName.setText(mTeamNames.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mTeamNames.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_team_name)
        TextView tvTeamName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
