package com.project.timelines.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.project.timelines.Models.Followers;
import com.project.timelines.Models.Tweets;
import com.project.timelines.Models.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProcessFilesTest {

    @Test
    public void testAllFileProcesses() {
        ProcessFiles processFiles = new ProcessFiles();
        String parentPath = "./src/test/java/com/project/timelines/utils/Data";
        int lenFollows = 6;
        int lenTweets = 9;
        int lenUsers = 3;

        processFiles.readFiles(parentPath);

        assertEquals(lenFollows, Followers.follows.size());
        assertEquals(lenTweets, Tweets.tweets.size());
        assertEquals(lenUsers, User.users.size());
    }
}
