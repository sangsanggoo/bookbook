package com.toyproject.bookmanagement.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toyproject.bookmanagement.aop.annotation.ValidAspect;
import com.toyproject.bookmanagement.dto.auth.SignupReqDto;

@RestController
@RequestMapping("/auth") //모든 요청은 앞에 /auth가 붙음 
public class AuthenticationController {
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login() {
		return ResponseEntity.ok().body(null);
	}
	//CORS 열어주는거임
	@CrossOrigin
	@ValidAspect
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody SignupReqDto signupReqDto,BindingResult bindingResult) {
		
		return ResponseEntity.ok().body(null);
	}
}


