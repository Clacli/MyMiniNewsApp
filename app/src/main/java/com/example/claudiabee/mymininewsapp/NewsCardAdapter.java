package com.example.claudiabee.mymininewsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewsCardAdapter extends RecyclerView.Adapter<NewsCardAdapter.NewsCardViewHolder> {

    private final static String LOG_TAG = NewsCardAdapter.class.getSimpleName();

    private Context mContext;

    NewsCardAdapter(List<News> newsFeed, Context context) {
        this.newsFeed = newsFeed;
        mContext = context;
    }

    // Constructor for the custom adapter, so that it has a handle to the data that the
    // RecyclerView displays
    private List<News> newsFeed;

    /**
     * This method is called when the {@link NewsCardViewHolder} needs to be initialized.
     * It is specified the layout that each item of the RecyclerView should use, inflating the layout
     * using LayoutInflater, passing the output of the constructor of the custom ViewHolder.
     */
    @NonNull
    @Override
    public NewsCardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View singleNewsItem = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_card_item, viewGroup, false);
        return new NewsCardViewHolder(singleNewsItem);
    }

    /**
     * This method specifies the content of each item of the RecyclerView.
     */
    @Override
    public void onBindViewHolder(@NonNull NewsCardViewHolder newsHolder, int position) {

        final News data = newsFeed.get(position);

        // Set text of the section name
        newsHolder.newsSectionTextView.setText(data.getNewsSection());

        // Date of publication on the web yyyy-MM-dd
        String dateOfWebPublication = data.getDateOfWebPublication();

        // Formatted date as EEE MMM d, yy
        String desiredPatternDate = formatDate(dateOfWebPublication);

        // Check if a date of publication on the web is provided for this News or not
        if (TextUtils.isEmpty(data.getDateOfWebPublication())) {
            newsHolder.dateOfWebPublicationTextView.setVisibility(View.GONE);
        } else {
            newsHolder.dateOfWebPublicationTextView.setText(desiredPatternDate);
            newsHolder.dateOfWebPublicationTextView.setVisibility(View.VISIBLE);
        }
        // Check if an author is provided for this News or not
        if (TextUtils.isEmpty(data.getNewsAuthor())) {
            newsHolder.authorNameTextView.setVisibility(View.GONE);
        } else {
            newsHolder.authorNameTextView.setText(data.getNewsAuthor());
            newsHolder.authorNameTextView.setVisibility(View.VISIBLE);
        }

        newsHolder.newsTitleTextView.setText(data.getNewsTitle());
        // Send an intent to get the news on the Guardian site
        newsHolder.newsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = data.getNewsUrl();
                openThisWebPage(url);
            }
        });

        // Hide the error message TextView
        newsHolder.noDataMessageTextView.setVisibility(View.GONE);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // RecyclerView.Adapter has three abstract methods that must be overridden

    /**
     * This method
     *
     * @return the number of items present in the list
     */
    @Override
    public int getItemCount() {
        return newsFeed.size();
    }

    /**
     * Return the formatted date String (i.e. "Mar 3, 1984") from a Date Object.
     */
    private String formatDate(String unformattedDate) {

        // Initialize an instance of SimpleDateFormat configuring it to the format I already have
        SimpleDateFormat inputPatternFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());

        // Initialize an instance of SimpleDateFormat configuring it to the desired format
        SimpleDateFormat desideredPatternFormat = new SimpleDateFormat("EEE MMM d,' ' yyyy", Locale.getDefault());

        String desideredPatternDate = "";

        try {
            // Create a Date object, required by SimpleDateFormat, calling parse() method on
            // inputPatternDate passing as argument dateOfWebPublication String.
            Date webPublicationDateObj = inputPatternFormat.parse(unformattedDate);

            // C
            return desideredPatternFormat.format(webPublicationDateObj);

        } catch (ParseException e) {
            Log.e(LOG_TAG, "Problem in parsing ");
        }
        return desideredPatternDate;
    }

    // Helper method
    private void openThisWebPage(String url) {
        Uri webPage = Uri.parse(url);
        Intent i = new Intent(Intent.ACTION_VIEW, webPage);
        if (i.resolveActivity(mContext.getPackageManager()) != null) {
            mContext.startActivity(i);
        }
    }

    // Define a custom class that extends RecyclerView.ViewHolder.
    // We are going to reuse the card_item layout, that represent a news item,
    // providing a reference to the views for each data item and
    // initializing the views that belong to the items of the RicyclerView
    // inside the constructor of this custom View.Holder.
    public static class NewsCardViewHolder extends RecyclerView.ViewHolder {
        CardView newsCardView;
        TextView newsSectionTextView;
        TextView newsTitleTextView;
        TextView authorNameTextView;
        TextView dateOfWebPublicationTextView;
        TextView noDataMessageTextView;


        NewsCardViewHolder(View itemView) {
            super(itemView);
            newsCardView = itemView.findViewById(R.id.card_item);
            newsSectionTextView = itemView.findViewById(R.id.news_section);
            newsTitleTextView = itemView.findViewById(R.id.news_title);
            authorNameTextView = itemView.findViewById(R.id.news_author);
            dateOfWebPublicationTextView = itemView.findViewById(R.id.date_of_web_publication);
            noDataMessageTextView = itemView.findViewById(R.id.no_data_message);
        }

    }
}