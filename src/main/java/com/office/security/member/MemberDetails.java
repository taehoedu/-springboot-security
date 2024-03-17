package com.office.security.member;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.office.security.admin.AdminDetails;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SuppressWarnings("serial")
public class MemberDetails implements UserDetails {

	MemberDto memberDto;
	
	public MemberDetails(MemberDto memberDto) {
		this.memberDto = memberDto;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collection = new ArrayList<>();
		
		collection.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return "ROLE_".concat(memberDto.getAuthorityDto().getM_authority_role_name());
				
			}
		});
		
		return collection;
		
	}

	@Override
	public String getPassword() {
		return memberDto.getM_pw();
	}

	@Override
	public String getUsername() {
		return memberDto.getM_id();
	}

	@Override
	public boolean isAccountNonExpired() {
		return memberDto.isM_iscredentialsnonexpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return memberDto.isM_isaccountnonlocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return memberDto.isM_iscredentialsnonexpired();
	}

	@Override
	public boolean isEnabled() {
		return memberDto.isM_isenabled();
	}

}
