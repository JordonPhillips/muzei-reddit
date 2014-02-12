package com.jordonphillips.muzeireddit;

import com.jordonphillips.muzeireddit.gson.Listing;

import java.util.Map;

import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.QueryMap;

/**
 * Defines the interface for getting content from a subreddit in json form.
 * Created by Jordon on 2/12/14.
 */
public interface RedditService {
    @Headers({
            "User-Agent: Muzei-Reddit"
    })
    @GET("/r/{subreddit}/{sort}/.json")
    Listing getListing(
            @Path("subreddit") String subreddit,
            @Path("sort") String sort,
            @QueryMap Map<String, String> options
    );
}
