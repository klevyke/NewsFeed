package com.example.android.newsfeed;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Levy on 31.05.2018.
 */

public class NewsAdapter extends ArrayAdapter<News> {
    /**
     * Constructor
     * @param context
     * @param news
     */
    public NewsAdapter(@NonNull Context context, @NonNull ArrayList<News> news) {
        super(context, 0, news);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView==null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        News news = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID title
        TextView titleTextView = (TextView) convertView.findViewById(R.id.title);

        // Get the name of the current News object and set this text on the TextView
        titleTextView.setText(news.getTitle());

        // Find the TextView in the list_item.xml layout with the ID title
        TextView sectionTextView = (TextView) convertView.findViewById(R.id.section);

        // Get the name of the current News object and set this text on the TextView
        sectionTextView.setText(news.getSection());

        // Find the TextView in the list_item.xml layout with the ID title
        TextView dateTextView = (TextView) convertView.findViewById(R.id.date);

        // Get the date of the current News object and set this text on the TextView
        dateTextView.setText(news.getUserFriendlyDate());

        // Find the TextView in the list_item.xml layout with the ID title
        TextView authorTextView = (TextView) convertView.findViewById(R.id.author);

        // Get the date of the current News object and set this text on the TextView
        authorTextView.setText(news.getAuthor());

        final String url = news.getUrl();
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                getContext().startActivity(i);
            }
        });

        return convertView;
    }
}
