package com.example.claudiabee.mymininewsapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    /**
     * URL for news data from the Guardian dataset
     */
    // Add a personal key at the end of the url String before trying the app
    private static final String GUARDIAN_REQUEST_URL = "https://content.guardianapis.com/search?q=technology&format=json&show-tags=contributor&api-key=/*ADD_YOUR_PERSONAL_KEY _FOR_GUARDIAN_API_HERE*/";

    // Declare member variable of MainActivity
    private RecyclerView mNewsCardRecyclerView;
    private RecyclerView.LayoutManager mNewsCardLinearLayoutManager;
    private NewsCardAdapter mNewsCardAdapter;
    private CardView newsItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_card_recycler);

        /** Create an {@link AsyncTask} to perform the HTTP request to the given URL
         * on a background thread. When the result is received on the main UI thread,
         * then update the UI.
         */
        NewsAsyncTask task = new NewsAsyncTask();

        // Retrieve data from internet and display to the screen
        task.execute(GUARDIAN_REQUEST_URL);
    }

    /**
     * Update the UI with the given news information.
     */
    private void updateUi(List<News> newsFeed) {
        // Create an instance of the RecyclerView class
        mNewsCardRecyclerView = findViewById(R.id.news_card_recycler_view);

        // Once you have added a RecyclerView widget to your layout, obtain a handle to the object,
        // connect it to a layout manager, and attach an adapter for the data to be displayed:

        // Create a new LinearLayout manager to manage the positioning of the news card view items
        mNewsCardLinearLayoutManager = new LinearLayoutManager(this);

        // Set the mNewsCardLinearLayoutManager on the newsCardRecyclerView
        mNewsCardRecyclerView.setLayoutManager(mNewsCardLinearLayoutManager);

        // Initialize and use the adapter by calling the adapter's constructor and the
        // RecyclerView's setAdapter method
        mNewsCardAdapter = new NewsCardAdapter(newsFeed, this);
        mNewsCardRecyclerView.setAdapter(mNewsCardAdapter);
    }

    /**
     * {@link AsyncTask} to perform the network request on a background thread, and then
     * update the UI with the list of news items.
     */
    private class NewsAsyncTask extends AsyncTask<String, Void, List<News>> {

        /**
         * This method is invoked (or called) on a background thread, so we can perform
         * long-running operations like making a network request.
         * <p>
         * It is NOT ok to update the UI from the background thread, so we ust return a
         * {@link List<News>} object as the result.
         */
        @Override
        protected List<News> doInBackground(String... urls) {

            // Do not perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            // Perform the HTTP request for news data and process the response.
            List<News> result = QueryUtils.fetchEarthquakeData(urls[0]);

            return result;
        }

        @Override
        protected void onPostExecute(List<News> result) {

            // If there is no result, do nothing
            if (result == null) {
                return;
            }
            // Update the information displayed to the user.
            updateUi(result);
        }
    }
}
