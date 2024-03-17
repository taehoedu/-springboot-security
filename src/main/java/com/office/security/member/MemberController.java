package com.office.security.member;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.office.security.admin.AdminDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService memberService;
	
	// member 홈
	@GetMapping({"", "/"})
	public String home() {
		log.info("home()");
		
		return "member/home";
		
	}
	
	// 회원가입 폼
	@GetMapping("/create_member_form")
	public String createMemberForm() {
		log.info("createMemberForm()");
		
		String nextPage = "member/create_member_form";
		
		return nextPage;
		
	}
	
	// 회원가입 확인
	@PostMapping("/create_member_confirm")
	public String createMemberConfirm(MemberDto memberDto) {
		log.info("createMemberConfirm()");
		
		String nextPage = "member/create_member_ok";
		
		int result = memberService.createMemberConfirm(memberDto);
		if (result <= 0)
			nextPage = "member/create_member_ng";
		
		return nextPage;
		
	}
	
	// 로그인 폼
	@GetMapping("/login_member_form")
	public String loginMemberForm() {
		log.info("loginMemberForm()");
		
		String nextPage = "member/login_member_form";
		
		return nextPage;
		
	}
	
	// 계정 수정 폼
	@GetMapping("/modify_member_form")
	public String modifyMemberForm(Model model , Principal principal) {
		log.info("modifyMemberForm()");
		
		String nextPage = "member/modify_member_form";
		
		MemberDto memberDto = 
				memberService.getMemberDto(principal.getName());
		
		model.addAttribute("memberDto", memberDto);
		
		return nextPage;
		
	}
	
	// 계정수정 확인
	@PostMapping("/modify_member_confirm")
	public String modifyMemberConfirm(MemberDto memberDto) {
		log.info("modifyMemberConfirm()");
		
		String nextPage = "member/modify_member_ok";
		
		int result = memberService.modifyMemberConfirm(memberDto);
		if (result <= 0)
			nextPage = "member/modify_member_ng";
		
		return nextPage;
		
	}
	
	// 계정삭제 확인
	@GetMapping("/delete_member_confirm")
	public String deleteMemberConfirm(Principal principal) {
		log.info("deleteMemberConfirm()");
		
		int result = memberService.deleteMemberConfirm(principal.getName());
		if (result > 0)
			return "redirect:/member/logout_member_confirm";
		
		else 
			return "redirect:/member";
		
	}
	
}
