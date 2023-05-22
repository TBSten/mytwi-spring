package me.tbsten.mytwi.tweet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import me.tbsten.mytwi.auth.AuthApiController;
import me.tbsten.mytwi.auth.model.User;
import me.tbsten.mytwi.tweet.model.NewTweet;
import me.tbsten.mytwi.tweet.model.Timeline;
import me.tbsten.mytwi.tweet.model.Tweet;

@RestController
@RequestMapping("/api")
public class TweetApiController {
	@Autowired
	private TweetService tweetService;

	@GetMapping("/tweets")
	public List<Tweet> getTweets() {
		return tweetService.getTweets();
	}

	@GetMapping("/tweets/timeline")
	public Timeline getTimelineContents() {
		return tweetService.getTimeline();
	}

	@GetMapping("/tweet/{id}")
	public Tweet getTweet(
			@PathVariable("id") int id) {
		return tweetService.getTweetById(id);
	}

	@PostMapping(value = "/tweets", consumes = { "application/json" })
	public Tweet createNewTweet(
			@RequestBody NewTweet input,
			HttpServletRequest request) {
		User sessionUser = AuthApiController.getSessionUser(request);
		Tweet newTweet = tweetService.createTweet(input, sessionUser);
		return newTweet;
	}

}
