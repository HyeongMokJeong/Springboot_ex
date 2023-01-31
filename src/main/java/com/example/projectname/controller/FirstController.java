package com.example.projectname.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

	@GetMapping("/hi")
	public String niceToMeetYou(Model model) {
		model.addAttribute("username", "pipi");
		return "greet"; // template/greet.mustache 파일을 찾아서 브라우저로 전송
	}
	
	@GetMapping("/bye")
	public String goodBye(Model model) {
		model.addAttribute("username", "popo");
		return "bye";
	}

}
