package com.example.android.popularmovies.utilities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.data.ReviewPOJO;

import java.util.ArrayList;

/**
 * Created by domoniqueasmith on 15/05/2017.
 */

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder> {
    private ArrayList<ReviewPOJO> reviewsList;


    public ReviewsAdapter() {
        reviewsList = new ArrayList<>();
    }

    public void setReviews(ArrayList<ReviewPOJO> results) {
        reviewsList.clear();
        reviewsList.addAll(results);
        notifyDataSetChanged();
    }

    @Override
    public ReviewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reviews_view, parent, false);
        return new ReviewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ReviewsViewHolder holder, int position) {
        ReviewPOJO thisReviewPOJO = reviewsList.get(position);
        holder.mItem = thisReviewPOJO;
        holder.rContent.setText(thisReviewPOJO.getContent());
        holder.mAuthor.setText(thisReviewPOJO.getAuthor());
        holder.rUrl.setText(thisReviewPOJO.getUrl());
    }

    @Override
    public int getItemCount() {
        return reviewsList.size();
    }


    public class ReviewsViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView rContent;
        public final TextView mAuthor;
        public final TextView rUrl;
        public ReviewPOJO mItem;

        public ReviewsViewHolder(View view) {
            super(view);
            mView = view;
            rContent = (TextView) view.findViewById(R.id.content);
            mAuthor = (TextView) view.findViewById(R.id.author);
            rUrl = (TextView) view.findViewById(R.id.url);
        }
    }
}
