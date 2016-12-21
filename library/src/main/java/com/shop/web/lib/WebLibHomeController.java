package com.shop.web.lib;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/lib")
class WebLibHomeController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String home(Lib lib, BindingResult result) {
		return "lib";
	}
}
