package com.office.security.admin;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IAdminDao {

	public int insertNewAdmin(AdminDto adminDto);
	public AdminDto selectAdminByAId(String a_id);
	public int updateAdminByANo(AdminDto adminDto);
	public int deleteAdminByAId(String a_id);
	
}
	