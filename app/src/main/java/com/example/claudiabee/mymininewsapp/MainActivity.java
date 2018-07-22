package com.example.claudiabee.mymininewsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    public static final String STUDENT_API_KEY = "ADD_YOUR_API_KEY_HERE";
    // Bind resources with Butter Knife
    @BindString(R.string.no_internet_connection_message) String noInternetConnectionMessage;
    @BindString(R.string.no_article_found) String noArticleFound;

    /**
     * Tag to use in log message
     */
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    /**
     * URL to query for news data from the Guardian dataset
     */
    // Add a personal key at the end of the url String before trying the app
    private static final String GUARDIAN_REQUEST_URL = "https://content.guardianapis.com/search";

    /**
     * Constant value for the earthquake loader
     */
    public static final int LOADER_ID = 0;

    //private
    @BindView(R.id.news_card_recycler_view) RecyclerView mNewsCardRecyclerView;

    // This is the empty view
    @BindView(R.id.empty_news_feed) TextView mEmptyView;

    // This is the loading indicator

    @BindView(R.id.loading_spinner) View mLoadingIndicator;

    // This is the Layout that manages the position of the {@link CardView}
    private RecyclerView.LayoutManager mNewsCardLinearLayoutManager;
    // This is the Adapter used to display the data of the list.
    private NewsCardAdapter mNewsCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_card_recycler);
        ButterKnife.bind(this);
        // getActionBar().setDisplayHomeAsUpEnabled(true);

        // Create a new LinearLayout manager to manage the positioning of the news card view items
        mNewsCardLinearLayoutManager = new LinearLayoutManager(this);

        // Set the mNewsCardLinearLayoutManager on the newsCardRecyclerView
        mNewsCardRecyclerView.setLayoutManager(mNewsCardLinearLayoutManager);

        // Check for network connectivity.
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();

        // If there is internet connectivity initialize the loader.
        if (isConnected) {
            // Prepare the Loader. Either re-connect with an existing one, or start a new one
            getLoaderManager().initLoader(LOADER_ID, null, this);
        } else {
            mLoadingIndicator.setVisibility(View.GONE);
            mEmptyView.setText(noInternetConnectionMessage);
        }
    }

    // 1 First Loader callback to override onCreateLoader(){}
    @NonNull
    @Override
    // onCreateLoader instantiates and returns a new Loader for the given ID
    public Loader<List<News>> onCreateLoader(int i, @Nullable Bundle bundle) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // getString retrieves a String value from the preferences (topic). The second parameter is the
        // default value for this preference.
        String topic = sharedPreferences.getString(getString(R.string.settings_topic_key), getString(R.string.settings_topic_default));

        // getString retrieves a String value from the preferences (order by). The second parameter is the
        // default value for this preference.
        String orderBy = sharedPreferences.getString(getString(R.string.settings_order_by_key), getString(R.string.settings_order_by_default));

        // parse breaks apart the URI string that's passed into its parameter
        Uri baseUri = Uri.parse(GUARDIAN_REQUEST_URL);

        // buildUpon prepares the baseUri that we just parsed so we can add query parameters to it
        Uri.Builder uriBuilder = baseUri.buildUpon();

        // Append query parameter and its value. For example, the 'format=json'
        uriBuilder.appendQueryParameter("q", topic);
        uriBuilder.appendQueryParameter("format", "json");
        uriBuilder.appendQueryParameter("order-by", orderBy);
        uriBuilder.appendQueryParameter("show-tags", "contributor");
        uriBuilder.appendQueryParameter("api-key", STUDENT_API_KEY);

        //  Create a new loader for the given URL
        return new NewsLoader(this, uriBuilder.toString());
    }

    // 2 Second Loader callbacks to override
    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> newsFeed) {

        // Hide the loading indicator
        mLoadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No earthquakes found."
        mEmptyView.setText(noArticleFound);

        // Clear the adapter of previous existing data
        mNewsCardAdapter = null;

        // If there is a valid list of {@linkNews}, then add them to the adapter's
        // data set.
        if (newsFeed != null && !newsFeed.isEmpty()) {
            mEmptyView.setVisibility(View.GONE);
            updateUi(newsFeed);
        }
    }

    // 3 Third Loader callback method to override
    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
        // Loader reset, so we can clear out our existing data
        mNewsCardAdapter = null;
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


    @Override
    // This methods initialize the content of the Option Menu of the Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the Options Menu specified in the main.xml
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    // This methods opens the SettingsActivity via an intent when menu item is selected
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
