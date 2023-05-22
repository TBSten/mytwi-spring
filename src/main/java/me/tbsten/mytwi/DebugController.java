package me.tbsten.mytwi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DebugController {

	@GetMapping("/debug")
	public String test() {
		return "/debug";
	}

}
