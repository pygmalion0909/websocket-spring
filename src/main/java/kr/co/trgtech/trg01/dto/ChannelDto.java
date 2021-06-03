package kr.co.trgtech.trg01.dto;

import org.apache.ibatis.type.Alias;

@Alias("ChannelDto")
public class ChannelDto {
	private String id;
	private String userId;
	private String title;
	private String writer;
	private String passwd;
	private String createDate;
	private String modifyDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
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
		return "ChannelDto [id=" + id + ", userId=" + userId + ", title=" + title + ", writer=" + writer + ", passwd="
				+ passwd + ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
	}
}
