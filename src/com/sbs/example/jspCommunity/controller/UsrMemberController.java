package com.sbs.example.jspCommunity.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Member;
import com.sbs.example.jspCommunity.dto.ResultData;
import com.sbs.example.jspCommunity.service.MemberService;

public class UsrMemberController {

	private MemberService memberService;

	public UsrMemberController() {
		memberService = Container.memberService;
	}

	// 회원가입 폼
	public String doJoinForm(HttpServletRequest request, HttpServletResponse response) {

		return "usr/member/doJoinForm";
	}

	// 회원가입
	public String doJoin(HttpServletRequest request, HttpServletResponse response) {

		String loginId = request.getParameter("loginId");
		// String loginPw = request.getParameter("loginPw");
		// 암호화된 비밀번호 값을 받기
		String loginPw = request.getParameter("loginPwReal");
		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String cellPhoneNo = request.getParameter("cellPhoneNo");

		List<Member> members = memberService.getMemberListForPrint();

		// 해당 loginId가 사용가능한지 중복확인
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getLoginId().equals(loginId) == true) {
				request.setAttribute("alertMsg", "해당 아이디는 이미 사용중인 아이디입니다.");
				request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
				return "common/redirect";
			}
		}

		// 해당 nickname이 사용가능한지 중복확인
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getNickname().equals(nickname) == true) {
				request.setAttribute("alertMsg", "해당 닉네임는 이미 사용중인 닉네임입니다.");
				request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
				return "common/redirect";
			}
		}

		Map<String, Object> joinArg = new HashMap<>();
		joinArg.put("loginId", loginId);
		joinArg.put("loginPw", loginPw);
		joinArg.put("name", name);
		joinArg.put("nickname", nickname);
		joinArg.put("email", email);
		joinArg.put("cellPhoneNo", cellPhoneNo);

		// 신규 회원가입
		int id = memberService.join(joinArg);

		// 생성 알림창 보여주고 회원정보로 이동하기
		request.setAttribute("alertMsg", id + "번 회원님 반갑습니다.");

		Member member = memberService.getMemberById(id);

		request.setAttribute("member", member);

		/* 비밀번호 변경기간 최초설정 시작 */
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

		// attr에 저장
		Container.attrService.setValue("member__" + member.getId() + "__extra__isPwChangeDateLimit", endDate,
				"2099-12-12 00:00:00");
		/* 비밀번호 변경기간 최초설정 끝 */

		/*
		 * // 회원가입시 축하메일 발송 EmailService emailService = Container.emailService;
		 * 
		 * emailService.send(email, nickname + "님, 회원가입을 축하드립니다.", nickname +
		 * "님, 회원가입을 축하드립니다.");
		 */
		return "usr/member/doJoin";

	}

	// 로그인 폼
	public String doLoginForm(HttpServletRequest request, HttpServletResponse response) {
		return "usr/member/doLoginForm";
	}

	// 로그인
	public String doLogin(HttpServletRequest request, HttpServletResponse response) {
		String loginId = request.getParameter("loginId");
		// String loginPw = request.getParameter("loginPw");
		// 암호화된 비밀번호 값을 받기
		String loginPw = request.getParameter("loginPwReal");

		Member member = memberService.getMemberByLoginId(loginId);

		// 해당 loginId가 등록된 id인지 확인
		if (member == null) {
			request.setAttribute("alertMsg", "해당 아이디는 없는 아이디입니다. 아이디를 확인하세요.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";
		}

		// 해당 loginPw가 일치하는지 확인
		if (member.getLoginPw().equals(loginPw) == false) {
			request.setAttribute("alertMsg", "비밀번호가 틀렸습니다.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";
		}

		// 로그인 여부를 세션에 저장
		HttpSession session = request.getSession();
		session.setAttribute("loginedMemberId", member.getId());

		// 임시패스워드 사용 여부 확인
		String rs = Container.attrService.getValue("member__" + member.getId() + "__extra__isUsingTempPassword");

		// 임시패스워드 사용중이면 알림창 보여주고 메인으로 이동
		if (rs.equals("1")) {
			request.setAttribute("alertMsg", "현재 임시비밀번호를 사용 중입니다. 비밀번호를 변경해 주세요.");
			request.setAttribute("replaceUrl", "../home/main");
			return "common/redirect";
		}

		/* 비밀번호 변경날짜 90일 이상 지났는지 여부 확인 시작 */
		String limitDate = Container.attrService.getValue("member__" + member.getId() + "__extra__isPwChangeDateLimit");

		// 날짜 포멧 셋팅
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		// Calendar생성
		Calendar cal = Calendar.getInstance();
		// 오늘 날짜 가져오기
		Date date = new Date();
		// 오늘 날짜 셋팅
		cal.setTime(date);
		String todayDate = sdf.format(cal.getTime());

		// 오늘 날짜와 제한기간 날짜 비교
		int compare = todayDate.compareTo(limitDate);

		// 만약 오늘날짜가 지정날짜보다 크거나 같으면 기간만료 알림창 보여주고 메인으로 이동
		if (compare >= 0) {
			request.setAttribute("alertMsg", "비밀번호 변경 후 90일이 지났습니다. 비밀번호를 변경해 주세요.");
			request.setAttribute("replaceUrl", "../home/main");
			return "common/redirect";
		}
		/* 비밀번호 변경날짜 90일 이상 지났는지 여부 확인 끝 */

		// 로그인 알림창 보여주고 메인화면으로 이동
		request.setAttribute("alertMsg", member.getNickname() + ", 님 반갑습니다.");
		request.setAttribute("replaceUrl", "../home/main");
		return "common/redirect";
	}

	// 로그아웃
	public String doLogout(HttpServletRequest request, HttpServletResponse response) {

		// 로그인 여부를 세션에서 삭제
		HttpSession session = request.getSession();
		session.removeAttribute("loginedMemberId");

		request.setAttribute("alertMsg", "로그아웃 되었습니다.");
		request.setAttribute("replaceUrl", "../home/main");
		return "common/redirect";

	}

	// 회원가입 폼 작성 시 ajax로 중복체크
	public String getLoginIdDup(HttpServletRequest request, HttpServletResponse response) {

		String loginId = request.getParameter("loginId");

		Member member = memberService.getMemberByLoginId(loginId);

		String code = null;
		String msg = null;

		if (member == null) {
			code = "S-1"; // S = success의 약자, 숫자는 유형 개념 // 1이면 일반적인 성공, 2이면 약간 문제는 있지만 성공? 이런 방식
			msg = "해당 ID는 사용이 가능합니다.";
		}
		if (member != null) {
			code = "F-1";
			msg = "해당 ID는 이미 사용중입니다.";
		}
		if (loginId.trim().length() == 0) {
			code = "F-2";
			msg = "해당 ID는 사용이 불가능합니다.";
		}

		// ResultData 객체 도입으로 삭제
		/*
		 * rs.put("loginId", loginId); rs.put("code", code); rs.put("msg", msg);
		 * 
		 * rs 맵리스트를 json방식으로 생성해서 data로 보내기 request.setAttribute("data",
		 * Util.getJsonText(rs));
		 * 
		 * return "common/pure";
		 */

		request.setAttribute("data", new ResultData(code, msg, "loginId", loginId));
		return "common/json";
	}

	// ajax로 닉네임 중복체크
	public String getNicknameDup(HttpServletRequest request, HttpServletResponse response) {

		String nickname = request.getParameter("nickname");

		Member member = memberService.getMemberByNickname(nickname);

		String code = null;
		String msg = null;

		if (member == null) {
			code = "S-1"; // S = success의 약자, 숫자는 유형 개념
							// 1이면 일반적인 성공, 2이면 약간 문제는 있지만 성공? 이런 방식
			msg = "해당 닉네임은 사용이 가능합니다.";
		}
		if (member != null) {
			code = "F-1";
			msg = "해당 닉네임은 이미 사용중입니다.";
		}

		if (nickname.trim().length() == 0) {
			code = "F-2";
			msg = "해당 닉네임은 사용이 불가능합니다.";
		}

		// ResultData 객체 도입으로 삭제
		/*
		 * rs.put("nickname", nickname); rs.put("code", code); rs.put("msg", msg);
		 * 
		 * // rs 맵리스트를 json방식으로 생성해서 data로 보내기 request.setAttribute("data",
		 * Util.getJsonText(rs));
		 * 
		 * return "common/pure";
		 */

		request.setAttribute("data", new ResultData(code, msg, "nickname", nickname));
		return "common/json";

	}

	// 회원 정보 페이지
	public String showMyPage(HttpServletRequest request, HttpServletResponse response) {
		return "usr/member/showMyPage";
	}

	// 회원 정보 수정 폼
	public String doModifyForm(HttpServletRequest request, HttpServletResponse response) {
		return "usr/member/doModifyForm";
	}

	// 회원 정보 수정
	public String doModifyInfo(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));

		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPwReal");

		// 비밀번호 null여부 확인
		if (loginPw != null && loginPw.length() == 0) {
			loginPw = null;
		}

		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String cellphoneNo = request.getParameter("cellphoneNo");

		// 임시패스워드 사용 여부 확인
		String rs = Container.attrService.getValue("member__" + id + "__extra__isUsingTempPassword");

		Map<String, Object> args = new HashMap<String, Object>();

		args.put("id", id);
		args.put("loginId", loginId);
		args.put("loginPw", loginPw);
		args.put("name", name);
		args.put("nickname", nickname);
		args.put("email", email);
		args.put("cellphoneNo", cellphoneNo);

		memberService.modify(args);

		// 회원수정 후 비번 변경 여부 확인
		Member member = memberService.getMemberById(id);
		String modifiedLoginPw = member.getLoginPw();

		// 임시패스워드 사용중인 회원이 비밀번호를 수정했으면 attr기록 삭제
		if (loginPw != null && rs.equals("1") && loginPw != modifiedLoginPw) {
			Container.attrService.remove("member__" + id + "__extra__isUsingTempPassword");
		}

		/* 비밀번호 변경 시 변경기간 재설정 시작 */
		if (loginPw != null && loginPw != modifiedLoginPw) {
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

			// attr에서 기존 기록 삭제 신규 기간 저장
			Container.attrService.remove("member__" + member.getId() + "__extra__isPwChangeDateLimit");
			Container.attrService.setValue("member__" + member.getId() + "__extra__isPwChangeDateLimit", endDate,
					"2099-12-12 00:00:00");
		}
		/* 비밀번호 변경 시 변경기간 재설정 끝 */

		request.setAttribute("alertMsg", "수정되었습니다.");
		request.setAttribute("replaceUrl", "../member/showMyPage");
		return "common/redirect";
	}

	// 아이디 찾기 폼
	public String doFindLoginIdForm(HttpServletRequest request, HttpServletResponse response) {
		return "usr/member/doFindLoginIdForm";
	}

	// 아이디 찾기
	public String doFindLoginId(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		Member member = memberService.getMemberByNameAndEmail(name, email);

		// 해당 이름과 이메일주소를 가진 회원이 존재하는지 확인
		if (member == null) {
			request.setAttribute("alertMsg", "일치하는 회원이 존재하지 않습니다.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";
		}

		// 로그인아이디 알림창 보여주고 로그인화면으로 이동
		request.setAttribute("alertMsg", name + "회원님의 아이디는 \"" + member.getLoginId() + "\"입니다.");
		request.setAttribute("replaceUrl", "../member/doLoginForm");
		return "common/redirect";
	}

	// 비밀번호 찾기 폼
	public String doFindLoginPwForm(HttpServletRequest request, HttpServletResponse response) {
		return "usr/member/doFindLoginPwForm";
	}

	// 비밀번호 찾기
	public String doFindLoginPw(HttpServletRequest request, HttpServletResponse response) {
		String loginId = request.getParameter("loginId");
		String email = request.getParameter("email");

		Member member = memberService.getMemberByLoginId(loginId);

		// 해당 loginId가 등록된 id인지 확인
		if (member == null) {
			request.setAttribute("alertMsg", "일치하는 회원이 존재하지 않습니다.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";
		}

		// 해당 email이 일치하는지 확인
		if (member.getEmail().equals(email) == false) {
			request.setAttribute("alertMsg", "이메일주소가 일치하지 않습니다.");
			request.setAttribute("historyBack", true); // historyBack: 뒤로 돌아가기
			return "common/redirect";
		}

		// 임시 비밀번호 생성 후 회원 email로 발송
		// memberService.sendTempLoginPwToEmail(member);

		// 임시 비밀번호 생성 후 회원 email로 발송(개선)
		/*
		 * //ResultData 객체 도입으로 삭제 Map<String, Object> sendTempLoginPwToEmailRs =
		 * memberService.sendTempLoginPwToEmail(member);
		 * 
		 * String resultCode = (String) sendTempLoginPwToEmailRs.get("resultCode");
		 * String resultMsg = (String) sendTempLoginPwToEmailRs.get("resultMsg");
		 */

		// 임시 비밀번호 생성 후 회원 email로 발송(개선)
		ResultData sendTempLoginPwToEmailRs = memberService.sendTempLoginPwToEmail(member);

		/// 만약 메일 발송 실패인 경우
		// if(resultCode.contains("F")) {
		if (sendTempLoginPwToEmailRs.isFail()) {
			request.setAttribute("alertMsg", sendTempLoginPwToEmailRs.getMsg());
			request.setAttribute("historyBack", true);
			return "common/redirect";
		}

		// 임시패스워드 발급 알림창 보여주고 메인화면으로 이동
		request.setAttribute("alertMsg", sendTempLoginPwToEmailRs.getMsg());
		request.setAttribute("replaceUrl", "../home/main");
		return "common/redirect";
	}

}
