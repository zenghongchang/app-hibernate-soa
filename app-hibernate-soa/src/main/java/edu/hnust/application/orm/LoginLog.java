package edu.hnust.application.orm;

import java.io.Serializable;

public class LoginLog implements Serializable {
	private static final long serialVersionUID = -1263884122482645835L;
	private Integer id;
    private Integer customerId;
    private String ipAddress;
    private String loginAddress = "";
    private String userAgent;
    private String session;
    private String loginTime;
    private String logoutTime;
    
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getLoginAddress() {
		return loginAddress;
	}

	public void setLoginAddress(String loginAddress) {
		this.loginAddress = loginAddress;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	} 

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

    @Override
    public String toString() {
        return "LoginLog [id=" + id + ", customerId=" + customerId + ", ipAddress=" + ipAddress + ", loginAddress=" + loginAddress + ", userAgent=" + userAgent + ", session=" + session + ", loginTime=" + loginTime + ", logoutTime=" + logoutTime + "]";
    }
}