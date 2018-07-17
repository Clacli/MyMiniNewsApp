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
    private String mSection;

    /**
     * The title of the news
     */
    private String mTitle;

    /**
     * The news author's name
     */
    private String mAuthor;

    /**
     * the date the news was publishes on the web
     */
    private String mDateOfWebPublication;

    /**
     * The url for the news
     */
    private String mUrl;

    /**
     * Create a new {@link News} object.
     *
     * @param section              is the section/topic the news is about
     * @param title                is the title of the news
     * @param author               is the author of the news
     * @param dateOfWebPublication is the date the news was published on the web
     * @param url                  the url of the page on the Guardian site displaying the news
     */
    public News(String section, String dateOfWebPublication, String author, String title, String url) {
        mSection = section;
        mDateOfWebPublication = dateOfWebPublication;
        mAuthor = author;
        mTitle = title;
        mUrl = url;
    }

    /**
     * Return the Section the news belongs to on the Guardian site
     */
    public String getNewsSection() {
        return mSection;
    }

    /**
     * Return the title of the news
     */
    public String getNewsTitle() {
        return mTitle;
    }

    /**
     * Return the author's name of the news
     */
    public String getNewsAuthor() {
        return mAuthor;
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
        return mUrl;
    }

    /**
     * Return the string representation of the {@link News} object
     */
    @Override
    public String toString() {
        return "This News: " + "mSection is " + mSection + ", mDateOfWebPublication is " + mDateOfWebPublication + ", mAuthor is " + mAuthor + ", mTitle is " + mTitle + ", mUrl is " + mUrl;
    }
}
