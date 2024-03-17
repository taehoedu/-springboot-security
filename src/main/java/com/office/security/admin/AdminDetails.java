package com.office.security.admin;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SuppressWarnings("serial")
public class AdminDetails implements UserDetails {

	private AdminDto adminDto;
	
	public AdminDetails(AdminDto adminDto) {
		this.adminDto = adminDto;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collection = new ArrayList<>();
		
		collection.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return "ROLE_".concat(adminDto.getAuthorityDto().getA_authority_role_name());
				
			}
		});
		
		return collection;
	}

	@Override
	public String getPassword() {
		return adminDto.getA_pw();
		
	}

	@Override
	public String getUsername() {
		return adminDto.getA_id();
		
	}

	@Override
	public boolean isAccountNonExpired() {
		return adminDto.isA_isaccountnonexpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return adminDto.isA_isaccountnonlocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return adminDto.isA_iscredentialsnonexpired();
	}

	@Override
	public boolean isEnabled() {
		return adminDto.isA_isenabled();
	}

	public AdminDto getAdminDto() {
		return adminDto;
	}
	
}
