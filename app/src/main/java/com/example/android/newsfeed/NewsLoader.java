package com.example.android.newsfeed;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Levy on 31.05.2018.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    String urls[];

    /* Constructor */
    public NewsLoader (Context context, String... urls) {
        super(context);
        this.urls = urls;
    }
    /* Do background tasks */
    @Override
    public List<News> loadInBackground() {
        // Don't perform the request if there are no URLs, or the first URL is null.
        if (urls.length < 1 || urls[0] == null) {
            return null;
        }

        List<News> result = QueryUtils.fetchEarthquakeData(urls[0]);
        return result;
    }
    /* Start loading data in background */
    @Override
    protected void onStartLoading() {
        forceLoad();
        super.onStartLoading();
    }
}
