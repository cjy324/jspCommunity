package com.sbs.example.jspCommunity;

public class App {

	public static String getSite() {
		return "JSP Community";
	}

	public static String getContextName() {
		return "jspCommunity";
	}
	
	public static String getMailUrl() {
		return "http://" + getSiteDomain() + ":" + getSitePort() + "/" + getContextName() + "/usr/home/main";
	}
	
	public static String getLoginUrl() {
		return "http://" + getSiteDomain() + ":" + getSitePort() + "/" + getContextName() + "/usr/member/doLoginForm";
	}
	
	private static int getSitePort() {
		return 8083;
	}

	private static String getSiteDomain() {
		return "localhost";
	}
	
}
