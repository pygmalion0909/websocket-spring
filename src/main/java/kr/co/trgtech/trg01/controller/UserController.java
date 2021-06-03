package kr.co.trgtech.trg01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.trgtech.trg01.config.websocket.SocketHandler;
import kr.co.trgtech.trg01.dto.UserDto;
import kr.co.trgtech.trg01.service.UserService;

@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * @title login page
	 * @date 2020-11-18 
	 */
	@RequestMapping(value="/loginPage")
	public String loginPage() {
		return "loginPage";
	}
	
	/**
	 * @title signup page
	 * @date 2020-11-18 
	 */
	@RequestMapping(value="/signupPage")
	public String signupPage() {
		return "signupPage";
	}
	
	/**
	 * @title signup 등록
	 * @date 2020-11-18 
	 */
	@RequestMapping(value="/signup")
	public String signup(UserDto userDto) {
		logger.debug("===sp/userDto===" + userDto);
		userService.signup(userDto);
		return "loginPage";
	}
	
}
