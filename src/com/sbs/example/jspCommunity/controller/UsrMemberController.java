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
import com.sbs.example.util.Util;

public class UsrMemberController extends Controller {

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
		if (Util.isEmpty(loginId)) {
			return msgAndBack(request, "아이디를 입력하세요.");
		}

		// String loginPw = request.getParameter("loginPw");
		// 암호화된 비밀번호 값을 받기
		String loginPw = request.getParameter("loginPwReal");
		if (Util.isEmpty(loginPw)) {
			return msgAndBack(request, "비밀번호를 입력하세요.");
		}

		String name = request.getParameter("name");
		if (Util.isEmpty(name)) {
			return msgAndBack(request, "이름을 입력하세요.");
		}

		String nickname = request.getParameter("nickname");
		if (Util.isEmpty(nickname)) {
			return msgAndBack(request, "닉네임을 입력하세요.");
		}

		String email = request.getParameter("email");
		if (Util.isEmpty(email)) {
			return msgAndBack(request, "email을 입력하세요.");
		}

		String cellPhoneNo = request.getParameter("cellPhoneNo");
		if (Util.isEmpty(cellPhoneNo)) {
			return msgAndBack(request, "연락처를 입력하세요.");
		}

		List<Member> members = memberService.getMemberListForPrint();

		// 해당 loginId가 사용가능한지 중복확인
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getLoginId().equals(loginId) == true) {
				return msgAndBack(request, "해당 아이디는 이미 사용중인 아이디입니다.");
			}
		}
		;

		// 해당 nickname이 사용가능한지 중복확인
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getNickname().equals(nickname) == true) {
				return msgAndBack(request, "해당 닉네임는 이미 사용중인 닉네임입니다.");
			}
		}
		;

		Map<String, Object> joinArg = new HashMap<>();
		joinArg.put("loginId", loginId);
		joinArg.put("loginPw", loginPw);
		joinArg.put("name", name);
		joinArg.put("nickname", nickname);
		joinArg.put("email", email);
		joinArg.put("cellPhoneNo", cellPhoneNo);

		// 신규 회원가입
		int id = memberService.join(joinArg);

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
		Container.attrService.setValue("member__" + member.getId() + "__extra__isPwChangeDateLimit", endDate, null);
		/* 비밀번호 변경기간 최초설정 끝 */

		/* 회원가입시 축하메일 발송 */
		Container.emailService.send(email, nickname + "님, 회원가입을 축하드립니다.", nickname + "님, 회원가입을 축하드립니다.");

		return "usr/member/doJoin";
	}

	// 로그인 폼
	public String doLoginForm(HttpServletRequest request, HttpServletResponse response) {
		return "usr/member/doLoginForm";
	}

	// 로그인
	public String doLogin(HttpServletRequest request, HttpServletResponse response) {
		String loginId = request.getParameter("loginId");
		if (Util.isEmpty(loginId)) {
			return msgAndBack(request, "아이디를 입력하세요.");
		}
		// String loginPw = request.getParameter("loginPw");
		// 암호화된 비밀번호 값을 받기
		String loginPw = request.getParameter("loginPwReal");
		if (Util.isEmpty(loginPw)) {
			return msgAndBack(request, "비밀번호를 입력하세요.");
		}

		Member member = memberService.getMemberByLoginId(loginId);

		// 해당 loginId가 등록된 id인지 확인
		if (member == null) {
			return msgAndBack(request, "해당 아이디는 없는 아이디입니다. 아이디를 확인하세요.");
		}

		// 해당 loginPw가 일치하는지 확인
		if (member.getLoginPw().equals(loginPw) == false) {
			return msgAndBack(request, "비밀번호가 틀렸습니다.");
		}

		// 로그인 여부를 세션에 저장
		HttpSession session = request.getSession();
		session.setAttribute("loginedMemberId", member.getId());

		/* 임시패스워드 사용 여부 확인(개선) 시작 */
		boolean isUsingTempPassword = memberService.getIsUsingTempPassword(member.getId());

		// 임시패스워드 사용중이면 알림창 보여주고 회원정보로 이동
		if (isUsingTempPassword) {
			return msgAndReplaceUrl(request, "현재 임시비밀번호를 사용 중입니다. 비밀번호를 변경해 주세요.", "../member/showMyPage");
		}
		/* 임시패스워드 사용 여부 확인(개선) 끝 */

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

		/* 비밀번호 변경날짜 90일 이상 지났는지 여부 확인 끝 */

		
		// 로그인 알림창 보여주고 화면으로 이동
		String replaceUrl = "../home/main";

		if (Util.isEmpty(request.getParameter("nextUrlAfterLogin")) == false) {
			replaceUrl = request.getParameter("nextUrlAfterLogin");
		}
		
		if (Util.isEmpty(request.getParameter("beforeUrl")) == false) {
			replaceUrl = request.getParameter("beforeUrl");
		}
		
		// 만약 오늘날짜가 지정날짜보다 크거나 같으면 기간만료 알림창 보여주고 메인으로 이동
		if (compare >= 0) {
			return msgAndReplaceUrl(request, "비밀번호 변경 후 90일이 지났습니다. 비밀번호를 변경해 주세요.", replaceUrl);
		}

		return msgAndReplaceUrl(request, member.getNickname() + ", 님 반갑습니다.", replaceUrl);

	}

	// 로그아웃
	public String doLogout(HttpServletRequest request, HttpServletResponse response) {

		// 로그인 여부를 세션에서 삭제
		HttpSession session = request.getSession();
		session.removeAttribute("loginedMemberId");
		
		String replaceUrl = "../home/main";
		
		if (Util.isEmpty(request.getParameter("beforeUrl")) == false) {
			replaceUrl = request.getParameter("beforeUrl");
		}

		return msgAndReplaceUrl(request, "로그아웃 되었습니다.", replaceUrl);

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

		return jsonWithData(request, new ResultData(code, msg, "loginId", loginId));
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

		return jsonWithData(request, new ResultData(code, msg, "nickname", nickname));
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

		int id = Util.getAsInt(request.getParameter("id"), 0);
		if (id == 0) {
			return msgAndBack(request, "회원 번호를 입력하세요.");
		}

		String loginPw = request.getParameter("loginPwReal");

		// 비밀번호 null여부 확인
		if (loginPw != null && loginPw.trim().length() == 0) {
			loginPw = null;
		}

		String name = request.getParameter("name");
		if (Util.isEmpty(name)) {
			return msgAndBack(request, "이름을 입력하세요.");
		}

		String nickname = request.getParameter("nickname");

		// 닉네임 null여부 확인
		if (nickname != null && nickname.trim().length() == 0) {
			nickname = null;
		}

		String email = request.getParameter("email");
		if (Util.isEmpty(email)) {
			return msgAndBack(request, "email을 입력하세요.");
		}

		String cellphoneNo = request.getParameter("cellphoneNo");
		if (Util.isEmpty(cellphoneNo)) {
			return msgAndBack(request, "연락처를 입력하세요.");
		}

		// 임시패스워드 사용 여부 확인
		boolean isUsingTempPassword = memberService.getIsUsingTempPassword(id);

		Map<String, Object> args = new HashMap<String, Object>();

		args.put("id", id);
		args.put("loginPw", loginPw);
		args.put("name", name);
		args.put("nickname", nickname);
		args.put("email", email);
		args.put("cellphoneNo", cellphoneNo);

		memberService.modify(args);

		// 회원수정 후 비번 변경 여부 확인
		Member member = memberService.getMemberById(id);
		String modifiedLoginPw = member.getLoginPw();

		// 임시패스워드 사용중인 회원이 비밀번호를 수정했으면 attr정보 삭제
		if (loginPw != null && isUsingTempPassword && loginPw != modifiedLoginPw) {
			Container.attrService.remove("member__" + member.getId() + "__extra__isUsingTempPassword");
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
			Container.attrService.setValue("member__" + member.getId() + "__extra__isPwChangeDateLimit", endDate, null);
		}
		/* 비밀번호 변경 시 변경기간 재설정 끝 */

		return msgAndReplaceUrl(request, "수정되었습니다.", "../member/showMyPage");
	}

	// 아이디 찾기 폼
	public String doFindLoginIdForm(HttpServletRequest request, HttpServletResponse response) {
		return "usr/member/doFindLoginIdForm";
	}

	// 아이디 찾기
	public String doFindLoginId(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		if (Util.isEmpty(name)) {
			return msgAndBack(request, "이름을 입력하세요.");
		}
		String email = request.getParameter("email");
		if (Util.isEmpty(email)) {
			return msgAndBack(request, "email을 입력하세요.");
		}

		Member member = memberService.getMemberByNameAndEmail(name, email);

		// 해당 이름과 이메일주소를 가진 회원이 존재하는지 확인
		if (member == null) {
			return msgAndBack(request, "일치하는 회원이 존재하지 않습니다.");
		}

		// 로그인아이디 알림창 보여주고 로그인화면으로 이동
		return msgAndReplaceUrl(request, name + "회원님의 아이디는 \"" + member.getLoginId() + "\"입니다.",
				"../member/doLoginForm");
	}

	// 비밀번호 찾기 폼
	public String doFindLoginPwForm(HttpServletRequest request, HttpServletResponse response) {
		return "usr/member/doFindLoginPwForm";
	}

	// 비밀번호 찾기
	public String doFindLoginPw(HttpServletRequest request, HttpServletResponse response) {
		String loginId = request.getParameter("loginId");
		if (Util.isEmpty(loginId)) {
			return msgAndBack(request, "아이디를 입력하세요.");
		}

		String email = request.getParameter("email");
		if (Util.isEmpty(email)) {
			return msgAndBack(request, "email을 입력하세요.");
		}

		Member member = memberService.getMemberByLoginId(loginId);

		// 해당 loginId가 등록된 id인지 확인
		if (member == null) {
			return msgAndBack(request, "일치하는 회원이 존재하지 않습니다.");
		}

		// 해당 email이 일치하는지 확인
		if (member.getEmail().equals(email) == false) {
			return msgAndBack(request, "이메일주소가 일치하지 않습니다.");
		}

		// 임시 비밀번호 생성 후 회원 email로 발송(개선)
		ResultData sendTempLoginPwToEmailRs = memberService.sendTempLoginPwToEmail(member);

		/// 만약 메일 발송 실패인 경우
		if (sendTempLoginPwToEmailRs.isFail()) {
			return msgAndBack(request, sendTempLoginPwToEmailRs.getMsg());
		}

		// 임시패스워드 발급 알림창 보여주고 메인화면으로 이동
		return msgAndReplaceUrl(request, sendTempLoginPwToEmailRs.getMsg(), "../home/main");
	}

}
