package com.cjy.jspCommunity.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class KakaoService {

	public Map<String, Object> getAccessToken(String authorize_code) {
		String access_Token = "";
		String refresh_Token = "";
		int expires_in = 0; //액세스 토큰 만료 시간(초) //최초: 21599초(약6시간)
		int refresh_token_expires_in = 0; //리프레시 토큰 만료 시간(초) //최초: 5183999초(약60일)
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로

			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=1b006c31c8a811b93a7505373a5e6b1b"); // 본인이 발급받은 key
			sb.append("&redirect_uri=http://localhost:8083/jspCommunity/usr/member/doKakaoLogin"); // 본인이 설정해 놓은 경로
			sb.append("&code=" + authorize_code);
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			// Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
			// 이 로직 실행을 위해서 pom.xml에 Gson 라이브러리를 추가한 것입니다.
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
			
			expires_in = element.getAsJsonObject().get("expires_in").getAsInt();
			refresh_token_expires_in = element.getAsJsonObject().get("refresh_token_expires_in").getAsInt();

			System.out.println("access_token : " + access_Token);
			System.out.println("refresh_token : " + refresh_Token);
			System.out.println("expires_in : " + expires_in);
			System.out.println("refresh_token_expires_in : " + refresh_token_expires_in);

			br.close();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 사용자토큰을 리턴합니다.
		Map<String, Object> tokensInfo = new HashMap<String, Object>();
		tokensInfo.put("access_Token", access_Token);
		tokensInfo.put("refresh_Token", refresh_Token);
		tokensInfo.put("expires_in", expires_in);
		tokensInfo.put("refresh_token_expires_in", refresh_token_expires_in);
		
		return tokensInfo;
	}

	public HashMap<String, Object> getUserInfo(String access_Token) {

		// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		HashMap<String, Object> userInfo = new HashMap<>();
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			// 요청에 필요한 Header에 포함될 내용
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			// properties는 프로필 정보(닉네임/프로필 사진)를 갖고있습니다.(필수동의항목)
			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			// kakao_account는 이메일, 성별, 연령대 등의 정보를 갖고있습니다.(선택동의항목)
			JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

			// 카카오로그인시 사용자가 '이메일정보제공' 항목을 선택하지 않을 수도 있습니다.
			// 만약, 정보제공에 동의하지 않았다면 '"email_needs_agreement":true'값이 나오고,
			// 정보제공에 동의했다면 '"email_needs_agreement":false'값이 나옵니다.
			boolean email_needs_agreement = kakao_account.getAsJsonObject().get("email_needs_agreement").getAsBoolean();

			// 위에서 '"email_needs_agreement":true'값이 나온다면 kakao_account 객체 내에 "email" key값이
			// null이 되고맙니다.
			// 따라서 "email"값을 가져올 수 없게 되어 오류가 발생합니다.
			// 이를 방지하기 위해 if문으로 아래와 같이 처리했습니다.
			String email = "이메일 동의 항목에 사용자 동의 필요";
			if (email_needs_agreement == false) {
				email = kakao_account.getAsJsonObject().get("email").getAsString();
			}
			
			String kakaoId = element.getAsJsonObject().get("id").getAsString();
			String nickname = properties.getAsJsonObject().get("nickname").getAsString();
			String profile_image = properties.getAsJsonObject().get("profile_image").getAsString();
			String thumbnail_image = properties.getAsJsonObject().get("thumbnail_image").getAsString();

			userInfo.put("kakaoId", kakaoId);
			userInfo.put("nickname", nickname);
			userInfo.put("email", email);
			userInfo.put("profile_image", profile_image);
			userInfo.put("thumbnail_image", thumbnail_image);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 사용자 정보를 리턴합니다.
		return userInfo;
	}

	// 카카오계정 로그아웃
	public void kakaoLogout(String access_Token) {
		// 우선 session에 저장되어있는 access_Token값을 가져와 access_Token값을 통해 로그인되어있는 사용자를 확인합니다.
		// 이후 로그아웃을 진행합니다.
		String reqURL = "https://kapi.kakao.com/v1/user/logout";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String result = "";
			String line = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println(result);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 카카오계정 연결 해제
	public void kakaoUnlink(String access_Token) {
		// 우선 session에 저장되어있는 access_Token값을 가져와 access_Token값을 통해 로그인되어있는 사용자를 확인합니다.
		// 이후 계정연결을 해제합니다.
		String reqURL = "https://kapi.kakao.com/v1/user/unlink";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String result = "";
			String line = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println(result);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 나에게 메시지 보내기
	public boolean isSendMessage(String access_Token) {
		// 마찬가지로 access_Token값을 가져와 access_Token값을 통해 로그인되어있는 사용자를 확인합니다.
		String reqURL = "https://kapi.kakao.com/v2/api/talk/memo/default/send";
		try {
			URL url = new URL(reqURL);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true); // OutputStream으로 POST 데이터를 넘겨주겠다는 옵션.
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();

			
			//json 형식으로 전송 데이터 셋팅
			JsonObject json = new JsonObject();
			json.addProperty("object_type", "text");
			json.addProperty("text", "카카오 메시지 테스트 전송");
			json.addProperty("button_title", "웹으로 이동"); //button_title은 선택사항입니다.
			// 만약, button_title을 넣지 않으면 버튼명이 디폴트 값으로 "자세히 보기"로 나옵니다.
			JsonObject link = new JsonObject();
			link.addProperty("web_url", "https://www.naver.com"); // 카카오개발자사이트 앱>앱설정>플랫폼>Web>사이트도메인에 등록한 도메인 입력
			link.addProperty("mobile_web_url", "https://www.naver.com"); // 카카오개발자사이트 앱>앱설정>플랫폼>Web>사이트도메인에 등록한 도메인 입력
																//만약, 카카오개발자사이트에 도메인을 등록하지 않았다면 링크버튼 자체가 나오지 않습니다.

			json.add("link", link.getAsJsonObject());

			sb.append("template_object=" + json);

			System.out.println(sb.toString());

			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			// response body : 0이면 성공
			System.out.println("response body : " + result);

			bw.close();
			br.close();

			if (responseCode == 200) {
				return true;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

}

