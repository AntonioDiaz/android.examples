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
import com.adiaz.testingsqllite.db.entities.Sport;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by adiaz on 2/1/18.
 */

public class SportsAdapter extends RecyclerView.Adapter <SportsAdapter.SportsAdapterViewHolder> {

    public static final String TAG = SportsAdapter.class.getSimpleName();
    private Cursor mCursor;
    private Context context;
    private ListItemClickListener mListItemClickListener;

    public SportsAdapter(ListItemClickListener mListItemClickListener) {
        this.mListItemClickListener = mListItemClickListener;
    }

    public void swapCursor(Cursor newCursor) {
        mCursor = newCursor;
        notifyDataSetChanged();
    }

    @Override
    public SportsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.sports_list_item, parent, false);
        return new SportsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SportsAdapterViewHolder holder, int position) {
        boolean move = mCursor.moveToPosition(position);
        Sport sport = DbContract.SportsEntry.initEntity(mCursor);
        holder.mTvTitle.setText(sport.id() + " - " + sport.name());
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public class SportsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_sport)
        TextView mTvTitle;

        public SportsAdapterViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View view) {
            int clickedItemIndex = getAdapterPosition();
            mListItemClickListener.onListItemClick(clickedItemIndex);
        }
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}
