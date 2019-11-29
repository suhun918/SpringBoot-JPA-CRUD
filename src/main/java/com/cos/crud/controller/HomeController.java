package com.cos.crud.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.crud.model.User;
import com.cos.crud.utils.Script;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public @ResponseBody String home(HttpSession session) {
		
		User user = (User)session.getAttribute("user");
		if(user==null) {
			return "index not login";
		}else {
			return Script.href("/board/list");
		}
		
	}
}
