package kr.co.trgtech.trg01.dto;

import org.apache.ibatis.type.Alias;

@Alias("UserDto")
public class UserDto{
	private String id;
	private String loginId;
	private String passwd;
	private String createDate;
	private String modifyDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
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
		return "UserDto [id=" + id + ", loginId=" + loginId + ", passwd=" + passwd + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + "]";
	}
}
