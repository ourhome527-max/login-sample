package com.framework.login;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.HashMap;

@Service
@Slf4j
public class LoginService {

	private LoginMapper loginMapper;

	public LoginService(LoginMapper loginMapper) {
		this.loginMapper = loginMapper;
	}

	@Transactional(readOnly = true)
	public Map<String, Object> requestLoginThymeleaf(Map<String, Object> params) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("REPL_PAGE_MSG", "성공.");

		// String userName = (String)params.get("userName");
		// String userPassword = (String)params.get("userPassword");

		/*
		 * log.info("{} {}", userName, userPassword);
		 * 
		 * if ("admin".equals(userName) && "1234".equals(userPassword)) {
		 * resultMap.put("REPL_PAGE_MSG", "성공"); log.info("로그인 성공"); } else {
		 * resultMap.put("REPL_PAGE_MSG", "실패"); log.error("로그인 실패"); }
		 */

		// TODO 1. ID가 DB에 있는지 확인
		Map<String, Object> memberInfo = loginMapper.selectMemberInfo(params);
		// TODO 1-1 ID가 존재하지 않으면 로그인 실패
		if (memberInfo == null) {
			resultMap.put("REPL_PAGE_MSG", "존재하는 ID가 없습니다.");
		} else {
			log.info("ID는 있음");

			// TODO 1-2 ID가 존재하면 회원정보를 조회
			String dbPw = (String) memberInfo.get("PW");
			String requestPw = (String) params.get("userPassword");
			
			// TODO 2. PW가 맞는지 확인(조회한 회원정보의 PW 값을 String 비교)
			if (!requestPw.equals(dbPw)) {
				resultMap.put("REPL_PAGE_MSG", "PW가 일치하지 않습니다.");
			}
			
			// 회원 정보 설정
			resultMap.put("MEMBER_INFO", memberInfo);
			log.info("member 정보: {}", memberInfo);
		}

		
		
		return resultMap;
	}
}
