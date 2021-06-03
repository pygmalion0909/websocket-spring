package kr.co.trgtech.trg01.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.trgtech.trg01.dto.UserDto;
import kr.co.trgtech.trg01.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	/**
	 * @title Spring Security login 필수 메소드
	 * @throws UsernameNotFoundException 유저가 없을 때 예외 발생
	 */
	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		//user정보
		UserDto userInfo = userRepository.findByLoginId(loginId);
		//권한
		List<GrantedAuthority> authorities = new ArrayList<>();
		return new User(userInfo.getLoginId(), userInfo.getPasswd(), authorities);
	}
	
	/**
	 * @title signup
	 */
	public void signup(UserDto userDto) {
		userDto.setPasswd(passwordEncoder.encode(userDto.getPasswd()));
		userRepository.signup(userDto);
	}
	
}
