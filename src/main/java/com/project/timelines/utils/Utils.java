package com.project.timelines.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import com.project.timelines.Models.Followers;
import com.project.timelines.Models.User;
import com.project.timelines.Models.Tweets;

public class Utils {

    protected TreeSet<Tweets> tweetsTreeSet;
    protected Map<String, List<String>> mapOfFollowers;

    private Map<String, List<String>> mapFollowers() {
        mapOfFollowers = new HashMap<>();

        for (Followers follower : Followers.follows) {
            if (!mapOfFollowers.containsKey(follower.getSourceId())) {
                List<String> destIds = new ArrayList<String>(Arrays.asList());
                destIds.add(follower.getDestinationId());
                mapOfFollowers.put(follower.getSourceId(), destIds);

            } else {
                mapOfFollowers.get(follower.getSourceId()).add(follower.getDestinationId());
            }
        }

        return mapOfFollowers;
    }

    private void prepareFollowersAndTweets() {
        mapOfFollowers = mapFollowers();
        tweetsTreeSet = new TreeSet<Tweets>(new TheComparator()); // Search and sort is done in log N
        tweetsTreeSet.addAll(Tweets.tweets);

        System.out.println("Finished preparing tweets and Follows information.");
    }

    private List<Tweets> getTweetAuthorNames(List<Tweets> userTweets) {
        for (User user : User.users) {
            for (Tweets tweet : userTweets) {
                if (tweet.getAuthorId().equals(user.getId())) {
                    tweet.setAuthorName(user.getFullname());
                }
            }
        }

        return userTweets;
    }

    private User getTweets(String userId, int timelineLength) {

        try {
            List<Tweets> userTweets = new ArrayList<Tweets>(Arrays.asList());
            List<String> userFollowers = mapOfFollowers.get(userId);

            Iterator<Tweets> iterator = tweetsTreeSet.descendingIterator();
        
            while (iterator.hasNext()) {
                Tweets tweet = iterator.next();
                if (userTweets.size() < timelineLength && userFollowers.contains(tweet.getAuthorId())) {
                    userTweets.add(tweet);
                } else {
                    break;
                }
            }

            for (User user : User.users) {
                if (user.getId().equals(userId)) {

                    List<Tweets> userTweetsWithNames = getTweetAuthorNames(userTweets);
                    user = new User(userId, user.getHandle(), user.getFullname(), userTweetsWithNames);

                    return user;
                }
            }
        } catch (NullPointerException e) {

            System.out.println("User does not exist. \n");
        }

        return new User("N/A", "N/A", "N/A", new ArrayList<Tweets>(Arrays.asList()));

    }

    public void readAndPopulateModels(String parentPath){
        new ProcessFiles().readFiles(parentPath);
        prepareFollowersAndTweets();

        System.out.println("-------------------------------------------------------------");
    }

    
    public User getUserTweets(String userId, int timelineLength) {
        return getTweets(userId, timelineLength);
    }

}
