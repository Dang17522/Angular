package com.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping(value = {"/","","/admin"})
	public String home() {
		return "redirect:/PhoneStore";
	}
}
