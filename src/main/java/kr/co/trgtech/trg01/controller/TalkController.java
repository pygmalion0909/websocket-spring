package kr.co.trgtech.trg01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.trgtech.trg01.dto.TalkDto;
import kr.co.trgtech.trg01.repository.ChannelRepository;
import kr.co.trgtech.trg01.service.TalkService;

@Controller
public class TalkController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TalkService talkService;
	@Autowired
	private ChannelRepository channelRepository;
	
}