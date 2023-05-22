package me.tbsten.mytwi.tweet;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.val;
import me.tbsten.mytwi.auth.AuthService;
import me.tbsten.mytwi.auth.model.PublicUserInfo;
import me.tbsten.mytwi.auth.model.User;
import me.tbsten.mytwi.tweet.model.NewTweet;
import me.tbsten.mytwi.tweet.model.Timeline;
import me.tbsten.mytwi.tweet.model.Tweet;

@Service
public class TweetService {

	@Autowired
	private TweetRepository tweetRepository;
	@Autowired
	private AuthService authService;
	
	public Tweet getTweetById(int id) {
		return tweetRepository.getReferenceById(id);
	}
	
	public List<Tweet> getTweets() {
		return tweetRepository.findAll(Sort.by(Sort.Direction.DESC, "createAt"));
	}
	
	public Tweet createTweet(NewTweet input, User author) {
		Tweet tweet = new Tweet(
				0, 
				input.getContent(), 
				author.getId(),
				new Date()
			);
		val newTweet = tweetRepository.save(tweet);
		return newTweet;
	}
	
	public Timeline getTimeline() {
		List<Tweet> tweets = getTweets();
		var users = new HashMap<String, PublicUserInfo>();
		for(Tweet tweet:tweets) {
			users.put(tweet.getAuthorId(), authService.getPublicUserInfo(tweet.getAuthorId()));
		}
		return new Timeline(tweets, users);
	}
	
}
