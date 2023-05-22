package me.tbsten.mytwi.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class LoginInput{
	private String id;
	private String password;
}
