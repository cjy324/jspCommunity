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
			sb.append("&client_id=c5cafbd7423d14e396657832f41dd5e7"); // 본인이 발급받은 key
			sb.append("&redirect_uri=https://getit.devj.me/usr/member/doKakaoLogin"); // 본인이 설정해 놓은 경로
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
//			System.out.println("response body : " + result);

			// Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
			// 이 로직 실행을 위해서 pom.xml에 Gson 라이브러리를 추가한 것입니다.
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
			
			expires_in = element.getAsJsonObject().get("expires_in").getAsInt();
			refresh_token_expires_in = element.getAsJsonObject().get("refresh_token_expires_in").getAsInt();

			br.close();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 사용자토큰을 리턴
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
//			System.out.println("response body : " + result);

			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			// properties는 프로필 정보(닉네임/프로필 사진)를 갖고있습니다.(필수동의항목)
			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			// kakao_account는 이메일, 성별, 연령대 등의 정보를 갖고있습니다.(선택동의항목)
			JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

			boolean email_needs_agreement = kakao_account.getAsJsonObject().get("email_needs_agreement").getAsBoolean();

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

		// 사용자 정보를 리턴
		return userInfo;
	}

}

