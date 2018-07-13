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
     * The title of the news
     */
    private String mNewsTitle;

    /**
     * The news author's name
     */
    private String mNewsAuthor;

    /**
     * the date the news was publishes on the web
     */
    private String mDateOfWebPublication;

    /**
     * The url for the news
     */
    private String mNewsUrl;

    /**
     * Create a new {@link News} object.
     *
     * @param newsSection is the section/topic the news is about
     * @param newsTitle   is the title of the news
     * @param newsUrl     the url of the page on the Guardian site displaying the news
     */
    public News(String newsSection, String newsTitle, String newsUrl) {
        mNewsSection = newsSection;
        mNewsTitle = newsTitle;
        mNewsUrl = newsUrl;
    }


    /**
     * Create a new {@link News} object.
     *
     * @param newsSection        is the section/topic the news is about
     * @param newsTitle          is the title of the news
     * @param dateOfWebPublication is the date the news was published on the web
     * @param newsUrl            the url of the page on the Guardian site displaying the news
     */
    public News(String newsSection, String dateOfWebPublication, String newsTitle, String newsUrl) {
        mNewsSection = newsSection;
        mNewsTitle = newsTitle;
        mDateOfWebPublication = dateOfWebPublication;
        mNewsUrl = newsUrl;
    }

    /**
     * Create a new {@link News} object.
     *
     * @param newsSection        is the section/topic the news is about
     * @param newsTitle          is the title of the news
     * @param newsAuthor         is the author of the news
     * @param dateOfWebPublication is the date the news was published on the web
     * @param newsUrl            the url of the page on the Guardian site displaying the news
     */
    public News(String newsSection, String dateOfWebPublication, String newsAuthor, String newsTitle, String newsUrl) {
        mNewsSection = newsSection;
        mDateOfWebPublication = dateOfWebPublication;
        mNewsAuthor = newsAuthor;
        mNewsTitle = newsTitle;
        mNewsUrl = newsUrl;
    }

    /**
     * Return the Section the news belongs to on the Guardian site
     */
    public String getNewsSection() {
        return mNewsSection;
    }

    /**
     * Return the title of the news
     */
    public String getNewsTitle() {
        return mNewsTitle;
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
    public String getDateOfWebPublication() {
        return mDateOfWebPublication;
    }

    /**
     * Return the url to the news as a String
     */
    public String getNewsUrl() {
        return mNewsUrl;
    }

    /**
     * Return the string representation of the {@link News} object
     */
    @Override
    public String toString() {
        return "This News: " + "mNewsSection is " + mNewsSection + ", mDateOfWebPublication is " + mDateOfWebPublication + ", mNewsAuthor is " + mNewsAuthor + ", mNewsTitle is " + mNewsTitle +
                ", mNewsUrl is " + mNewsUrl;
    }
}
