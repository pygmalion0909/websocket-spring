package kr.co.trgtech.trg01.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.trgtech.trg01.dto.ChannelDto;

@Mapper
public interface ChannelRepository {
	public List<ChannelDto> findBy();
	public ChannelDto findById(String id);
	public void insert(ChannelDto channelDto);
	public void update(ChannelDto channelDto);
	public void deleteByChannelId(String channelId);
	public int findByPasswd(ChannelDto channelDto);
}
