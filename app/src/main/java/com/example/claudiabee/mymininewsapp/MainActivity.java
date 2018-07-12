package com.example.claudiabee.mymininewsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Declare member variable of MainActivity
    private RecyclerView mNewsCardRecyclerView;
    private RecyclerView.LayoutManager mNewsCardLinearLayoutManager;
    private NewsCardAdapter mNewsCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_card_recycler);

        // Create a list of News
        ArrayList<News> newsFeed = new ArrayList<>();
        newsFeed.add(new News("clouds", "20 Jan 2018", "Mysterious writer", "Clouds topology", "urlOfCloudsWeb"));
        newsFeed.add(new News("plants", "01 March 2018", "Flora", "It's springtime, we're back green", "urlGreens2"));
        newsFeed.add(new News("clouds", "20 Jan 2018", "Clouds topology", "urlOfCloudsWeb"));
        newsFeed.add(new News("plants", "01 March 2018", "Flora", "It's springtime, we're back green", "urlGreens2"));
        newsFeed.add(new News("clouds", "20 Jan 2018", "Mysterious writer", "Clouds topology", "urlOfCloudsWeb"));
        newsFeed.add(new News("plants", "01 March 2018", "It's springtime, we're back green", "urlGreens2"));
        newsFeed.add(new News("clouds", "20 Jan 2018", "Mysterious writer", "Clouds topology", "urlOfCloudsWeb"));
        newsFeed.add(new News("plants", "01 March 2018", "Flora", "It's springtime, we're back green", "urlGreens2"));

        // Once you have added a RecyclerView widget to your layout, obtain a handle to the object,
        // connect it to a layout manager, and attach an adapter for the data to be displayed:

        // Create an instance of the RecyclerView class
        mNewsCardRecyclerView = findViewById(R.id.news_card_recycler_view);

        // Create a new LinearLayout manager to manage the positioning of the news card view items
        mNewsCardLinearLayoutManager = new LinearLayoutManager(this);

        // Set the mNewsCardLinearLayoutManager on the newsCardRecyclerView
        mNewsCardRecyclerView.setLayoutManager(mNewsCardLinearLayoutManager);

        // Initialize and use the adapter by calling the adapter's constructor and the
        // RecyclerView's setAdapter method
        mNewsCardAdapter = new NewsCardAdapter(newsFeed);
        mNewsCardRecyclerView.setAdapter(mNewsCardAdapter);
    }
}
