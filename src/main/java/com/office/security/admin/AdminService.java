package com.office.security.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AdminService {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	IAdminDao iAdminDao;
	
	// 회원가입 확인
	public int createAdminConfirm(AdminDto adminDto) {
		log.info("createAdminConfirm()");
		
		adminDto.setA_pw(passwordEncoder.encode(adminDto.getA_pw()));
		
		try {
			
			return iAdminDao.insertNewAdmin(adminDto);	
			
		} catch (Exception e) {
			log.info(e.getMessage());
			
			return 0;
			
		}
		
	}
	
	// 회원정보 가져오기
	public AdminDto getAdminDto(String a_id) {
		log.info("getAdminDto()");
		return iAdminDao.selectAdminByAId(a_id);
		
	}

	// 계정수정 확인
	public int modifyAdminConfirm(AdminDto adminDto) {
		log.info("modifyAdminConfirm()");
		
		adminDto.setA_pw(passwordEncoder.encode(adminDto.getA_pw()));
		
		return iAdminDao.updateAdminByANo(adminDto);
	}

	// 계정삭제 확인
	public int deleteAdminConfirm(String a_id) {
		log.info("deleteAdminConfirm()");
		
		return iAdminDao.deleteAdminByAId(a_id);
		
	}
	
}
