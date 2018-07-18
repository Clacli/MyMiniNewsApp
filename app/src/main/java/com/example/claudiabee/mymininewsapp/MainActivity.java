package com.example.claudiabee.mymininewsapp;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    /** Tag to use in log message*/
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    /**
     * URL to query for news data from the Guardian dataset
     */
    // Add a personal key at the end of the url String before trying the app
    private static final String GUARDIAN_REQUEST_URL = "https://content.guardianapis.com/search?q=music&format=json&show-tags=contributor&api-key=";

    /** Constant value for the earthquake loader */
    public static final int LOADER_ID = 0;


    private RecyclerView mNewsCardRecyclerView;
    // This is the empty view
    TextView mEmptyView;
    // This is the Layout that manages the position of the {@link CardView}
    private RecyclerView.LayoutManager mNewsCardLinearLayoutManager;
    // This is the Adapter used to display the data of the list.
    private NewsCardAdapter mNewsCardAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_card_recycler);

        // Create an instance of the MyCustomRecyclerView
        mNewsCardRecyclerView = (RecyclerView) findViewById(R.id.news_card_recycler_view);

        // Create an instance of the empty TextView
        mEmptyView = (TextView) findViewById(R.id.empty_news_feed);

        // Create a new LinearLayout manager to manage the positioning of the news card view items
        mNewsCardLinearLayoutManager = new LinearLayoutManager(this);

        // Set the mNewsCardLinearLayoutManager on the newsCardRecyclerView
        mNewsCardRecyclerView.setLayoutManager(mNewsCardLinearLayoutManager);


        // Prepare the Loader. Either re-connect with an existing one, or start a new one
        getLoaderManager().initLoader(LOADER_ID, null, this);
        Log.d(LOG_TAG, "Verifying Loader behaviour: init loader");
    }

    // 1 First Loader callback to override onCreateLoader(){}
    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int i, @Nullable Bundle bundle) {
        Log.d(LOG_TAG, "Verifying Loader behaviour: onCreateLoader");
        // Create a new loader for the given URL
        return new NewsLoader(this, GUARDIAN_REQUEST_URL);
    }

    // 2 Second Loader callbacks to override
    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> newsFeed) {

        // Set empty state text to display "No earthquakes found."
        mEmptyView.setText(R.string.no_article_message);

        // If there is a valid list of {@linkNews}, then add them to the adapter's
        // data set.
       if (newsFeed != null && !newsFeed.isEmpty()) {
           mEmptyView.setVisibility(View.GONE);
           updateUi(newsFeed);
       }
        Log.d(LOG_TAG, "Verifying Loader behaviour: onLoadFinished.");
    }

    // 3 Third Loader callback method to override
    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
        // Loader reset, so we can clear out our existing data

        Log.d(LOG_TAG, "loader.reset?");
    }

    /**
     * Update the UI with the given news information.
     */
    private void updateUi(List<News> newsFeed) {

        // Initialize and use the adapter by calling the adapter's constructor and the
        // MyCustomRecyclerView's setAdapter method
        mNewsCardAdapter = new NewsCardAdapter(newsFeed, this);
        mNewsCardRecyclerView.setAdapter(mNewsCardAdapter);
    }
}
