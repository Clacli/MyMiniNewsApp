package com.example.claudiabee.mymininewsapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper methods related to requesting and receiving news data from Guardian API.
 */
public class QueryUtils {

    /** Tag to use in log message*/
    private static final String LOG_TAG = QueryUtils.class.getName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {

    }

    /**
     * Query the Guardian dataset and return an {@link News} object to represent a single news.
     */
    public static List<News> fetchEarthquakeData(String requestUrl) {

        // Create an URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a {@link List<News>} object
        List<News> newsFeed = extractFeatureFromJson(jsonResponse);

        // Return the {@link List<News>}
        return newsFeed;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        // Declare URL object and initialize to null
        URL url = null;

        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {

        // Declare jsonResponse String and initialize it to an empty string
        String jsonResponse = "";

        // If the URL is null, then return early
        if (url == null) {
            return jsonResponse;
        }

        // Declare an HttpURLConnection object and initialize it to null
        HttpURLConnection urlConnection = null;

        // Declare an InputStream response object and initialize it to null
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds*/);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the news JSON result.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        // Create a new StringBuilder object
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            // Create a new InputStreamReader, passing as arguments
            // the inputStream received and the UTF-8 Charset
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return an {@link News} object by parsing out information
     * about the first earthquake from the input earthquakeJSON string.
     */
    private static List<News> extractFeatureFromJson(String newsJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(newsJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding single news to.
        List<News> newsFeed = new ArrayList<>();

        // Declare variables for the components of the List
        String section;
        String newsTitle;
        String newsUrl;
        String dateOfWebPublication;
        String authorsName;

        // Try to parse the newsJSON. If there's a problem with the way JSON
        // is formatted, a JSONException object will be thrown. Catch the exception
        // so the app doesn't crash, and print the error message to the logs.
        try {
            // Turn the SAMPLE_JSON_RESPONSE String variable into a JSONObject.
            JSONObject root = new JSONObject(newsJSON);

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
                    authorsName = null;
                }

                // Create a {@link News} object instance
                News newsItem = new News(section, dateOfWebPublication, authorsName, newsTitle, newsUrl);

                // Add this newsItem to the list of {@link News}
                newsFeed.add(newsItem);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the try block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception
            Log.e(LOG_TAG, "Problem parsing the newsFeed JSON response", e);
        }
        // return the list of News object
        return newsFeed;
    }


}
