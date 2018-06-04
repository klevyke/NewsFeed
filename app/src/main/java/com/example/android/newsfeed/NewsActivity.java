package com.example.android.newsfeed;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>>{

    private static final int NEWS_LOADER_ID = 1;

    /** JSON url String */
    private static final String JSON_URL = "https://content.guardianapis.com/search?q=roland%20garros&format=json&show-fields=byline,trailText&order-by=newest&api-key=test";

    /** Adapter for the list of news */
    private NewsAdapter mAdapter;

    /** Textview for empty state message **/
    TextView emptyState;

    /** Progressbar while loading news **/
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        // Find a reference to the {@link ListView} in the layout
        ListView newsListView = (ListView) findViewById(R.id.list);

        // Find a reference to the {@link ListView} in the layout
        emptyState = (TextView)findViewById(R.id.empty_state);
        newsListView.setEmptyView(emptyState);

        // Find a reference to the {@link ListView} in the layout
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        // Create a new {@link ArrayAdapter} of earthquakes
        mAdapter = new NewsAdapter(this, new ArrayList<News>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        newsListView.setAdapter(mAdapter);

        // Initialiye the ConnectivityManages and check the internet connection and display a message if no connection found
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
            emptyState.setText(R.string.no_connection);
            progressBar.setVisibility(View.GONE);
        } else {
            // Initialize the Loader
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        }

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected earthquake.
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                News currentEarthquake = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
    }

    /*
     *  Start the Loader and expect a List of News objects
     */
    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this, JSON_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {

        // Clear the adapter of previous data
        mAdapter.clear();

        // If there is a valid list of News, then add them to the adapter's
        // data set else set the empty textview
        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        } else {
            emptyState.setText(R.string.no_records);
        }
        // Hide the ProgressBar
        progressBar.setVisibility(View.GONE);
    }

    // Reset adapter on Loader reset
    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        mAdapter.clear();
    }
}
