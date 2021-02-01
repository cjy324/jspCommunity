package com.sbs.example.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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

	//URL 인코딩 유틸
	public static String getUrlEncoded(String url) {
		try {
			return URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return url;
		}
	}
}
