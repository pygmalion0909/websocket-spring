package kr.co.trgtech.trg01.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.trgtech.trg01.dto.TalkDto;

@Mapper
public interface TalkRepository {
	public List<TalkDto> findBy(String channelId);
	public void insert(TalkDto talkDto);
	public void deleteByChannelId(String channelId);
}
