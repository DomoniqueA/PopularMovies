package com.example.android.popularmovies.utilities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.data.TrailerPOJO;

import java.util.ArrayList;

/**
 * {@link RecyclerView.Adapter} that can display a {@link TrailerPOJO} and makes a call to the
 * specified {@link }.
 */
public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.TrailerViewHolder> {

    private final TrailerAdapterOnClickHandler tClickHandler;
    private ArrayList<TrailerPOJO> trailersList = new ArrayList<>();


    public TrailersAdapter(TrailerAdapterOnClickHandler clickHandler) {
        tClickHandler = clickHandler;

    }

    public void setTrailers(ArrayList<TrailerPOJO> results) {
        trailersList.clear();
        trailersList.addAll(results);
        notifyDataSetChanged();
    }

    @Override
    public TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trailers_view, parent, false);
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TrailerViewHolder holder, int position) {
        TrailerPOJO thisTrailerPOJO = trailersList.get(position);
        holder.mItem = thisTrailerPOJO;
        String mLink = thisTrailerPOJO.getTrailerLink();
        holder.mLinkView.setText(mLink);
        holder.mLinkName.setText(thisTrailerPOJO.getName());
    }

    @Override
    public int getItemCount() {
        return trailersList.size();
    }

    public interface TrailerAdapterOnClickHandler {
        void onClick(TrailerPOJO clickedTrailerLink);
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final View mView;
        public final TextView mLinkName;
        public final TextView mLinkView;
        public TrailerPOJO mItem;

        public TrailerViewHolder(View view) {
            super(view);
            mView = view;
            view.setOnClickListener(this);
            mLinkName = (TextView) view.findViewById(R.id.linkName);
            mLinkView = (TextView) view.findViewById(R.id.trailerLink);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            TrailerPOJO clickedTrailer = trailersList.get(adapterPosition);
            tClickHandler.onClick(clickedTrailer);
        }
    }
}
