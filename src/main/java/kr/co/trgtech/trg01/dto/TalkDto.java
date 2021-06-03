package kr.co.trgtech.trg01.dto;

import org.apache.ibatis.type.Alias;

@Alias("TalkDto")
public class TalkDto {
	private String id;
	private String channelId;
	private String userId;
	private String content;
	private String loginId;
	private String createDate;
	private String modifyDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	@Override
	public String toString() {
		return "TalkDto [id=" + id + ", channelId=" + channelId + ", userId=" + userId + ", content=" + content
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
	}
}
