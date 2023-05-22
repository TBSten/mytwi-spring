package me.tbsten.mytwi.auth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity(name = "user")
public class User {
	@Id
	private String id;
	@Column
	private String name;
	@Column
	private String password;
	
	public PublicUserInfo toPublicUserInfo() {
		return new PublicUserInfo(
				this.id, 
				this.name
			);
	}

}
