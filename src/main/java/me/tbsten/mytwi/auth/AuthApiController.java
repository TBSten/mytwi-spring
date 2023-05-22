package me.tbsten.mytwi.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import me.tbsten.mytwi.auth.model.LoginInput;
import me.tbsten.mytwi.auth.model.PublicUserInfo;
import me.tbsten.mytwi.auth.model.SignUpInput;
import me.tbsten.mytwi.auth.model.User;

@RestController
@RequestMapping("/api")
public class AuthApiController {
	@Autowired
	private AuthService authService;

	@PostMapping(value = "/signup", consumes = { "application/json" })
	public Object signup(
			@RequestBody SignUpInput input,
			HttpServletRequest request
		) {
		User user = authService.signUp(input);
		if(user == null) {
			return new AuthApiResponse("invalid signup input");
		}
		setSessionUser(request, user);
		return user;
	}

	@PostMapping(value = "/login", consumes = { "application/json" })
	public Object login(
			@RequestBody LoginInput input,
			HttpServletRequest request) {
		System.out.println(input);
		User loginUser = authService.login(input);
		System.out.println(loginUser);
		if (loginUser == null) {
			return new AuthApiResponse("failure");
		}
		
		setSessionUser(request, loginUser);
		return loginUser;
	}

	@PostMapping(value = "/logout")
	public AuthApiResponse logout(
			HttpServletRequest request) {
		User sessionUser = AuthApiController.getSessionUser(request);
		authService.logout(sessionUser);
		request.getSession().invalidate();
		return new AuthApiResponse("ok");
	}

	@GetMapping(value = "/user/session")
	public Object getUser(
			HttpServletRequest request) {
		User user = getSessionUser(request);
		if(user == null) {
			return new AuthApiResponse("none session");
		}
		return user;
	}
	
	@GetMapping(value = "/user/{id}")
	public PublicUserInfo getPublicUserInfo(
			@PathVariable("id") String id
		) {
		return authService.getPublicUserInfo(id);
	}
	
	@AllArgsConstructor
	@Getter
	private class AuthApiResponse {
		private String msg;
	}
	
	public static User getSessionUser(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		return user;	
	}
	public static void setSessionUser(HttpServletRequest request, User user) {
		request.getSession().setAttribute("user", user);
	}
}


