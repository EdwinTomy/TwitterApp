package com.codepath.apps.restclienttemplate;

import android.content.Context;

import com.codepath.apps.restclienttemplate.models.Tweet;

import org.json.JSONArray;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
class ExampleUnitTest {


    @Test
    public void profileTest() {
        Tweet tweet = new Tweet();
        tweet.user.profileImageUrl = "https://scontent.xx.fbcdn.net/v/t1.0-9/103588571_115463130194384_5648095564038403189_n.jpg?_nc_cat=102&_nc_sid=85a577&_nc_eui2=AeHsm8e7p5LJafOT-r3HYkmkrOA8qeeC7Yqs4Dyp54LtipwPFlpQANTnqrFx-gkWb-tSI1QRXHSEfm_b6G1dMM8E&_nc_ohc=eiq6TTTp_EUAX_aVv-0&_nc_ht=scontent.xx&_nc_rmd=260&_nc_log=1&oh=55192f5450094bdabf9d3c17075a9015&oe=5F2559B9";

        TweetsAdapter tweetsAdapter = new TweetsAdapter(this);
        assertNotNull(tweetsAdapter.tweets.get(0).imgurl);
    }

    @Test
    public void bodyTest() {
        Tweet tweet = new Tweet();
        tweet.body = "Hello!";

        TweetsAdapter tweetsAdapter = new TweetsAdapter(this);
        assertEquals("Hello!", tweetsAdapter.tweets.get(0).body);
    }
}