package com.office.security.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IMemberDao {

	public int insertNewMember(MemberDto memberDto);
	public MemberDto selectMemberByMId(String m_id);
	public int updateMemberByMNo(MemberDto memberDto);
	public int deleteMemberByMId(String m_id);
	
}
