package dias_plbtw.com.asikinaja;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import dias_plbtw.com.asikinaja.Fragments.AboutFragment;
import dias_plbtw.com.asikinaja.Fragments.BrowseFragment;
import dias_plbtw.com.asikinaja.Fragments.ConnectFragment;
import dias_plbtw.com.asikinaja.TwitterAPI.InitAPITwitter;
import retrofit.Response;

public class ShareTwitterActivity extends AppCompatActivity {

    private TwitterLoginButton twitterButton;

    //navigation drawer
    private Drawer result = null;
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_twitter);

        setNavigationBar();

        setUpViews();
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
                        getResources().getString(R.string.app_name),
                        Toast.LENGTH_SHORT).show();

                setUpViewsForTweetComposer();
            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(getApplicationContext(),
                        getResources().getString(R.string.app_name),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpViewsForTweetComposer() {
        String a = "";
        TweetComposer.Builder builder = new TweetComposer.Builder(this)
                .text("I use this App, You can downlaod it in Play Store ( https://play.google.com/store/apps )");
        builder.show();
    }

    public void setNavigationBar()
    {
        final android.app.FragmentManager fragmentManager = getFragmentManager();

        if(result!=null)
            result.removeAllItems();

        // Create the AccountHeader
        result = new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("").withIdentifier(1),
                        new PrimaryDrawerItem().withName("").withIdentifier(2),
                        new SectionDrawerItem().withName("Options"),
                        new PrimaryDrawerItem().withName("Browse").withIcon(FontAwesome.Icon.faw_cog).withIdentifier(3),
                        new PrimaryDrawerItem().withName("Connect Us").withIcon(FontAwesome.Icon.faw_cog).withIdentifier(4),
                        new PrimaryDrawerItem().withName("About").withIcon(FontAwesome.Icon.faw_cog).withIdentifier(5),
                        new PrimaryDrawerItem().withName("Share Twitter").withIcon(FontAwesome.Icon.faw_cog).withIdentifier(6),
                        new PrimaryDrawerItem().withName("Exit").withIcon(FontAwesome.Icon.faw_cog).withIdentifier(7)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null) {
                            Intent intent = null;
                            if (drawerItem.getIdentifier() == 3) {
                                fragmentManager.beginTransaction().replace(R.id.framelay, new BrowseFragment()).commit();
                            } else if (drawerItem.getIdentifier() == 4) {
                                fragmentManager.beginTransaction().replace(R.id.framelay, new ConnectFragment()).commit();
                            } else if (drawerItem.getIdentifier() == 5) {
                                fragmentManager.beginTransaction().replace(R.id.framelay, new AboutFragment()).commit();
                            } else if (drawerItem.getIdentifier() == 6) {
                                fragmentManager.beginTransaction().replace(R.id.framelay, new InitAPITwitter()).commit();
                            } else if (drawerItem.getIdentifier() == 7) {
                                System.exit(0);
                            }
                            if (intent != null) {

                            }
                        }
                        return false;
                    }
                })
                .withSelectedItem(-1)
                .withSavedInstance(savedInstanceState)
                .build();
    }
}
