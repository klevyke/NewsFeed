package com.example.android.newsfeed;

/**
 * Created by Levy on 31.05.2018.
 */

public class News {
    private String title;
    private String section;
    private Long date;
    private String url;

    public News(String title, String section, Long date, String url) {
        this.title = title;
        this.section = section;
        this.date = date;
        this.url = url;
    }

    public String getTile() {
        return title;
    }

    public String getSection() {
        return section;
    }

    public Long getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

}
