package com.project.timelines.Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Tweets {

    private String _id;
    private String _authorId;
    private String _authorName;
    private Date _timestamp;
    private String _text;
    public static List<Tweets> tweets = new ArrayList<Tweets>(Arrays.asList());

    public Tweets(String id, String authorId, String authorName, Date timestamp, String text) {
        _id = id;
        _authorId = authorId;
        _authorName = authorName;
        _timestamp = timestamp;
        _text = text;
    }

    public String getId() {
        return _id;
    }

    public String getAuthorId() {
        return _authorId;
    }

    public String getAuthorName() {
        return _authorName;
    }

    public Date getTimestamp() {
        return _timestamp;
    }

    public String getText() {
        return _text;
    }

    public void setAuthorName(String name){
        _authorName = name;
    }

    @Override
    public String toString() {
        return "{ Id: " + _id + "\n" + 
        " AuthorId: " + _authorId + "\n" + 
        " AuthorName: " + _authorName + "\n" + 
        " Timestamp: " + _timestamp + "\n" + 
        " Text: " + _text + " }";
    }
}
