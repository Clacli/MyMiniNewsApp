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

    private static final String SAMPLE_JSON_RESPONSE = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":64354,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":6436,\"orderBy\":\"relevance\",\"results\":[{\"id\":\"lifeandstyle/2018/jul/12/red-wine-warm-weather-summer\",\"type\":\"article\",\"sectionId\":\"lifeandstyle\",\"sectionName\":\"Life and style\",\"webPublicationDate\":\"2018-07-12T11:00:40Z\",\"webTitle\":\"Hot reds for warm weather\",\"webUrl\":\"https://www.theguardian.com/lifeandstyle/2018/jul/12/red-wine-warm-weather-summer\",\"apiUrl\":\"https://content.guardianapis.com/lifeandstyle/2018/jul/12/red-wine-warm-weather-summer\",\"tags\":[{\"id\":\"profile/fionabeckett\",\"type\":\"contributor\",\"webTitle\":\"Fiona Beckett\",\"webUrl\":\"https://www.theguardian.com/profile/fionabeckett\",\"apiUrl\":\"https://content.guardianapis.com/profile/fionabeckett\",\"references\":[],\"bio\":\"<p>Fiona Beckett is the Guardian's wine writer, a cookery writer and a contributing editor to Decanter and Fork magazines</p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/contributor/2015/2/1/1422809877301/Fiona-Beckett.jpg\",\"bylineLargeImageUrl\":\"https://uploads.guim.co.uk/2017/10/06/Fiona-Beckett,-L.png\",\"firstName\":\"beckett\",\"lastName\":\"\",\"twitterHandle\":\"winematcher\"}],\"isHosted\":false,\"pillarId\":\"pillar/lifestyle\",\"pillarName\":\"Lifestyle\"},{\"id\":\"technology/2018/jun/30/weather-forecast-apps-smartphone-predictions-forecasting\",\"type\":\"article\",\"sectionId\":\"technology\",\"sectionName\":\"Technology\",\"webPublicationDate\":\"2018-06-30T14:00:13Z\",\"webTitle\":\"Why are all my weather apps different?\",\"webUrl\":\"https://www.theguardian.com/technology/2018/jun/30/weather-forecast-apps-smartphone-predictions-forecasting\",\"apiUrl\":\"https://content.guardianapis.com/technology/2018/jun/30/weather-forecast-apps-smartphone-predictions-forecasting\",\"tags\":[{\"id\":\"profile/nic-fleming\",\"type\":\"contributor\",\"webTitle\":\"Nic Fleming\",\"webUrl\":\"https://www.theguardian.com/profile/nic-fleming\",\"apiUrl\":\"https://content.guardianapis.com/profile/nic-fleming\",\"references\":[],\"bio\":\"<p><strong>Nic Fleming</strong> is a freelance science journalist</p>\",\"firstName\":\"fleming\",\"lastName\":\"nic\"}],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"environment/2018/jul/02/dry-uk-weather-conditions-boosts-rare-butterflies\",\"type\":\"article\",\"sectionId\":\"environment\",\"sectionName\":\"Environment\",\"webPublicationDate\":\"2018-07-01T23:01:53Z\",\"webTitle\":\"Dry weather boosts UK's most endangered butterfly\",\"webUrl\":\"https://www.theguardian.com/environment/2018/jul/02/dry-uk-weather-conditions-boosts-rare-butterflies\",\"apiUrl\":\"https://content.guardianapis.com/environment/2018/jul/02/dry-uk-weather-conditions-boosts-rare-butterflies\",\"tags\":[{\"id\":\"profile/stevenmorris\",\"type\":\"contributor\",\"webTitle\":\"Steven Morris\",\"webUrl\":\"https://www.theguardian.com/profile/stevenmorris\",\"apiUrl\":\"https://content.guardianapis.com/profile/stevenmorris\",\"references\":[],\"bio\":\"<p>Steven Morris is a reporter for the <a href=\\\"http://www.guardian.co.uk/\\\">Guardian</a></p>\",\"bylineImageUrl\":\"https://uploads.guim.co.uk/2017/12/27/Steven-Morris.jpg\",\"bylineLargeImageUrl\":\"https://uploads.guim.co.uk/2017/12/27/Steven_Morris,_L.png\",\"firstName\":\"Steven\",\"lastName\":\"Morris\",\"twitterHandle\":\"stevenmorris20\"}],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"uk-news/2018/jun/10/after-hottest-may-on-record-fine-weather-will-last-until-autumn\",\"type\":\"article\",\"sectionId\":\"uk-news\",\"sectionName\":\"UK news\",\"webPublicationDate\":\"2018-06-11T06:23:31Z\",\"webTitle\":\"UK weather: forecasters predict long hot summer\",\"webUrl\":\"https://www.theguardian.com/uk-news/2018/jun/10/after-hottest-may-on-record-fine-weather-will-last-until-autumn\",\"apiUrl\":\"https://content.guardianapis.com/uk-news/2018/jun/10/after-hottest-may-on-record-fine-weather-will-last-until-autumn\",\"tags\":[],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"environment/2018/mar/23/beastly-weather-and-climate-catastrophe\",\"type\":\"article\",\"sectionId\":\"environment\",\"sectionName\":\"Environment\",\"webPublicationDate\":\"2018-03-23T16:34:27Z\",\"webTitle\":\"Beastly weather and climate catastrophe | Letters\",\"webUrl\":\"https://www.theguardian.com/environment/2018/mar/23/beastly-weather-and-climate-catastrophe\",\"apiUrl\":\"https://content.guardianapis.com/environment/2018/mar/23/beastly-weather-and-climate-catastrophe\",\"tags\":[],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"environment/2014/jan/13/weather-view-photos-readers-around-world\",\"type\":\"article\",\"sectionId\":\"environment\",\"sectionName\":\"Environment\",\"webPublicationDate\":\"2018-05-02T07:13:06Z\",\"webTitle\":\"Weather view: share your photos of this week's weather around the world\",\"webUrl\":\"https://www.theguardian.com/environment/2014/jan/13/weather-view-photos-readers-around-world\",\"apiUrl\":\"https://content.guardianapis.com/environment/2014/jan/13/weather-view-photos-readers-around-world\",\"tags\":[{\"id\":\"profile/guardian-readers\",\"type\":\"contributor\",\"webTitle\":\"Guardian readers\",\"webUrl\":\"https://www.theguardian.com/profile/guardian-readers\",\"apiUrl\":\"https://content.guardianapis.com/profile/guardian-readers\",\"references\":[],\"bio\":\"<p>The Guardian readers contributor tag is applied to any content that is solely or partly created by you, our readers. It includes projects, galleries and stories involving data, photography, perspectives and more. Thank you for your ongoing inspiration and participation</p>\",\"firstName\":\"readers\",\"lastName\":\"guardian\"}],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"business/2018/jul/04/world-cup-and-hot-weather-boost-supermarkets-food-sales\",\"type\":\"article\",\"sectionId\":\"business\",\"sectionName\":\"Business\",\"webPublicationDate\":\"2018-07-04T15:59:55Z\",\"webTitle\":\"World Cup and hot weather bolster supermarkets sales\",\"webUrl\":\"https://www.theguardian.com/business/2018/jul/04/world-cup-and-hot-weather-boost-supermarkets-food-sales\",\"apiUrl\":\"https://content.guardianapis.com/business/2018/jul/04/world-cup-and-hot-weather-boost-supermarkets-food-sales\",\"tags\":[{\"id\":\"profile/rebeccasmithers\",\"type\":\"contributor\",\"webTitle\":\"Rebecca Smithers\",\"webUrl\":\"https://www.theguardian.com/profile/rebeccasmithers\",\"apiUrl\":\"https://content.guardianapis.com/profile/rebeccasmithers\",\"references\":[],\"bio\":\"<p>Rebecca Smithers is consumer affairs correspondent for the <a href=\\\"http://www.guardian.co.uk/money/consumer-affairs\\\">Guardian</a></p>\",\"bylineImageUrl\":\"https://uploads.guim.co.uk/2017/12/11/Rebecca-Smithers.jpg\",\"bylineLargeImageUrl\":\"https://uploads.guim.co.uk/2017/12/11/Rebecca_Smithers,_R.png\",\"firstName\":\"smithers\",\"lastName\":\"\"}],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"business/2018/jul/12/dfs-blames-hot-weather-for-surprise-profit-warning\",\"type\":\"article\",\"sectionId\":\"business\",\"sectionName\":\"Business\",\"webPublicationDate\":\"2018-07-12T09:26:47Z\",\"webTitle\":\"DFS and Dunelm blame hot weather for shock profit downgrades\",\"webUrl\":\"https://www.theguardian.com/business/2018/jul/12/dfs-blames-hot-weather-for-surprise-profit-warning\",\"apiUrl\":\"https://content.guardianapis.com/business/2018/jul/12/dfs-blames-hot-weather-for-surprise-profit-warning\",\"tags\":[{\"id\":\"profile/juliakollewe\",\"type\":\"contributor\",\"webTitle\":\"Julia Kollewe\",\"webUrl\":\"https://www.theguardian.com/profile/juliakollewe\",\"apiUrl\":\"https://content.guardianapis.com/profile/juliakollewe\",\"references\":[],\"bio\":\"<p>Julia Kollewe writes about pharmaceuticals, property and insurance for the Guardian and the Observer, and often covers breaking City news as well. She previously worked at the Independent, Bloomberg News and Market News International</p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/contributor/2014/10/15/1413385261859/Julia-Kollewe.jpg\",\"bylineLargeImageUrl\":\"https://uploads.guim.co.uk/2017/10/06/Julia-Kollewe,-L.png\",\"firstName\":\"kollewe\",\"lastName\":\"julia\"}],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"uk-news/2018/jul/11/weatherwatch-weather-gods-confounded-sceptics-in-summer-2013\",\"type\":\"article\",\"sectionId\":\"uk-news\",\"sectionName\":\"UK news\",\"webPublicationDate\":\"2018-07-11T20:30:21Z\",\"webTitle\":\"Weatherwatch: weather gods confounded sceptics in summer 2013\",\"webUrl\":\"https://www.theguardian.com/uk-news/2018/jul/11/weatherwatch-weather-gods-confounded-sceptics-in-summer-2013\",\"apiUrl\":\"https://content.guardianapis.com/uk-news/2018/jul/11/weatherwatch-weather-gods-confounded-sceptics-in-summer-2013\",\"tags\":[{\"id\":\"profile/stephenmoss1\",\"type\":\"contributor\",\"webTitle\":\"Stephen Moss\",\"webUrl\":\"https://www.theguardian.com/profile/stephenmoss1\",\"apiUrl\":\"https://content.guardianapis.com/profile/stephenmoss1\",\"references\":[],\"bio\":\"<p>Stephen Moss is a naturalist, writer and broadcaster, who also lectures in nature and travel writing at Bath Spa University. His latest book is <a href=\\\"https://www.guardianbookshop.com/mrs-moreau-s-warbler.html\\\">Mrs Moreau's Warbler: How Birds Got Their&nbsp;Names</a>.<br></p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2012/3/29/1333031077761/Stephen_Moss2.jpg\",\"firstName\":\"Stephen\",\"lastName\":\"Moss\",\"twitterHandle\":\"stephenmoss_tv\"}],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"uk-news/2018/jul/01/ten-top-facts-about-uk-summer-weather-heatwave\",\"type\":\"article\",\"sectionId\":\"uk-news\",\"sectionName\":\"UK news\",\"webPublicationDate\":\"2018-07-01T05:00:36Z\",\"webTitle\":\"10 top facts about the UK’s hot summer weather\",\"webUrl\":\"https://www.theguardian.com/uk-news/2018/jul/01/ten-top-facts-about-uk-summer-weather-heatwave\",\"apiUrl\":\"https://content.guardianapis.com/uk-news/2018/jul/01/ten-top-facts-about-uk-summer-weather-heatwave\",\"tags\":[{\"id\":\"profile/jamiedoward\",\"type\":\"contributor\",\"webTitle\":\"Jamie Doward\",\"webUrl\":\"https://www.theguardian.com/profile/jamiedoward\",\"apiUrl\":\"https://content.guardianapis.com/profile/jamiedoward\",\"references\":[],\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Observer/Columnist/Columnists/2012/7/7/1341661609598/jamie-doward-003.jpg\",\"firstName\":\"doward\",\"lastName\":\"\"}],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"}]}}";

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
                String section = currentResult.getString("sectionName");

                // To find/retrieve the attributes within the currentResult JSONObject, we call the
                // method getString to extract the primitive value associated with the key "webPublicationDate"
                String dateOfWebPublication = currentResult.getString("webPublicationDate");

                // To find/retrieve the attributes within the currentResult JSONObject, we call the
                // method getString to extract the primitive value associated with the key "newsTitle"
                String newsTitle = currentResult.getString("webTitle");

                // To find/retrieve the attributes within the currentResult JSONObject, we call the
                // method getString to extract the primitive value associated with the key "webUrl"
                String newsUrl = currentResult.getString("webUrl");

                // Find the array nested within the response JSONObject with key "tags" and store it
                // in a variable called "tagsArray".
                JSONArray tagsArray = currentResult.getJSONArray("tags");

                try {
                    // Parse tagsArray to try to extract the news author's name which is not always
                    // specified
                    JSONObject contributor = tagsArray.getJSONObject(0);

                    String authorsName = contributor.getString("webTitle");

                    // Create a News object instance
                    News newsItem = new News(section, dateOfWebPublication, authorsName, newsTitle, newsUrl);

                    // Add this newsItem to the list of News
                    newsFeed.add(newsItem);

                } catch (JSONException e) {

                    // If an error is thrown when trying to extract the author's name in the try block,
                    // catch the exception here, so the app doesn't crash.
                    // Print a log message with the message from the exception
                    Log.e(LOG_TAG, "Problem parsing the newsFeed JSON response, no author is provided for this piece of news", e);

                    // Create a News object instance
                    News newsItem = new News(section, dateOfWebPublication, newsTitle, newsUrl);

                    // Add this newsItem to the list of News
                    newsFeed.add(newsItem);
                }
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
