package dias_plbtw.com.asikinaja.TwitterAPI;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import dias_plbtw.com.asikinaja.R;
import dias_plbtw.com.asikinaja.ShareTwitterActivity;
import io.fabric.sdk.android.Fabric;

/**
 * Created by Dias on 3/3/2017.
 */
public class InitAPITwitter extends Fragment {
    private static final String TWITTER_KEY = "WDuhNUL7cEztJEKuPnqfBDV2K";
    private static final String TWITTER_SECRET = "5vVNEtG2UvJBOgVbIZ6xBzb6sgwZefRMG7332JLPyeJVwGxqz4";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_browse, container, false);
        if (container == null) {
            return null;
        }

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(getActivity(), new Twitter(authConfig));

        Intent intent = new Intent(getActivity(), ShareTwitterActivity.class);
        getActivity().startActivity(intent);

        return view;
    }

}
