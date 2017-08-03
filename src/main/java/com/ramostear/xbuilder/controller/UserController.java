package com.ramostear.xbuilder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ramostear.xbuilder.service.UserService;

@Controller
@RequestMapping("/admin/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/index")
	private String index() {
		return "";
	}
	
	
}
