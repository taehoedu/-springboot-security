package com.office.security.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AdminDetailService implements UserDetailsService {
	
	@Autowired
	IAdminDao iAdminDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername()");
		
		AdminDto selectedAdminDto = 
				iAdminDao.selectAdminByAId(username);
		
		if (selectedAdminDto != null)
			return new AdminDetails(selectedAdminDto);
		
		return null;
		
//		return User.builder()
//				.username(selectedAdminDto.getA_id())
//				.password(selectedAdminDto.getA_pw())
//				.roles("ADMIN")
//				.build();
		
	}

}
