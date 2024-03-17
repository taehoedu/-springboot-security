package com.office.security.member;

import lombok.Data;

@Data
public class MemberDto {

	private int m_no;
	private String m_id;
	private String m_pw;
	private int m_authority_no;
	private boolean m_isaccountnonexpired;
	private boolean m_isaccountnonlocked;
	private boolean m_iscredentialsnonexpired;
	private boolean m_isenabled;
	private String m_reg_date;
	private String m_mod_date;
	
	private AuthorityDto authorityDto;
	
}
