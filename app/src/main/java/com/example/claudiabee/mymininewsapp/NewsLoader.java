package com.example.claudiabee.mymininewsapp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;

/**
 * Loads a list of {@link News} by using an AsyncTask to perform the HTTP
 * request to the given URL.
 */
public class NewsLoader extends AsyncTaskLoader<List<News>> {

    /**
     * Tag for log message
     */
    private static final String LOG_TAG = NewsLoader.class.getName();

    /**
     * Query URL
     */
    private String mUrl;

    /**
     * Constructor for a new {@link NewsLoader}.
     *
     * @param context of the activity
     * @param url     to query for data
     */
    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    /**
     * Start to load data
     */
    protected void onStartLoading() {
        forceLoad();
        Log.d(LOG_TAG, "Verify Loader behaviour: onStartLoading");
    }

    /**
     * This method is invoked (or called) on a background thread, so we can perform
     * long-running operations like making a network request.
     *
     * @ return a {@link List<News>} object as the result.
     */
    @Nullable
    @Override
    public List<News> loadInBackground() {
        // Do not perform the request if there are no URLs.
        if (mUrl == null) {
            return null;
        }
        Log.d(LOG_TAG, "Verify Loader behaviour verified mUrl not null");
        // Perform the HTTP request for news, process and parse the response,
        // and extract a list of news.
        List<News> newsFeed = QueryUtils.fetchEarthquakeData(mUrl);
        Log.d(LOG_TAG, "Verify Loader behaviour: made a list of Earthquakes");

        return newsFeed;
    }
}
