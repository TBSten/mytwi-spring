package me.tbsten.mytwi.tweet.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class NewTweet {
	@NotBlank
	String content;
}
