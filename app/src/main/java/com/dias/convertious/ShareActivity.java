package com.dias.convertious;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import dias_plbtw.com.convertme.R;
import retrofit.Response;

public class ShareActivity extends AppCompatActivity {

    private TwitterLoginButton twitterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Convertious");
        setSupportActionBar(toolbar);

        setUpViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.Back:
                finish();
                Intent myIntent = new Intent(this, NavigationActivity.class);
                startActivity(myIntent);
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        twitterButton.onActivityResult(requestCode, resultCode, data);
    }

    private void setUpViews() {
        setUpTwitterButton();
    }

    private void setUpTwitterButton() {
        twitterButton = (TwitterLoginButton) findViewById(R.id.twitter_button);
        twitterButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void onResponse(Response<TwitterSession> response) {

            }

            @Override
            public void onFailure(Throwable t) {

            }

            @Override
            public void success(Result<TwitterSession> result) {
                Toast.makeText(getApplicationContext(),
                        "Success",
                        Toast.LENGTH_SHORT).show();

                setUpViewsForTweetComposer();
            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(getApplicationContext(),
                        "Failed",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpViewsForTweetComposer() {
        String a = "";
        TweetComposer.Builder builder = new TweetComposer.Builder(this)
                .text("I use this App, You can downlaod it in Play Store ( https://play.google.com/store/apps/details?id=com.dias.convertious )");
        builder.show();
    }
}
