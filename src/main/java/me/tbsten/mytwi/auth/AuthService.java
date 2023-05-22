package me.tbsten.mytwi.auth;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.tbsten.mytwi.auth.model.LoginInput;
import me.tbsten.mytwi.auth.model.PublicUserInfo;
import me.tbsten.mytwi.auth.model.SignUpInput;
import me.tbsten.mytwi.auth.model.User;

@Service
public class AuthService {
	@Autowired
	private UserRepository userRepository;

	public User signUp(SignUpInput input) {
		if(userRepository.existsById(input.getId())) {
			throw new RuntimeException("there are same id user");
		}
		return userRepository.save(new User(
				input.getId(), 
				input.getUserName(), 
				input.getPassword()
			));
	}

	public User login(LoginInput input) {
		// 指定したIDで見つからなかったとき
		Optional<User> user = userRepository.findById(input.getId());
		System.out.println(user.get());
		if(user.isEmpty()) {
			System.out.println("invalid id "+input.getId());
			return null;
		}
		// パスワードが違うとき
		if(!Objects.equals(user.get().getPassword(), input.getPassword())) {
			System.out.println("invalid pass "+input.getPassword());
			return null;
		}
		return user.get();
	}

	public void logout(User sessionUser) { }
	
	public User getUser(String userId) {	
		var user = userRepository.findById(userId);
		return user.get();
	}
	
	public PublicUserInfo getPublicUserInfo(String userId) {
		return getUser(userId).toPublicUserInfo();
	}
}

