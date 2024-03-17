package com.office.security.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MemberService {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	IMemberDao iMemberDao;
	
	// 회원가입 확인
	public int createMemberConfirm(MemberDto memberDto) {
		log.info("createMemberConfirm()");
		
		memberDto.setM_pw(passwordEncoder.encode(memberDto.getM_pw()));
		
		return iMemberDao.insertNewMember(memberDto);
		
	}

	// 회원정보 가져오기
	public MemberDto getMemberDto(String a_id) {
		log.info("getMemberDto()");
		return iMemberDao.selectMemberByMId(a_id);
		
	}

	// 계정수정 확인
	public int modifyMemberConfirm(MemberDto memberDto) {
		log.info("modifyMemberConfirm()");
		
		memberDto.setM_pw(passwordEncoder.encode(memberDto.getM_pw()));
		
		return iMemberDao.updateMemberByMNo(memberDto);
		
	}

	// 계정삭제 확인
	public int deleteMemberConfirm(String m_id) {
		log.info("deleteMemberConfirm()");
		
		return iMemberDao.deleteMemberByMId(m_id);
	}
	
}
