package me.tbsten.mytwi.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class SignUpInput {
	private String id;
	private String userName;
	private String password;
}
