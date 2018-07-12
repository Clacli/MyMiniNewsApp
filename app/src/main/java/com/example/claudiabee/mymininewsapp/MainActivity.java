package com.example.claudiabee.mymininewsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Declare member variable of MainActivity
    private RecyclerView mNewsCardRecyclerView;
    private RecyclerView.LayoutManager mNewsCardLinearLayoutManager;
    private NewsCardAdapter mNewsCardAdapter;
    private CardView newsItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_card_recycler);

        // Create a list of {@link News}
        ArrayList<News> newsFeed = QueryUtils.extractNews();

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
        mNewsCardAdapter = new NewsCardAdapter(newsFeed, this);
        mNewsCardRecyclerView.setAdapter(mNewsCardAdapter);


    }
}
