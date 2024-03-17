package com.office.security.admin;

import lombok.Data;

@Data
public class AdminDto {

	private int a_no;
	private String a_id;
	private String a_pw;
	private int a_authority_no;
	private boolean a_isaccountnonexpired;
	private boolean a_isaccountnonlocked;
	private boolean a_iscredentialsnonexpired;
	private boolean a_isenabled;
	private String a_reg_date;
	private String a_mod_date;
	
	private AuthorityDto authorityDto;
	
}
