package com.project.timelines.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.project.timelines.Models.Followers;
import com.project.timelines.Models.Tweets;
import com.project.timelines.Models.User;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class UtilsTest {


    @Test
    public void testReadAndPopulateModelsAndGetUserTweets() {
        Utils utils = new Utils();
        User.users= new ArrayList<User>(Arrays.asList());
        Tweets.tweets = new ArrayList<Tweets>(Arrays.asList());
        Followers.follows = new ArrayList<Followers>(Arrays.asList());

        String parentPath = "./src/test/java/com/project/timelines/utils/Data";
        utils.readAndPopulateModels(parentPath);

        List<Tweets> testTweets = new ArrayList<Tweets>();
        testTweets.addAll(Tweets.tweets);

        Collections.sort(testTweets, (tweet1, tweet2) -> tweet1.getTimestamp().compareTo(tweet2.getTimestamp()));
        
        Map<String, List<String>> mapOfFollowers = new HashMap<>();
        List<String> followers1 = new ArrayList<>();
        List<String> followers2 = new ArrayList<>();
        List<String> followers3 = new ArrayList<>();

        followers1.add("10224712");
        followers1.add("16407251");

        followers2.add("989489610");
        followers2.add("16407251");

        followers3.add("989489610");
        followers3.add("10224712");

        mapOfFollowers.put("989489610", followers1);
        mapOfFollowers.put("10224712", followers2);
        mapOfFollowers.put("16407251", followers3);


        assertEquals(mapOfFollowers, utils.mapOfFollowers);
        assertEquals(testTweets.toArray().getClass(), utils.tweetsTreeSet.toArray().getClass());
        Assert.assertArrayEquals(testTweets.toArray(), utils.tweetsTreeSet.toArray());
    }

    @Test
    public void getUserTweetsTest(){
        Utils utils = new Utils();
        User.users= new ArrayList<User>(Arrays.asList());
        Tweets.tweets = new ArrayList<Tweets>(Arrays.asList());
        Followers.follows = new ArrayList<Followers>(Arrays.asList());

        String parentPath = "./src/test/java/com/project/timelines/utils/Data";
        utils.readAndPopulateModels(parentPath);

        Date date = new Date(Long.parseLong("1442275529") * 1000L);
        List<Tweets> tweet = new ArrayList<>();
        tweet.add(
            new Tweets("643576332438388737", "989489610", "Evan Jones", date,
            "@marcua oooo I want to hear/read the story! These are my kind of bugs!"));


        User u1 = new User("989489610", "epcjones","Evan Jones", new ArrayList<>());
        User u2 = new User("10224712", "pcalcado", "Phil Calcado", tweet);
        User u3 = new User("16407251", "richminer", "Rich Miner", tweet);

        User user1 = utils.getUserTweets("989489610", 1);
        User user2 = utils.getUserTweets("10224712", 1);
        User user3 = utils.getUserTweets("16407251", 1);

        assertEquals(u1.toString(), user1.toString());
        assertEquals(u2.toString(), user2.toString());
        assertEquals(u3.toString(), user3.toString());
    }
}
