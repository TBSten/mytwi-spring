package me.tbsten.mytwi.tweet.model;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import me.tbsten.mytwi.auth.model.PublicUserInfo;

@Getter
@ToString
@AllArgsConstructor
public class Timeline {
	private List<Tweet> tweets;
	private Map<String, PublicUserInfo> users;
}
