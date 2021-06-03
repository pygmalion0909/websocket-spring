package kr.co.trgtech.trg01.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.trgtech.trg01.dto.TalkDto;
import kr.co.trgtech.trg01.dto.UserDto;
import kr.co.trgtech.trg01.repository.TalkRepository;
import kr.co.trgtech.trg01.repository.UserRepository;

@Service
public class TalkService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TalkRepository talkRepository;
	
	/**
	 * @title 대화조회
	 */
	public List<TalkDto> talkList(String channelId){
		return talkRepository.findBy(channelId);
	}
	
	/**
	 * @title 대화저장
	 */
	public void insertTalk(TalkDto talkDto){
		talkRepository.insert(talkDto);
	}
	
}
