package com.framework.login;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {
	
	@GetMapping("/login-template")
	public ModelAndView loginTemplate() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login/login-template2");
		return mav;
	}
	
	@PostMapping("/request-login")
	@ResponseBody
	//public ResponseEntity
	public Map<String, Object> requestLogin(@RequestBody Map<String, Object> params){
		Map<String, Object> result = new HashMap<>();
		
		String userName = (String)params.get("userName");
		String userPassword = (String)params.get("userPassword");
		
		if("admin".equals(userName) && "1234".equals(userPassword)) {
			result.put("REPL_MSG", "성공");
			log.info("로그인 성공");
		}else {
			result.put("REPL_MSG", "실패");
			log.error("로그인 실패");
		}
		return result;
	}
}
