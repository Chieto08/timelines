package com.project.timelines;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.project.timelines.Models.Tweets;
import com.project.timelines.Models.User;
import com.project.timelines.utils.Utils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TimelinesApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TimelinesApplication.class, args);
		runApplication();
	}

	private static void runApplication() throws Exception {
		Utils utils = new Utils();
		utils.readAndPopulateModels("./src/main/java/com/project/timelines/utils/Data");
		Scanner scanner = new Scanner(System.in);
		String input;

		System.out.println("Enter c to end program.");
		System.out.println("Enter the user id and a timeline length separated by a space. E.g: 989489610 5");

		try {
			while (!(input = scanner.nextLine()).equals("c")) {
				if (input.length() > 0) {
					String[] inputs = input.split(" ");
					String userId = inputs[0];
					int timelineLength = 0;

					try {
						timelineLength = Integer.parseInt(inputs[1]);
					} catch (NumberFormatException exception) {
						System.out.println("The timeline length is not a number, please enter the correct details. \n");
					}

					User user = utils.getUserTweets(userId, timelineLength);
					List<Tweets> userTweets = user.getTweets();

					if (userTweets.size() > 0) {
						System.out.println("\nUsername is: " + user.getFullname());
						System.out.println("Handle is: @" + user.getHandle());
						System.out.println("-------------------------------------------------------------");
						for (Tweets tweet : userTweets) {
							System.out.println("Author Name: " + tweet.getAuthorName() + "\n" + "Time: "
									+ new SimpleDateFormat().format(tweet.getTimestamp()) + "\n" + "Tweet: "
									+ tweet.getText());
							System.out.println();
						}
					}
				}
				System.out.println("Enter c to end program.");
				System.out.println("Enter the user id and a timeline length separated by a space. E.g: 989489610 5");
			}
			{
				System.out.println("Requested Program end");
				scanner.close();
				System.exit(1);
			}
		} catch (NoSuchElementException e) {
			System.out.println("Invalid input");
		}
	}

}
