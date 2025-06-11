package com.framework.login;

import org.apache.ibatis.annotations.Mapper;
import java.util.Map;

@Mapper
public interface LoginMapper {
	
	// ID가 존재하면 회원 정보 조회
	Map<String, Object> selectMemberInfo(Map<String, Object> params);
}
