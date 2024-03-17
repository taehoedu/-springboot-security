package com.office.security.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MemberDetailService implements UserDetailsService {
	
	@Autowired
	IMemberDao iMemberDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername()");
		
		MemberDto selectedMemberDto = 
				iMemberDao.selectMemberByMId(username);
		
		if (selectedMemberDto != null)
			return new MemberDetails(selectedMemberDto);
		
		return null;
		
//		return User.builder()
//				.username(selectedMemberDto.getM_id())
//				.password(selectedMemberDto.getM_pw())
//				.roles("USER")
//				.build();
		
	}

}
