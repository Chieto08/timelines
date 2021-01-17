package com.project.timelines.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.project.timelines.Models.Followers;
import com.project.timelines.Models.Tweets;
import com.project.timelines.Models.User;

public class ProcessFiles {
    
    private BufferedReader bufferedReader;

    private void scanFiles(String[] filepaths, String[] types) {
        String line = "";

        for (int i = 0; i < filepaths.length; i++) {
            try {
                try {
                    bufferedReader = new BufferedReader(new FileReader(filepaths[i]));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                while ((line = bufferedReader.readLine()) != null) {
                    String[] values = line.split(",");
                    populateModels(types[i], values);
                }
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void populateModels(String type, String[] values) {
        if (type == "follows") {
            Followers follower = new Followers(values[0], values[1]);
            Followers.follows.add(follower);
        } else if (type == "tweets") {
            Date date = new Date(Long.parseLong(values[2]) * 1000L);
            Tweets tweet = new Tweets(values[0], values[1], "N/A", date, values[3]);
            Tweets.tweets.add(tweet);
        } else if (type == "users") {
            User user = new User(values[0], values[1], values[2], new ArrayList<Tweets>(Arrays.asList()));
            User.users.add(user);
        }
    }

    public void readFiles(String parentPath) {
        System.out.println("Reading data files and populating models");
    
        String[] filepaths = { parentPath.concat("/follows.csv"), parentPath.concat("/tweets.csv"),
        parentPath.concat("/users.csv") };
        String[] types = { "follows", "tweets", "users" };

        scanFiles(filepaths, types);

        System.out.println("Finished reading files and preparing data");
    }
}
