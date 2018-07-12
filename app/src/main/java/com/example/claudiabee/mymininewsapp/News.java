package com.example.claudiabee.mymininewsapp;

/**
 * {@link News} represents a piece of information retrieved from the Guardian feed on the web
 * through the Guardian API.
 * It contains the name of the section to which the news belong, i.e. what the news is about,
 * the the date it was published on the web, its author (but not always), its title.
 */
public class News {

    /**
     * The name of the section of the newspaper
     */
    private String mNewsSection;

    /**
     * the date the news was publishes on the web
     */
    private String mWebPublicationDate;

    /**
     * The news author's name
     */
    private String mNewsAuthor;

    /**
     * The title of the news
     */
    private String mNewsTitle;

    /**
     * The url for the news
     */
    private String mNewsWebUrl;

    // Create a new News object. Overloaded constructor, in one for example author's name
    // might be missing

    News(String newsSection, String webPublicationDate, String newsTitle, String newsWebUrl) {
        mNewsSection = newsSection;
        mWebPublicationDate = webPublicationDate;
        mNewsTitle = newsTitle;
        mNewsWebUrl = newsWebUrl;
    }

    News(String newsSection, String webPublicationDate, String newsAuthor, String newsTitle, String newsWebUrl) {
        mNewsSection = newsSection;
        mWebPublicationDate = webPublicationDate;
        mNewsAuthor = newsAuthor;
        mNewsTitle = newsTitle;
        mNewsWebUrl = newsWebUrl;
    }

    /**
     * Return the Section the news belongs to on the Guardian site
     */
    public String getNewsSection() {
        return mNewsSection;
    }

    /**
     * Return the author's name of the news
     */
    public String getNewsAuthor() {
        return mNewsAuthor;
    }

    /**
     * Return the date of the publication of the news on the web
     */
    public String getWebPublicationDate() {
        return mWebPublicationDate;
    }

    /**
     * Return the title of the news
     */
    public String getNewsTitle() {
        return mNewsTitle;
    }

    /**
     * Return the url to the news as a String
     */
    public String getNewsWebUrl() {
        return mNewsWebUrl;
    }

    /**
     * Return the content of the News class as a                                                                      String
     */
    @Override
    public String toString() {
        return "This News: " + "mNewsSection is " + mNewsSection + ", mWebPublicationDate is " + mWebPublicationDate + ", mNewsAuthor is " + mNewsAuthor + ", mNewsTitle is " + mNewsTitle + ", mNewsWebUrl is " + mNewsWebUrl;
    }
}
