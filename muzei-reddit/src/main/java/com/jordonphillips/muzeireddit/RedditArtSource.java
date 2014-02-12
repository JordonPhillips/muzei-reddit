package com.jordonphillips.muzeireddit;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.google.android.apps.muzei.api.Artwork;
import com.google.android.apps.muzei.api.MuzeiArtSource;
import com.jordonphillips.muzeireddit.gson.SubmissionData;

import retrofit.RestAdapter;

/**
 * Created by Jordon on 2/12/14.
 */
public class RedditArtSource extends MuzeiArtSource {
    private static final String SOURCE_NAME = "MuzeiReddit";
    private static final String REDDIT_URL = "http://www.reddit.com/";

    public RedditArtSource() {
        super(SOURCE_NAME);
    }

    protected void onUpdate(int reason) {
        SubmissionData data = getTop();
        publishArtwork(new Artwork.Builder()
                .imageUri(Uri.parse(data.url))
                .title(data.title)
                .byline(data.author)
                .viewIntent(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(REDDIT_URL+data.permalink)))
                .build());
    }

    private SubmissionData getTop() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(REDDIT_URL)
                .build();
        RedditService service = restAdapter.create(RedditService.class);
        SubmissionData data = service.getListing("EarthPorn","top",null).data.children.get(0).data;
        return data;
    }
}
