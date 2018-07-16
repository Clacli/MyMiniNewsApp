package com.example.claudiabee.mymininewsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving news data from Guardian API.
 */
public class QueryUtils {


    private static final String LOG_TAG = QueryUtils.class.getName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {

    }

    /**
     * @return a list of {@link News} objects that has been built up from parsing a JSON response
     */
    public static ArrayList<News> extractNews() {

        String section;
        String newsTitle;
        String newsUrl;
        String dateOfWebPublication;
        String authorsName;


        // Create an empty ArrayList that we can start adding News to
        ArrayList<News> newsFeed = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON is
        // formatted, a JSONException object will be thrown.
        // Catch the exception so that the app doesn't crash, and print the error message to
        // the logs.
        try {

            // Turn the SAMPLE_JSON_RESPONSE String variable into a JSONObject.
            JSONObject root = new JSONObject(SAMPLE_JSON_RESPONSE);

            // Find the object nested within the root object with key "response" and store
            // it in a variable called "responseJsonObject"
            JSONObject responseJsonObject = root.getJSONObject("response");

            // Find the array nested within the responseJsonObject with key "results"
            // and store it in a variable called "resultsJsonArray".
            JSONArray resultsJsonArray = responseJsonObject.getJSONArray("results");

            // Iterate the results JsonArray and parse each News inside it
            for (int i = 0; i < resultsJsonArray.length(); i++) {

                // The JSONArray class exposes a method called getJSONObject to access and
                // get an object at a specified index position and then store in a
                // variable of  type JSONObject.
                JSONObject currentResult = resultsJsonArray.getJSONObject(i);

                // To find/retrieve the attributes within the currentResult JSONObject, we call the
                // method getString to extract the primitive value associated with the key "sectionName"
                section = currentResult.getString("sectionName");

                // To find/retrieve the attributes within the currentResult JSONObject, we call the
                // method getString to extract the primitive value associated with the key "newsTitle"
                newsTitle = currentResult.getString("webTitle");

                // To find/retrieve the attributes within the currentResult JSONObject, we call the
                // method getString to extract the primitive value associated with the key "webUrl"
                newsUrl = currentResult.getString("webUrl");

                try {
                    // To find/retrieve the attributes within the currentResult JSONObject, we call the
                    // method getString to extract the primitive value associated with the key "webPublicationDate"
                    dateOfWebPublication = currentResult.getString("webPublicationDate");

                } catch (JSONException e) {
                    Log.e(LOG_TAG, "Date of publication on the web is not specified", e);
                    dateOfWebPublication = null;
                }

                // Find the array nested within the response JSONObject with key "tags" and store it
                // in a variable called "tagsArray".
                JSONArray tagsArray = currentResult.getJSONArray("tags");

                try {
                    // Parse tagsArray to try to extract the news author's name which is not always
                    // specified
                    JSONObject contributor = tagsArray.getJSONObject(0);

                    authorsName = contributor.getString("webTitle");

                } catch (JSONException e) {

                    // If an error is thrown when trying to extract the author's name in the try block,
                    // catch the exception here, so the app doesn't crash.
                    // Print a log message with the message from the exception
                    Log.e(LOG_TAG, "Problem parsing the newsFeed JSON response, no author is provided for this piece of news", e);
                    authorsName = null;
                }

                // Create a News object instance
                News newsItem = new News(section, dateOfWebPublication, authorsName, newsTitle, newsUrl);

                // Add this newsItem to the list of News
                newsFeed.add(newsItem);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the try block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception
            Log.e(LOG_TAG, "Problem parsing the newsFeed JSON response", e);
        }

        // return the list of news
        return newsFeed;
    }
}
