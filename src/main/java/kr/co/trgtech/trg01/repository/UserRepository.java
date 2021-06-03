package kr.co.trgtech.trg01.repository;

import org.apache.ibatis.annotations.Mapper;

import kr.co.trgtech.trg01.dto.UserDto;

@Mapper
public interface UserRepository {
	//user단일조회
	public UserDto findByLoginId(String loginId);
	//signup
	public void signup(UserDto userDto);
}
