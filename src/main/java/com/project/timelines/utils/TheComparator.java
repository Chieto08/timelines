package com.project.timelines.utils;

import java.util.Comparator;

import com.project.timelines.Models.Tweets;


public class TheComparator implements Comparator<Tweets>{

    @Override
    public int compare(Tweets tweet1, Tweets tweet2) {
        return tweet1.getTimestamp().compareTo(tweet2.getTimestamp());
    }
}