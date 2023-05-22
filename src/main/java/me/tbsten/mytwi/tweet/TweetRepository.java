package me.tbsten.mytwi.tweet;

import org.springframework.data.jpa.repository.JpaRepository;

import me.tbsten.mytwi.tweet.model.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Integer> {
}
