package com.office.security.admin;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@GetMapping({"", "/"})
	public String home() {
		log.info("home()");
		
		return "admin/home";
		
	}
	
	// 회원가입 폼
	@GetMapping("/create_admin_form")
	public String createAdminForm() {
		log.info("createAdminForm()");
		
		String nextPage = "admin/create_admin_form";
		
		return nextPage;
		
	}
	
	// 회원가입 확인
	@PostMapping("/create_admin_confirm")
	public String createAdminConfirm(AdminDto adminDto) {
		log.info("createAdminConfirm()");
		
		String nextPage = "admin/create_admin_ok";
		
		int result = adminService.createAdminConfirm(adminDto);
		if (result <= 0)
			nextPage = "admin/create_admin_ng";
		
		return nextPage;
		
	}
	
	// 로그인 폼
	@GetMapping("/login_admin_form")
	public String loginMemberForm() {
		log.info("loginMemberForm()");
		
		String nextPage = "admin/login_admin_form";
		
		return nextPage;
		
	}
	
	// 계정수정 폼
	@GetMapping("/modify_admin_form")
	public String modifyAdminForm(Model model, Principal principal) {
		log.info("modifyAdminForm()");
		
		String nextPage = "admin/modify_admin_form";
		
		AdminDto adminDto = 
				adminService.getAdminDto(principal.getName());
		
		model.addAttribute("adminDto", adminDto);
		
		return nextPage;
		
	}
	
	// 계정수정 확인
	@PostMapping("/modify_admin_confirm")
	public String modifyAdminConfirm(AdminDto adminDto) {
		log.info("modifyAdminConfirm()");
		
		String nextPage = "admin/modify_admin_ok";
		
		int result = adminService.modifyAdminConfirm(adminDto);
		if (result <= 0)
			nextPage = "admin/modify_admin_ng";
		
		return nextPage;
		
	}
	
	// 계정삭제 확인
	@GetMapping("/delete_admin_confirm")
	public String deleteAdminConfirm(Principal principal) {
		log.info("deleteAdminConfirm()");
		
		int result = adminService.deleteAdminConfirm(principal.getName());
		if (result > 0)
			return "redirect:/admin/logout_admin_confirm";
		
		else 
			return "redirect:/admin";
		
	}
		
}
