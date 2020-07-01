package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class ComposeActivity extends AppCompatActivity {

    public static final int MAX_TWEET_LENGTH = 140;
    public static final String TAG = "Compose Actvity";

    EditText etCompose;
    Button btnTweet;
    TwitterClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        etCompose = findViewById(R.id.etCompose);
        btnTweet = findViewById(R.id.btnTweet);
        client = TwitterApp.getRestClient(this);

        //Set quick listener on button
        btnTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tweetContent = etCompose.getText().toString();
                if(tweetContent.isEmpty()){
                    Toast.makeText(ComposeActivity.this, "Sorry, the tweet is empty!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(tweetContent.length() > MAX_TWEET_LENGTH){
                    Toast.makeText(ComposeActivity.this, "Sorry, the tweet is too long!", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(ComposeActivity.this, tweetContent, Toast.LENGTH_SHORT).show();

                //Make API call to publish tweet
                client.publishTweet(tweetContent, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Headers headers, JSON json) {
                        Log.i(TAG, "succeed to publish");
                        try {
                            Tweet tweet = Tweet.fromJson(json.jsonObject);
                            Log.i(TAG, "published tweet:" + tweet.body);

                            //Returning tweet to RV
                            Intent intent = new Intent();
                            intent.putExtra("tweet", Parcels.wrap(tweet));
                            setResult(RESULT_OK, intent);
                            finish();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                        Log.e(TAG, "failed to publish", throwable);
                    }
                });
            }
        });
    }
}