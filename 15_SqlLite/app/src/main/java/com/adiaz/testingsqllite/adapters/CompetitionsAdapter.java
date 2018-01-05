package com.adiaz.testingsqllite.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adiaz.testingsqllite.R;
import com.adiaz.testingsqllite.db.DbContract;
import com.adiaz.testingsqllite.db.entities.Competition;
import com.adiaz.testingsqllite.db.entities.Sport;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by adiaz on 2/1/18.
 */

public class CompetitionsAdapter extends RecyclerView.Adapter<CompetitionsAdapter.ViewHolder> {

    Cursor mCursor;
    ListItemClickListener listItemClickListener;

    public CompetitionsAdapter(ListItemClickListener listItemClickListener) {
        this.listItemClickListener = listItemClickListener;
    }

    public void swapCursor (Cursor cursor) {
        this.mCursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.competition_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        boolean move = mCursor.moveToPosition(position);
        Competition competition = DbContract.CompetitionsEntry.initEntity(mCursor);
        holder.tvCompetition.setText(competition.idSport() + " - " + competition.name());
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_competition)
        TextView tvCompetition;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listItemClickListener.onListItemClick(getAdapterPosition());
        }
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}
