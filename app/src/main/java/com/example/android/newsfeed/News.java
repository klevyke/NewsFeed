package com.example.android.newsfeed;

/**
 * Created by Levy on 31.05.2018.
 */

public class News {
    private String title;
    private String section;
    private String date;
    private String author;
    private String url;

    public News(String title, String section, String date, String author, String url) {
        this.title = title;
        this.section = section;
        this.date = date;
        this.author = author;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getSection() {
        return section;
    }

    public String getDate() {
        return date;
    }

    /*
    /* Returns the date in a user friendly format
     */
    public String getUserFriendlyDate() {

        return getDate().replace("T", " ").replace("-",".").replace("Z","");
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

}
