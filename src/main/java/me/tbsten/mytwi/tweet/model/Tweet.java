package me.tbsten.mytwi.tweet.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "tweet")
public class Tweet {
	@Id
	@GeneratedValue
	private int id;
	@Column(length = 1000)
	private String content;
	@Column
	private String authorId;
	@Column
	private Date createAt; 
}
