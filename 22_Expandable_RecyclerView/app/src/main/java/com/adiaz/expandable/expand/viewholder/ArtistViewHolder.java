package com.adiaz.expandable.expand.viewholder;

import android.view.View;
import android.widget.TextView;

import com.adiaz.expandable.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class ArtistViewHolder extends ChildViewHolder {

    private TextView childTextView;

    public ArtistViewHolder(View itemView) {
        super(itemView);
        childTextView = itemView.findViewById(R.id.list_item_artist_name);
    }

    public void setArtistName(String name) {
        childTextView.setText(name);
    }
}
