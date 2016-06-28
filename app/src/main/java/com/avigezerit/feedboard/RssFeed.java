package com.avigezerit.feedboard;

/**
 * Created by Shaharli on 28/06/2016.
 */
public class RssFeed {

    public int id;
    public String rssFeedTitle;
    public String rssFeedAddress;

    public RssFeed(String rssFeedTitle, String rssFeedAddress) {
        this.rssFeedTitle = rssFeedTitle;
        this.rssFeedAddress = rssFeedAddress;
    }

    public RssFeed(int feedId, String rssFeedTitle, String rssFeedAddress) {
        this.id = id;
        this.rssFeedTitle = rssFeedTitle;
        this.rssFeedAddress = rssFeedAddress;
        this.id = feedId;
    }
}
