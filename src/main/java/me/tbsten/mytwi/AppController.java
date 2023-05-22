package me.tbsten.mytwi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

	@GetMapping("{path:^(?!.*static|api).*$}/**")
	public String all() {
		return "index";
	}

}
