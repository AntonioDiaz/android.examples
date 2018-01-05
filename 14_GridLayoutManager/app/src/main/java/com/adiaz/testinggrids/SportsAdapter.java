package com.adiaz.testinggrids;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.adiaz.testinggrids.retrofit.entities.SportsDeref;

import java.util.List;

/**
 * Created by adiaz on 30/12/17.
 */

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.SportsViewHolder> {

    List<SportsDeref> sportsList;
    Context mContext;
    public SportsAdapter(List<SportsDeref> sportsList) {
        this.sportsList = sportsList;
    }

    @Override
    public SportsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        int layoutIdForListItem = R.layout.sport_list_item;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new SportsAdapter.SportsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(SportsViewHolder holder, int position) {
        if (position==0) {
            holder.mSportName.setText(mContext.getString(R.string.favorites));
            holder.mImageSport.setImageResource(R.drawable.favorite);
        } else {
            SportsDeref sport = sportsList.get(position - 1);
            holder.mSportName.setText(sport.getTag());
            //int image = Utils.getResId("football", R.Drawable.class);
            try {
                int identifier = mContext.getResources().getIdentifier(sport.getImage(), "drawable", mContext.getPackageName());
                holder.mImageSport.setImageResource(identifier);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        return sportsList.size() + 1;
    }

    public class SportsViewHolder extends RecyclerView.ViewHolder {
        public final TextView mSportName;
        public final ImageView mImageSport;

        public SportsViewHolder(View itemView) {
            super(itemView);
            mSportName = (TextView) itemView.findViewById(R.id.tv_sport);
            mImageSport = (ImageView) itemView.findViewById(R.id.iv_sport);
        }
    }
}
