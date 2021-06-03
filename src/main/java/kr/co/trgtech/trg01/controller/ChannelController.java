package kr.co.trgtech.trg01.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.trgtech.trg01.dto.ChannelDto;
import kr.co.trgtech.trg01.repository.ChannelRepository;
import kr.co.trgtech.trg01.repository.UserRepository;
import kr.co.trgtech.trg01.service.ChannelService;
import kr.co.trgtech.trg01.service.TalkService;

@Controller
public class ChannelController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ChannelService channelService;
	@Autowired
	private TalkService talkService;
	@Autowired
	private ChannelRepository channelRepository;
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/")
	public String rootUrlRedirect() {
		return "redirect:/channelPage";
	}
	
	@RequestMapping(value="/channelPage")
	public String channelPage(Model model, Principal principal) {
		logger.debug("channel channelPage begin -");
		model.addAttribute("list", channelService.selectChannelList(principal.getName()));
		return "channel/channelPage";
	}

	@RequestMapping(value="/channelPage/{channelId}")
	public String channelTalk(@PathVariable String channelId, Model model, Principal principal) {
		model.addAttribute("list", talkService.talkList(channelId));
		model.addAttribute("channelInfo", channelRepository.findById(channelId));
		model.addAttribute("userInfo", userRepository.findByLoginId(principal.getName()));
		return "channel/channelTalk";
	}
	
	@RequestMapping(value="/channelPage/create")
	public String channelCreate(ChannelDto channelDto, Model model) {
		logger.debug("channel create begin - [{}]", channelDto);
		if(channelDto.getId() != null && channelDto.getId() != "") model.addAttribute("info", channelDto);
		return "channel/channelCreate";
	}
	
	@RequestMapping(value="/channel/insert")
	public String insertChannel(ChannelDto channelDto, Principal principal) {
		logger.debug("channel insert begin -" + channelDto);
		channelService.insertChannel(channelDto, principal.getName());
		return "redirect:/channelPage";
	}
	
	@RequestMapping(value="/channel/update")
	public String updateChannel(ChannelDto channelDto) {
		logger.debug("channel update begin - [{}]", channelDto);
		channelService.updateChannel(channelDto);
		return "redirect:/channelPage";
	}
	
	@RequestMapping(value="/channel/delete/{channelId}")
	public String deleteChannel(@PathVariable String channelId) {
		logger.debug("channel delete begin - [{}]", channelId);
		channelService.deleteChannel(channelId);
		return "redirect:/channelPage";
	}
	
}
