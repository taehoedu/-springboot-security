package com.office.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.office.security.admin.AdminAccessDeniedHandler;
import com.office.security.admin.AdminAuthenticationEntryPoint;
import com.office.security.admin.AdminDetailService;
import com.office.security.admin.IAdminDao;
import com.office.security.member.IMemberDao;
import com.office.security.member.MemberAccessDeniedHandler;
import com.office.security.member.MemberAuthenticationEntryPoint;
import com.office.security.member.MemberDetailService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	IAdminDao iAdminDao; 
	
	@Autowired
	IMemberDao iMemberDao;
	
	@Autowired
	AdminDetailService adminDetailService;
	
	@Autowired
	MemberDetailService memberDetailService;
	
	@Bean PasswordEncoder passwordEncoder() {
		log.info("passwordEncoder()");
		
		return new BCryptPasswordEncoder();
		
	}
	
	@Bean
	@Order(1)
	SecurityFilterChain filterChainForAdmin(HttpSecurity http) throws Exception {
		log.info("filterChainForAdmin()");
		
		http
			.cors(cors -> cors.disable())
			.csrf(csrf -> csrf.disable());
		
		http
			.securityMatcher("/admin/**")
			.authorizeHttpRequests(auth -> auth
					.requestMatchers(
							"/admin", 
							"/admin/create_admin_form",
							"/admin/create_admin_confirm", 
							"/admin/create_admin_ok", 
							"/admin/create_admin_ng", 
							"/admin/login_admin_form"
							).permitAll()
					.requestMatchers("/admin/service1/**").hasRole("SUPER_ADMIN")
					.requestMatchers("/admin/service2/**").hasRole("SUPER_ADMIN")
					.anyRequest().authenticated()
					);
		
		http
		.exceptionHandling(exceptionConfig -> exceptionConfig
				.authenticationEntryPoint(new AdminAuthenticationEntryPoint())
				.accessDeniedHandler(new AdminAccessDeniedHandler()));
		
		http
			.formLogin(login -> login
					.loginPage("/admin/login_admin_form")
					.loginProcessingUrl("/admin/login_admin_confirm")
					.usernameParameter("a_id")
					.passwordParameter("a_pw")
					.successHandler((request, response, authentication) -> {
						log.info("admin login success handeler");
						
						RequestCache requestCache = new HttpSessionRequestCache();
						SavedRequest savedRequest = requestCache.getRequest(request, response);
						String targetURI = "/admin";
						if (savedRequest != null) {
							targetURI = savedRequest.getRedirectUrl();
							requestCache.removeRequest(request, response);
							
						}
						response.sendRedirect(targetURI);
						
					})
					.failureHandler((request, response, exception) -> {
						log.info("admin login fail handeler");
						log.info("exception: " + exception);
						
						response.sendRedirect("/admin/login_admin_form");
						
					}));
		
		http
			.logout(logout -> logout
					.logoutUrl("/admin/logout_admin_confirm")
					.logoutSuccessHandler((request, response, authentication) -> {
						log.info("admin logout success");
						
						response.sendRedirect("/admin");
						
					})
					);
		http
			.userDetailsService(adminDetailService);
		
		http
			.sessionManagement(sess -> sess
					.maximumSessions(1)
					.maxSessionsPreventsLogin(false))
			.sessionManagement(sess -> sess
					.sessionFixation().newSession());
		
		return http.build();
	}
	
	@Bean
	@Order(2)
	SecurityFilterChain filterChainForMember(HttpSecurity http) throws Exception {
		log.info("filterChainForMember()");
		
		http
			.cors(cors -> cors.disable())
			.csrf(csrf -> csrf.disable());

		http
			.securityMatcher("/member/**")
			.authorizeHttpRequests(auth -> auth
					.requestMatchers(
							"/member", 
							"/member/create_member_form", 
							"/member/create_member_confirm", 
							"/member/create_member_ok", 
							"/member/create_member_ng", 
							"/member/login_member_form"
							).permitAll()
					.requestMatchers("/member/service1/**").hasRole("USER")
					.requestMatchers("/member/service2/**").hasRole("USER")
					.anyRequest().authenticated()
					);
		
		http
			.exceptionHandling(exceptionConfig -> exceptionConfig
					.authenticationEntryPoint(new MemberAuthenticationEntryPoint())
					.accessDeniedHandler(new MemberAccessDeniedHandler()));

		
		http
			.formLogin(login -> login
					.loginPage("/member/login_member_form")
					.loginProcessingUrl("/member/login_member_confirm")
					.usernameParameter("m_id")
					.passwordParameter("m_pw")
					.successHandler((request, response, authentication) -> {
						log.info("member login success handeler");
						
						RequestCache requestCache = new HttpSessionRequestCache();
						SavedRequest savedRequest = requestCache.getRequest(request, response);
						String targetURI = "/";
						if (savedRequest != null) {
							targetURI = savedRequest.getRedirectUrl();
							requestCache.removeRequest(request, response);
							
						}
						response.sendRedirect(targetURI);

						
					})
					.failureHandler((request, response, exception) -> {
						log.info("member login fail handeler");
						log.info("exception: " + exception);
						
						response.sendRedirect("/member/login_member_form");
						
					}));
		
		http
			.logout(logout -> logout
					.logoutUrl("/member/logout_member_confirm")
					.logoutSuccessHandler((request, response, authentication) -> {
						log.info("member logout success");
						
						response.sendRedirect("/");
						
					})
					);
		http
			.userDetailsService(memberDetailService);
		
		http
			.sessionManagement(sess -> sess
					.maximumSessions(1)
					.maxSessionsPreventsLogin(false))
			.sessionManagement(sess -> sess
					.sessionFixation().newSession());
		
		return http.build();
	}
	
}
