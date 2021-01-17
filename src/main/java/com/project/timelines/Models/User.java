package com.project.timelines.Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {

    private String _id;
    private String _handle;
    private String _fullName;
    private List<Tweets> _tweets;
    public static List<User> users = new ArrayList<User>(Arrays.asList());

	public User(
        String id,
        String handle,
        String fullName,
        List<Tweets> tweets
    ){
        _id = id;
        _handle = handle;
        _fullName = fullName;
        _tweets = tweets;
    }

    public String getId(){
        return _id;
    }

    public String getHandle(){
        return _handle;
    }

    public String getFullname(){
        return _fullName;
    }

    public List<Tweets> getTweets(){
        return _tweets;
    }


    @Override
    public String toString(){
        return "{Id: " + _id  + "\n" +
         " handle: " + _handle + "\n" + 
         " fullname: " + _fullName + "\n" +
         " tweets: " + _tweets.toString() + " }";
    }

}