package kr.co.trgtech.trg01.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.trgtech.trg01.dto.ChannelDto;
import kr.co.trgtech.trg01.dto.TalkDto;
import kr.co.trgtech.trg01.service.ChannelService;
import kr.co.trgtech.trg01.service.TalkService;

@RestController
public class ApiController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ChannelService channelService;
	
	@PostMapping(value="/channel/checkPasswd")
	public String channelCheckPasswd(ChannelDto channelDto) {
		logger.debug("channel checkPasswd begin - [{}]", channelDto);
		return channelService.checkPasswd(channelDto);
	}
	
}
