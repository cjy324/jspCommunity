package com.sbs.example.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {

	// json파일 생성 유틸
	public static String getJsonText(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		String rs = "";
		try {
			rs = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return rs;
	}

	// json파일을 Map으로 가져오는 유틸
	public static Map getJsonMapFromFile(InputStream inStream) {
		ObjectMapper mapper = new ObjectMapper();

		try {
			return mapper.readValue(inStream, Map.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

	// 메일 보내는 유틸
	public static int sendMail(String smtpServerId, String smtpServerPw, String from, String fromName, String to,
			String title, String body) {
		Properties prop = System.getProperties();
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", "587");

		Authenticator auth = new MailAuth(smtpServerId, smtpServerPw);

		Session session = Session.getDefaultInstance(prop, auth);

		MimeMessage msg = new MimeMessage(session);

		try {
			msg.setSentDate(new Date());

			msg.setFrom(new InternetAddress(from, fromName));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject(title, "UTF-8");
			msg.setContent(body, "text/html; charset=UTF-8");

			Transport.send(msg);

			// 메일발송 실패시 -1,-2,-3 리턴
		} catch (AddressException ae) {
			System.out.println("AddressException : " + ae.getMessage());
			return -1;
		} catch (MessagingException me) {
			System.out.println("MessagingException : " + me.getMessage());
			return -2;
		} catch (UnsupportedEncodingException e) {
			System.out.println("UnsupportedEncodingException : " + e.getMessage());
			return -3;
		}

		// 메일발송 성공시 1 리턴
		return 1;
	}

	// 임시 패스워드 생성 유틸
	public static String getTempPassword(int length) {
		int index = 0;
		char[] charArr = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f',
				'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < length; i++) {
			index = (int) (charArr.length * Math.random());
			sb.append(charArr[index]);
		}

		return sb.toString();
	}

	// 임시 패스워드 암호화 유틸
	public static String sha256(String base) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}

			return hexString.toString();

		} catch (Exception ex) {
			return "";
		}
	}

	// 입력받은 값을 int로 형변환시켜주는 유틸
	public static int getAsInt(Object value, int defaultValue) {
		if (value instanceof Integer) {
			return (int) value;
		} else if (value instanceof Long) {
			return Long.valueOf((long) value).intValue();
		} else if (value instanceof Float) {
			return Float.valueOf((float) value).intValue();
		} else if (value instanceof Double) {
			return Double.valueOf((double) value).intValue();
		} else if (value instanceof String) {
			try {
				return Integer.parseInt((String) value);
			} catch (NumberFormatException e) {
			}
		}

		return defaultValue;
	}

	// 입력받은 String이 null인지, 0인지 확인하는 유틸
	public static boolean isEmpty(Object object) {
		if (object == null) {
			return true;
		}
		if (object instanceof String) {
			if (((String) object).trim().length() == 0) {
				return true;
			}

		}

		return false;
	}

	// URL 인코딩 유틸
	public static String getUrlEncoded(String url) {
		try {
			return URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return url;
		}
	}

	public static String getNewUrlRemoved(String url, String paramName) {
		String deleteStrStarts = paramName + "=";
		int delStartPos = url.indexOf(deleteStrStarts);

		if (delStartPos != -1) {
			int delEndPos = url.indexOf("&", delStartPos);

			if (delEndPos != -1) {
				delEndPos++;
				url = url.substring(0, delStartPos) + url.substring(delEndPos, url.length());
			} else {
				url = url.substring(0, delStartPos);
			}
		}

		if (url.charAt(url.length() - 1) == '?') {
			url = url.substring(0, url.length() - 1);
		}

		if (url.charAt(url.length() - 1) == '&') {
			url = url.substring(0, url.length() - 1);
		}

		return url;
	}

	public static String getNewUrl(String url, String paramName, String paramValue) {
		url = getNewUrlRemoved(url, paramName);

		if (url.contains("?")) {
			url += "&" + paramName + "=" + paramValue;
		} else {
			url += "?" + paramName + "=" + paramValue;
		}

		url = url.replace("?&", "?");

		return url;
	}

	public static String getNewUrlAndEncoded(String url, String paramName, String pramValue) {
		return getUrlEncoded(getNewUrl(url, paramName, pramValue));
	}

	public static Map<String, Object> getParamMap(HttpServletRequest request) {
		Map<String, Object> param = new HashMap<>();

		Enumeration<String> parameterNames = request.getParameterNames();

		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			Object paramValue = request.getParameter(paramName);

			param.put(paramName, paramValue);
		}

		return param;
	}

	// 오늘부터 90일 계산 유틸
	public static String getPwChangeEndDate() {
		// 날짜 포멧 셋팅
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		// Calendar생성
		Calendar cal = Calendar.getInstance();
		// 오늘 날짜
		Date date = new Date();
		// 오늘 날짜로 셋팅
		cal.setTime(date);
		// 오늘 날짜에 기간을 더한다(90일)
		cal.add(cal.DATE, 90);
		// cal에 셋팅한 값을 지정한 형식으로 가져온다
		String endDate = sdf.format(cal.getTime());

		return endDate;
	}

	// 오늘 날짜 계산 유틸
	public static String getTodayDate() {
		// 날짜 포멧 셋팅
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		// Calendar생성
		Calendar cal = Calendar.getInstance();
		// 오늘 날짜 가져오기
		Date date = new Date();
		// 오늘 날짜 셋팅
		cal.setTime(date);
		String todayDate = sdf.format(cal.getTime());

		return todayDate;
	}

	// 암호 랜덤 생성
	public static String getUUIDStr() {
		return UUID.randomUUID().toString();
	}

	// 초단위인 토큰 유효기간을 날짜단위로 환산하는 유틸
	public static String getTokenExpiresInToDateTime(long originToken_expires_in) {
		// 현재 시간 초단위로 가져오기
		long currentTimeMillis = System.currentTimeMillis();
		// 현재 시간에 만료기간 더하기
		originToken_expires_in = currentTimeMillis + (originToken_expires_in * 1000);

		// 다시 날짜 단위로 환산
		SimpleDateFormat simpl = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String token_expires_in = simpl.format(originToken_expires_in);

		return token_expires_in;
	}

}
