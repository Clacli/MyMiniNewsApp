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

    private static final String SAMPLE_JSON_RESPONSE = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":91766,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":9177,\"orderBy\":\"relevance\",\"results\":[{\"id\":\"books/2018/jul/13/best-recent-science-fiction-review-roundup\",\"type\":\"article\",\"sectionId\":\"books\",\"sectionName\":\"Books\",\"webPublicationDate\":\"2018-07-13T10:59:08Z\",\"webTitle\":\"The best recent science fiction – reviews roundup\",\"webUrl\":\"https://www.theguardian.com/books/2018/jul/13/best-recent-science-fiction-review-roundup\",\"apiUrl\":\"https://content.guardianapis.com/books/2018/jul/13/best-recent-science-fiction-review-roundup\",\"tags\":[{\"id\":\"profile/eric-brown\",\"type\":\"contributor\",\"webTitle\":\"Eric Brown\",\"webUrl\":\"https://www.theguardian.com/profile/eric-brown\",\"apiUrl\":\"https://content.guardianapis.com/profile/eric-brown\",\"references\":[],\"firstName\":\"brown\",\"lastName\":\"eric\"}],\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"},{\"id\":\"science/2018/jul/07/why-science-is-breeding-ground-for-sexism\",\"type\":\"article\",\"sectionId\":\"science\",\"sectionName\":\"Science\",\"webPublicationDate\":\"2018-07-07T14:00:02Z\",\"webTitle\":\"Why science breeds a culture of sexism\",\"webUrl\":\"https://www.theguardian.com/science/2018/jul/07/why-science-is-breeding-ground-for-sexism\",\"apiUrl\":\"https://content.guardianapis.com/science/2018/jul/07/why-science-is-breeding-ground-for-sexism\",\"tags\":[{\"id\":\"profile/davidbatty\",\"type\":\"contributor\",\"webTitle\":\"David Batty\",\"webUrl\":\"https://www.theguardian.com/profile/davidbatty\",\"apiUrl\":\"https://content.guardianapis.com/profile/davidbatty\",\"references\":[],\"bio\":\"<p>David Batty is a news editor and writer. His specialist areas are the super rich, cultural politics, visual art, higher education and social affairs. He has worked at the Guardian since 2001, and also lectures in journalism.</p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/contributor/2007/10/01/david_batty_140x140.jpg\",\"firstName\":\"batty\",\"lastName\":\"\"},{\"id\":\"profile/nicola-davis\",\"type\":\"contributor\",\"webTitle\":\"Nicola Davis\",\"webUrl\":\"https://www.theguardian.com/profile/nicola-davis\",\"apiUrl\":\"https://content.guardianapis.com/profile/nicola-davis\",\"references\":[],\"bio\":\"<p>Nicola Davis writes about science, health and environment for the Guardian and Observer and was commissioning editor for Observer Tech Monthly. Previously she worked for the Times and other publications. She has a MChem and  DPhil in Organic Chemistry from the University of Oxford. Nicola also presents the&nbsp;<a href=\\\"https://www.theguardian.com/science/series/science\\\">Science Weekly podcast</a>.</p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/About/General/2013/8/30/1377879747391/Dr-Nicola-Davis-004.jpg\",\"firstName\":\"davis\",\"lastName\":\"nicola\",\"twitterHandle\":\"NicolaKSDavis\"}],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"science/political-science/2018/jun/29/elsevier-are-corrupting-open-science-in-europe\",\"type\":\"article\",\"sectionId\":\"science\",\"sectionName\":\"Science\",\"webPublicationDate\":\"2018-06-29T15:00:45Z\",\"webTitle\":\"Elsevier are corrupting open science in Europe\",\"webUrl\":\"https://www.theguardian.com/science/political-science/2018/jun/29/elsevier-are-corrupting-open-science-in-europe\",\"apiUrl\":\"https://content.guardianapis.com/science/political-science/2018/jun/29/elsevier-are-corrupting-open-science-in-europe\",\"tags\":[],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"guardian-masterclasses/2018/jun/25/how-to-communicate-your-science-creatively-and-effectively-bespoke-masterclasses-lucy-maddox-laura-knight\",\"type\":\"article\",\"sectionId\":\"guardian-masterclasses\",\"sectionName\":\"Guardian Masterclasses\",\"webPublicationDate\":\"2018-06-25T13:43:50Z\",\"webTitle\":\"How to communicate science creatively and effectively | Lucy Maddox and Laura Knight\",\"webUrl\":\"https://www.theguardian.com/guardian-masterclasses/2018/jun/25/how-to-communicate-your-science-creatively-and-effectively-bespoke-masterclasses-lucy-maddox-laura-knight\",\"apiUrl\":\"https://content.guardianapis.com/guardian-masterclasses/2018/jun/25/how-to-communicate-your-science-creatively-and-effectively-bespoke-masterclasses-lucy-maddox-laura-knight\",\"tags\":[],\"isHosted\":false},{\"id\":\"books/2018/jun/08/the-best-recent-science-fiction-eric-brown\",\"type\":\"article\",\"sectionId\":\"books\",\"sectionName\":\"Books\",\"webPublicationDate\":\"2018-06-08T10:00:10Z\",\"webTitle\":\"The best recent science fiction – reviews roundup\",\"webUrl\":\"https://www.theguardian.com/books/2018/jun/08/the-best-recent-science-fiction-eric-brown\",\"apiUrl\":\"https://content.guardianapis.com/books/2018/jun/08/the-best-recent-science-fiction-eric-brown\",\"tags\":[{\"id\":\"profile/eric-brown\",\"type\":\"contributor\",\"webTitle\":\"Eric Brown\",\"webUrl\":\"https://www.theguardian.com/profile/eric-brown\",\"apiUrl\":\"https://content.guardianapis.com/profile/eric-brown\",\"references\":[],\"firstName\":\"brown\",\"lastName\":\"eric\"}],\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"},{\"id\":\"books/2018/may/11/the-best-recent-science-fiction-eric-brown\",\"type\":\"article\",\"sectionId\":\"books\",\"sectionName\":\"Books\",\"webPublicationDate\":\"2018-05-11T11:00:28Z\",\"webTitle\":\"The best recent science fiction – reviews roundup\",\"webUrl\":\"https://www.theguardian.com/books/2018/may/11/the-best-recent-science-fiction-eric-brown\",\"apiUrl\":\"https://content.guardianapis.com/books/2018/may/11/the-best-recent-science-fiction-eric-brown\",\"tags\":[{\"id\":\"profile/eric-brown\",\"type\":\"contributor\",\"webTitle\":\"Eric Brown\",\"webUrl\":\"https://www.theguardian.com/profile/eric-brown\",\"apiUrl\":\"https://content.guardianapis.com/profile/eric-brown\",\"references\":[],\"firstName\":\"brown\",\"lastName\":\"eric\"}],\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"},{\"id\":\"science/political-science/2018/mar/16/who-benefits-from-biomedical-science\",\"type\":\"article\",\"sectionId\":\"science\",\"sectionName\":\"Science\",\"webPublicationDate\":\"2018-03-16T16:30:14Z\",\"webTitle\":\"Who benefits from biomedical science?\",\"webUrl\":\"https://www.theguardian.com/science/political-science/2018/mar/16/who-benefits-from-biomedical-science\",\"apiUrl\":\"https://content.guardianapis.com/science/political-science/2018/mar/16/who-benefits-from-biomedical-science\",\"tags\":[{\"id\":\"profile/jack-stilgoe\",\"type\":\"contributor\",\"webTitle\":\"Jack Stilgoe\",\"webUrl\":\"https://www.theguardian.com/profile/jack-stilgoe\",\"apiUrl\":\"https://content.guardianapis.com/profile/jack-stilgoe\",\"references\":[],\"bio\":\"<p>Jack Stilgoe is a senior lecturer in the department of <a href=\\\"http://www.ucl.ac.uk/sts\\\" title=\\\"\\\">Science and Technology Studies at University College London</a>. On Twitter he is <a href=\\\"https://twitter.com/Jackstilgoe\\\">@JackStilgoe</a></p>\",\"firstName\":\"stilgoe\",\"lastName\":\"jack\",\"twitterHandle\":\"JackStilgoe\"}],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"uk-news/2018/may/08/met-launches-urgent-review-into-suspected-forensics-blunders\",\"type\":\"article\",\"sectionId\":\"uk-news\",\"sectionName\":\"UK news\",\"webPublicationDate\":\"2018-05-08T14:53:30Z\",\"webTitle\":\"Met urgently reviews suspected forensic science blunders\",\"webUrl\":\"https://www.theguardian.com/uk-news/2018/may/08/met-launches-urgent-review-into-suspected-forensics-blunders\",\"apiUrl\":\"https://content.guardianapis.com/uk-news/2018/may/08/met-launches-urgent-review-into-suspected-forensics-blunders\",\"tags\":[{\"id\":\"profile/hannah-devlin\",\"type\":\"contributor\",\"webTitle\":\"Hannah Devlin\",\"webUrl\":\"https://www.theguardian.com/profile/hannah-devlin\",\"apiUrl\":\"https://content.guardianapis.com/profile/hannah-devlin\",\"references\":[],\"bio\":\"<p>Hannah Devlin is the Guardian's science correspondent, having previously been science editor of the Times. She has a PhD in biomedical imaging from the University of Oxford. Hannah also presents the <a href=\\\"https://www.theguardian.com/science/series/science\\\">Science Weekly podcast</a>.</p>\",\"bylineImageUrl\":\"https://uploads.guim.co.uk/2018/04/12/Hannah-Devlin.jpg\",\"bylineLargeImageUrl\":\"https://uploads.guim.co.uk/2018/04/12/Hannah_Devlin,_L.png\",\"firstName\":\"Hannah\",\"lastName\":\"Devlin\",\"twitterHandle\":\"hannahdev\"},{\"id\":\"profile/vikramdodd\",\"type\":\"contributor\",\"webTitle\":\"Vikram Dodd\",\"webUrl\":\"https://www.theguardian.com/profile/vikramdodd\",\"apiUrl\":\"https://content.guardianapis.com/profile/vikramdodd\",\"references\":[],\"bio\":\"<p>Vikram Dodd is a <a href=\\\"http://www.guardian.co.uk/uk/ukcrime\\\">crime correspondent</a></p>\",\"bylineImageUrl\":\"https://uploads.guim.co.uk/2017/12/26/Vikram-Dodd.jpg\",\"bylineLargeImageUrl\":\"https://uploads.guim.co.uk/2017/12/26/Vikram_Dodd,_L.png\",\"firstName\":\"Vikram\",\"lastName\":\"Dodd\"}],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"commentisfree/2018/may/07/calling-dietary-guidelines-wrong-ignores-the-science\",\"type\":\"article\",\"sectionId\":\"commentisfree\",\"sectionName\":\"Opinion\",\"webPublicationDate\":\"2018-05-07T03:23:19Z\",\"webTitle\":\"Calling dietary guidelines ‘wrong’ ignores the science | Gideon Meyerowitz-Katz\",\"webUrl\":\"https://www.theguardian.com/commentisfree/2018/may/07/calling-dietary-guidelines-wrong-ignores-the-science\",\"apiUrl\":\"https://content.guardianapis.com/commentisfree/2018/may/07/calling-dietary-guidelines-wrong-ignores-the-science\",\"tags\":[{\"id\":\"profile/gideon-meyerowitz-katz\",\"type\":\"contributor\",\"webTitle\":\"Gideon Meyerowitz-Katz\",\"webUrl\":\"https://www.theguardian.com/profile/gideon-meyerowitz-katz\",\"apiUrl\":\"https://content.guardianapis.com/profile/gideon-meyerowitz-katz\",\"references\":[],\"bio\":\"<p>Gideon Meyerowitz-Katz is an epidemiologist working in chronic disease in Sydney’s west, with a particular focus on the social determinants that control our health. He writes a regular health blog covering science communication, public health, and what that new study you've read about actually means. You can find his blog at&nbsp;<a href=\\\"http://medium.com/@gidmk\\\">medium.com/@gidmk</a><br><br></p>\",\"firstName\":\"Gideon\",\"lastName\":\"Meyerowitz-Katz\",\"twitterHandle\":\"gidmk\"}],\"isHosted\":false,\"pillarId\":\"pillar/opinion\",\"pillarName\":\"Opinion\"},{\"id\":\"science/brain-flapping/2018/apr/19/forensic-science-the-tip-of-the-iceberg\",\"type\":\"article\",\"sectionId\":\"science\",\"sectionName\":\"Science\",\"webPublicationDate\":\"2018-04-19T10:48:40Z\",\"webTitle\":\"Forensic science: the tip of the iceberg?\",\"webUrl\":\"https://www.theguardian.com/science/brain-flapping/2018/apr/19/forensic-science-the-tip-of-the-iceberg\",\"apiUrl\":\"https://content.guardianapis.com/science/brain-flapping/2018/apr/19/forensic-science-the-tip-of-the-iceberg\",\"tags\":[],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"}]}}";

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
