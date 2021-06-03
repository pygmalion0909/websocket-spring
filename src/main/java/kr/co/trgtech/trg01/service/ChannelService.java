package kr.co.trgtech.trg01.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.trgtech.trg01.dto.ChannelDto;
import kr.co.trgtech.trg01.dto.UserDto;
import kr.co.trgtech.trg01.repository.ChannelRepository;
import kr.co.trgtech.trg01.repository.TalkRepository;
import kr.co.trgtech.trg01.repository.UserRepository;

@Service
public class ChannelService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ChannelRepository channelRepository;
	@Autowired
	private TalkRepository talkRepositoy;
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * @title 채널조회
	 */
	public List<ChannelDto> selectChannelList(String loginId){
		UserDto userInfo = userRepository.findByLoginId(loginId);
		List<ChannelDto> channelList = channelRepository.findBy();
		for(ChannelDto item : channelList) {
			if(item.getUserId().equals(userInfo.getId())) {
				item.setWriter("YES");
			}else {
				item.setWriter("NO");
			}
		}
		logger.debug("==sp/channelList==[{}]", channelList);
		return channelList;
	}
	
	/**
	 * @title 채널생성
	 */
	public void insertChannel(ChannelDto channelDto, String loginId) {
		if(channelDto.getPasswd().trim().equals("")) channelDto.setPasswd(null);
		channelDto.setUserId(userRepository.findByLoginId(loginId).getId());
		channelRepository.insert(channelDto);
	}
	
	/**
	 * @title 채널수정
	 */
	public void updateChannel(ChannelDto channelDto) {
		if(channelDto.getPasswd().trim().equals("")) channelDto.setPasswd(null);
		channelRepository.update(channelDto);
	}
	
	/**
	 * @title 채널삭제
	 */
	public void deleteChannel(String channelId) {
		//대화삭제
		talkRepositoy.deleteByChannelId(channelId);
		//채널삭제
		channelRepository.deleteByChannelId(channelId);
	}
	
	/**
	 * @title 채널비번체크
	 */
	public String checkPasswd(ChannelDto channelDto) {
		logger.debug("===sp/checkPasswd===" + channelDto);
		String result = "NO";
		if(channelRepository.findByPasswd(channelDto) > 0) return result = "YES";
		return result;
	}
	
}
