package com.sbs.example.jspCommunity.servlet;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.service.EmailService;
import com.sbs.example.util.Util;

@WebServlet(name = "loadAppConfig", urlPatterns = {"/loadConfig"}, loadOnStartup = 1 )
//외부 고객이 들어오지 않을 서블릿
public class ConfigServlet extends HttpServlet{
		
	   	// 생성자, 서블릿 실행시 실행
		// 기본적으로 서블릿이 호출되면 실행됨
		//but, loadOnStartup = 1 이 붙으면 이 서블릿은 프로그램 실행시 자동으로 초기화가 진행됨(호출하지 않아도)
		@Override
		public void init(ServletConfig config) throws ServletException {
			super.init(config);
			
			//config.json 정보 얻어오기
			ServletContext context = getServletContext();
			InputStream inStream = context.getResourceAsStream("/META-INF/config.json");
			
			//jackson을 통해 config.json을 읽기
			Map<String, Object> configMap = Util.getJsonMapFromFile(inStream);
			
			//프로그램 실행 시 mail 관련 정보 가져오기(거의 처음 순서로 실행됨)
			String gmailId = (String) configMap.get("gmailId");
			String gmailPw = (String) configMap.get("gmailPw");
			
			EmailService emailService = Container.emailService;
			emailService.init(gmailId, gmailPw, "jspCommunity", "jspCommunity");
			
			//프로그램 실행하자마자 메일 발송(테스트)
			//emailService.send("juy32400@gmail.com", "메일 제목", "메일 내용");
		}

}
