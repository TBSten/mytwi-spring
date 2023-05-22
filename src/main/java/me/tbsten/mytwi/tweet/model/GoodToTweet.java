package me.tbsten.mytwi.tweet.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Table(name = "good_to_tweet")
@IdClass(GoodToTweet.PK.class)
@Getter
@AllArgsConstructor
public class GoodToTweet {

	@Id
	@Column
	private int tweetId;

	@Id
	@Column
	private String userId;

	@Embeddable
	class PK implements Serializable {
		@Column
		private int tweetId;
		@Column
		private String userId;
	}

}
