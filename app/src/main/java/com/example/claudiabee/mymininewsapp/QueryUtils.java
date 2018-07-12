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

    private static final String SAMPLE_JSON_RESPONSE = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":102,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":11,\"orderBy\":\"relevance\",\"results\":[{\"id\":\"music/2017/apr/27/readers-recommend-playlist-your-songs-about-mathematics\",\"type\":\"article\",\"sectionId\":\"music\",\"sectionName\":\"Music\",\"webPublicationDate\":\"2017-04-27T11:00:04Z\",\"webTitle\":\"Readers recommend playlist: your songs about mathematics\",\"webUrl\":\"https://www.theguardian.com/music/2017/apr/27/readers-recommend-playlist-your-songs-about-mathematics\",\"apiUrl\":\"https://content.guardianapis.com/music/2017/apr/27/readers-recommend-playlist-your-songs-about-mathematics\",\"tags\":[{\"id\":\"profile/sarah-chappell\",\"type\":\"contributor\",\"webTitle\":\"Sarah Chappell\",\"webUrl\":\"https://www.theguardian.com/profile/sarah-chappell\",\"apiUrl\":\"https://content.guardianapis.com/profile/sarah-chappell\",\"references\":[],\"bio\":\"<p>Long-time lurker and relatively recent new contributor to the Readers Recommend blog, Sarah has also written about books since 2012 (in between running, swimming, organising a busy family and working as an HR Manager) and posts here as&nbsp;<a href=\\\"https://profile.theguardian.com/user/id/10102877\\\">AFictionHabit</a>. You can follow her on Twitter&nbsp;<a href=\\\"https://twitter.com/afictionhabit\\\">@afictionhabit</a></p>\",\"firstName\":\"sarah\",\"lastName\":\"chappell\",\"twitterHandle\":\"afictionhabit\"}],\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"},{\"id\":\"stage/2017/dec/04/spongebob-squarepants-the-broadway-musical-review-sugar-shock-visual-pleasure\",\"type\":\"article\",\"sectionId\":\"stage\",\"sectionName\":\"Stage\",\"webPublicationDate\":\"2017-12-05T03:00:04Z\",\"webTitle\":\"SpongeBob Squarepants: The Broadway Musical review – sugar-shock visual pleasure\",\"webUrl\":\"https://www.theguardian.com/stage/2017/dec/04/spongebob-squarepants-the-broadway-musical-review-sugar-shock-visual-pleasure\",\"apiUrl\":\"https://content.guardianapis.com/stage/2017/dec/04/spongebob-squarepants-the-broadway-musical-review-sugar-shock-visual-pleasure\",\"tags\":[{\"id\":\"profile/alexis-soloski\",\"type\":\"contributor\",\"webTitle\":\"Alexis Soloski\",\"webUrl\":\"https://www.theguardian.com/profile/alexis-soloski\",\"apiUrl\":\"https://content.guardianapis.com/profile/alexis-soloski\",\"references\":[],\"bio\":\"<p>Alexis Soloski theatre critic for the Guardian</p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2014/4/23/1398261619256/soloski.jpg\",\"firstName\":\"Alexis\",\"lastName\":\"Soloski\",\"twitterHandle\":\"ASoloski\"}],\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"},{\"id\":\"tv-and-radio/2018/feb/19/everything-sucks-nostalgia-tv-gilmore-girls-full-house-netflix-friends-mad-men\",\"type\":\"article\",\"sectionId\":\"tv-and-radio\",\"sectionName\":\"Television & radio\",\"webPublicationDate\":\"2018-02-19T17:58:14Z\",\"webTitle\":\"Nostalgia sucks! Why TV needs to stop living in the past\",\"webUrl\":\"https://www.theguardian.com/tv-and-radio/2018/feb/19/everything-sucks-nostalgia-tv-gilmore-girls-full-house-netflix-friends-mad-men\",\"apiUrl\":\"https://content.guardianapis.com/tv-and-radio/2018/feb/19/everything-sucks-nostalgia-tv-gilmore-girls-full-house-netflix-friends-mad-men\",\"tags\":[{\"id\":\"profile/lara-williams\",\"type\":\"contributor\",\"webTitle\":\"Lara Williams\",\"webUrl\":\"https://www.theguardian.com/profile/lara-williams\",\"apiUrl\":\"https://content.guardianapis.com/profile/lara-williams\",\"references\":[],\"bio\":\"<p>Lara Williams is an author and freelance writer based in Manchester. Her&nbsp;<a href=\\\"http://waterstones.com/book/treats/lara-williams/9781910449707\\\">debut book Treats</a>&nbsp;was published in 2016 and more of her writing can be found&nbsp;<a href=\\\"http://larawilliamswriter.com/\\\">here</a></p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2010/5/10/1273489199163/lara.jpg\",\"firstName\":\"williams\",\"lastName\":\"lara\"}],\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"},{\"id\":\"film/2017/dec/08/anthony-harvey-obituary\",\"type\":\"article\",\"sectionId\":\"film\",\"sectionName\":\"Film\",\"webPublicationDate\":\"2017-12-08T10:57:06Z\",\"webTitle\":\"Anthony Harvey obituary\",\"webUrl\":\"https://www.theguardian.com/film/2017/dec/08/anthony-harvey-obituary\",\"apiUrl\":\"https://content.guardianapis.com/film/2017/dec/08/anthony-harvey-obituary\",\"tags\":[{\"id\":\"profile/ronaldbergan\",\"type\":\"contributor\",\"webTitle\":\"Ronald Bergan\",\"webUrl\":\"https://www.theguardian.com/profile/ronaldbergan\",\"apiUrl\":\"https://content.guardianapis.com/profile/ronaldbergan\",\"references\":[],\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2008/11/10/ronald_bergan_140x140.jpg\",\"firstName\":\"bergan\",\"lastName\":\"\"}],\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"},{\"id\":\"music/2017/sep/15/open-mike-eagle-rap-chicago-social-housing\",\"type\":\"article\",\"sectionId\":\"music\",\"sectionName\":\"Music\",\"webPublicationDate\":\"2017-09-15T16:59:58Z\",\"webTitle\":\"Can rap shine a light on America's social housing crisis?\",\"webUrl\":\"https://www.theguardian.com/music/2017/sep/15/open-mike-eagle-rap-chicago-social-housing\",\"apiUrl\":\"https://content.guardianapis.com/music/2017/sep/15/open-mike-eagle-rap-chicago-social-housing\",\"tags\":[{\"id\":\"profile/christina-lee\",\"type\":\"contributor\",\"webTitle\":\"Christina Lee\",\"webUrl\":\"https://www.theguardian.com/profile/christina-lee\",\"apiUrl\":\"https://content.guardianapis.com/profile/christina-lee\",\"references\":[],\"bio\":\"<p>Christina Lee is an Atlanta-based music journalist whose work has appeared in RollingStone.com, MTV Networks, Spin and Gawker Media.</p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2015/5/5/1430845575073/Christina-Lee.jpg\",\"firstName\":\"lee\",\"lastName\":\"christina\",\"twitterHandle\":\"MinaAnnLee\"}],\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"},{\"id\":\"stage/2018/may/01/british-theatre-stars-storm-the-nominations-for-2018-tony-awards\",\"type\":\"article\",\"sectionId\":\"stage\",\"sectionName\":\"Stage\",\"webPublicationDate\":\"2018-05-01T13:58:17Z\",\"webTitle\":\"British theatre stars storm the nominations for 2018 Tony awards\",\"webUrl\":\"https://www.theguardian.com/stage/2018/may/01/british-theatre-stars-storm-the-nominations-for-2018-tony-awards\",\"apiUrl\":\"https://content.guardianapis.com/stage/2018/may/01/british-theatre-stars-storm-the-nominations-for-2018-tony-awards\",\"tags\":[{\"id\":\"profile/chriswiegand\",\"type\":\"contributor\",\"webTitle\":\"Chris Wiegand\",\"webUrl\":\"https://www.theguardian.com/profile/chriswiegand\",\"apiUrl\":\"https://content.guardianapis.com/profile/chriswiegand\",\"references\":[],\"bio\":\"<p>Chris Wiegand is the Stage editor of the Guardian</p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/contributor/2015/9/25/1443177725299/Chris-Wiegand.jpg\",\"bylineLargeImageUrl\":\"https://uploads.guim.co.uk/2017/10/06/Chris-Wiegand,-L.png\",\"firstName\":\"wiegand\",\"lastName\":\"\",\"twitterHandle\":\"ChrisWiegand\"}],\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"},{\"id\":\"music/2017/mar/23/readers-recommend-playlist-knowledge\",\"type\":\"article\",\"sectionId\":\"music\",\"sectionName\":\"Music\",\"webPublicationDate\":\"2017-03-23T12:00:03Z\",\"webTitle\":\"Readers recommend playlist: your songs about knowledge\",\"webUrl\":\"https://www.theguardian.com/music/2017/mar/23/readers-recommend-playlist-knowledge\",\"apiUrl\":\"https://content.guardianapis.com/music/2017/mar/23/readers-recommend-playlist-knowledge\",\"tags\":[{\"id\":\"profile/george-boyland\",\"type\":\"contributor\",\"webTitle\":\"George Boyland\",\"webUrl\":\"https://www.theguardian.com/profile/george-boyland\",\"apiUrl\":\"https://content.guardianapis.com/profile/george-boyland\",\"references\":[],\"bio\":\"<p>George Boyland s a regular contributor to the Readers Recommend music blog.</p>\",\"firstName\":\"boyland\",\"lastName\":\"george\"}],\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"},{\"id\":\"tv-and-radio/2016/jan/05/tuesdays-best-tv-our-school-victorian-bakers-you-make-me-feel-like-dancing-celebrity-big-brother-live-launch\",\"type\":\"article\",\"sectionId\":\"tv-and-radio\",\"sectionName\":\"Television & radio\",\"webPublicationDate\":\"2016-01-05T06:05:01Z\",\"webTitle\":\"Tuesday’s best TV: Our School; Victorian Bakers; You Make Me Feel Like Dancing; Celebrity Big Brother: Live Launch\",\"webUrl\":\"https://www.theguardian.com/tv-and-radio/2016/jan/05/tuesdays-best-tv-our-school-victorian-bakers-you-make-me-feel-like-dancing-celebrity-big-brother-live-launch\",\"apiUrl\":\"https://content.guardianapis.com/tv-and-radio/2016/jan/05/tuesdays-best-tv-our-school-victorian-bakers-you-make-me-feel-like-dancing-celebrity-big-brother-live-launch\",\"tags\":[{\"id\":\"profile/jack-seale\",\"type\":\"contributor\",\"webTitle\":\"Jack Seale\",\"webUrl\":\"https://www.theguardian.com/profile/jack-seale\",\"apiUrl\":\"https://content.guardianapis.com/profile/jack-seale\",\"references\":[],\"bio\":\"<p>Jack Seale is a freelance journalist, specialising in TV. He writes regularly for the Guardian and Radio Times. <a href=\\\"http://Jackseale.com\\\" title=\\\"\\\">Jackseale.com</a></p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2015/1/27/1422361457417/seale.jpeg\",\"firstName\":\"seale\",\"lastName\":\"jack\"},{\"id\":\"profile/mark-gibbings-jones\",\"type\":\"contributor\",\"sectionId\":\"tv-and-radio\",\"sectionName\":\"Television & radio\",\"webTitle\":\"Mark Gibbings-Jones\",\"webUrl\":\"https://www.theguardian.com/profile/mark-gibbings-jones\",\"apiUrl\":\"https://content.guardianapis.com/profile/mark-gibbings-jones\",\"references\":[],\"bio\":\"<p>TV reviewer</p>\",\"firstName\":\"gibbings-jones\",\"lastName\":\"mark\"},{\"id\":\"profile/alicatterall\",\"type\":\"contributor\",\"webTitle\":\"Ali Catterall\",\"webUrl\":\"https://www.theguardian.com/profile/alicatterall\",\"apiUrl\":\"https://content.guardianapis.com/profile/alicatterall\",\"references\":[],\"bio\":\"<p>Ali Catterall is a staff writer for Film4.com and writes Movie Rush, Film4's movie reviews show. He has also worked for Channel 4, the BBC and The Guardian, and is the co-author of Your Face Here: British Cult Movies Since the Sixties.</p>\",\"firstName\":\"catterall\",\"lastName\":\"ali\"},{\"id\":\"profile/andrewmueller\",\"type\":\"contributor\",\"webTitle\":\"Andrew Mueller\",\"webUrl\":\"https://www.theguardian.com/profile/andrewmueller\",\"apiUrl\":\"https://content.guardianapis.com/profile/andrewmueller\",\"references\":[],\"bio\":\"<p>Andrew Mueller is a journalist and author. His current book, I Wouldn't<br/>Start From Here: the 21st Century and Where it all Went Wrong is available now through Portobello. His website is <a href=\\\"http://www.andrewmueller.net\\\">andrewmueller.net</a></p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2008/06/04/Andrew_Mueller_140x140.jpg\",\"firstName\":\"mueller\",\"lastName\":\"andrew\"},{\"id\":\"profile/hannah-verdier\",\"type\":\"contributor\",\"webTitle\":\"Hannah Verdier\",\"webUrl\":\"https://www.theguardian.com/profile/hannah-verdier\",\"apiUrl\":\"https://content.guardianapis.com/profile/hannah-verdier\",\"references\":[],\"firstName\":\"verdier\",\"lastName\":\"hannah\"},{\"id\":\"profile/graeme-virtue\",\"type\":\"contributor\",\"webTitle\":\"Graeme Virtue\",\"webUrl\":\"https://www.theguardian.com/profile/graeme-virtue\",\"apiUrl\":\"https://content.guardianapis.com/profile/graeme-virtue\",\"references\":[],\"bio\":\"<p>Graeme Virtue is a writer and broadcaster based in Glasgow. You can follow him on Twitter at <a href=\\\"https://twitter.com/graemevirtue\\\">@graemevirtue</a></p>\",\"firstName\":\"virtue\",\"lastName\":\"graeme\"},{\"id\":\"profile/paulhowlett\",\"type\":\"contributor\",\"webTitle\":\"Paul Howlett\",\"webUrl\":\"https://www.theguardian.com/profile/paulhowlett\",\"apiUrl\":\"https://content.guardianapis.com/profile/paulhowlett\",\"references\":[],\"bio\":\"<p>Paul Howlett is production editor for <a href=\\\"http://www.guardian.co.uk/theguardian/g2\\\">G2</a></p>\",\"firstName\":\"howlett\",\"lastName\":\"\"}],\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"},{\"id\":\"culture/2016/feb/27/saturday-morning-cereal-bowl-podcast-palatable-kids-music\",\"type\":\"article\",\"sectionId\":\"culture\",\"sectionName\":\"Culture\",\"webPublicationDate\":\"2016-02-27T12:45:06Z\",\"webTitle\":\"Saturday Morning Cereal Bowl: the podcast for palatable kids' music\",\"webUrl\":\"https://www.theguardian.com/culture/2016/feb/27/saturday-morning-cereal-bowl-podcast-palatable-kids-music\",\"apiUrl\":\"https://content.guardianapis.com/culture/2016/feb/27/saturday-morning-cereal-bowl-podcast-palatable-kids-music\",\"tags\":[{\"id\":\"profile/melissa-locker\",\"type\":\"contributor\",\"webTitle\":\"Melissa Locker\",\"webUrl\":\"https://www.theguardian.com/profile/melissa-locker\",\"apiUrl\":\"https://content.guardianapis.com/profile/melissa-locker\",\"references\":[],\"firstName\":\"locker\",\"lastName\":\"melissa\"}],\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"},{\"id\":\"film/2017/sep/17/steve-buscemi-not-fulfilled-true-potential-interview-death-of-stalin-electric-dreams\",\"type\":\"article\",\"sectionId\":\"film\",\"sectionName\":\"Film\",\"webPublicationDate\":\"2017-09-17T07:00:34Z\",\"webTitle\":\"Steve Buscemi: ‘In some ways I feel I haven’t fulfilled my true potential'\",\"webUrl\":\"https://www.theguardian.com/film/2017/sep/17/steve-buscemi-not-fulfilled-true-potential-interview-death-of-stalin-electric-dreams\",\"apiUrl\":\"https://content.guardianapis.com/film/2017/sep/17/steve-buscemi-not-fulfilled-true-potential-interview-death-of-stalin-electric-dreams\",\"tags\":[{\"id\":\"profile/aaron-hicklin\",\"type\":\"contributor\",\"webTitle\":\"Aaron Hicklin\",\"webUrl\":\"https://www.theguardian.com/profile/aaron-hicklin\",\"apiUrl\":\"https://content.guardianapis.com/profile/aaron-hicklin\",\"references\":[],\"bio\":\"<p>Aaron Hicklin joined Out magazine as editor in chief in 2006. He is based in New York</p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2008/11/5/1225898309473/aaron.jpg\",\"firstName\":\"hicklin\",\"lastName\":\"\"}],\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"}]}}";

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
                String webPublicationDate = currentResult.getString("webPublicationDate");

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
                    News newsItem = new News(section, webPublicationDate, authorsName, newsTitle, newsUrl);

                    // Add this newsItem to the list of News
                    newsFeed.add(newsItem);

                } catch (JSONException e) {

                    // If an error is thrown when trying to extract the author's name in the try block,
                    // catch the exception here, so the app doesn't crash.
                    // Print a log message with the message from the exception
                    Log.e(LOG_TAG, "Problem parsing the newsFeed JSON response, no author is provided for this piece of news", e);

                    // Create a News object instance
                    News newsItem = new News(section, webPublicationDate, newsTitle, newsUrl);

                    // Add this newsItem to the list of News
                    newsFeed.add(newsItem);
                }
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the abobe statements in the try block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception
            Log.e(LOG_TAG, "Problem parsing the newsFeed JSON response", e);
        }

        // return the list of news
        return newsFeed;
    }
}
