package com.example.bankingapplication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankingapplication.dto.RegistrationData;
import com.example.bankingapplication.dto.RegistrationResponse;
import com.example.bankingapplication.service.RegistrationService;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {
	@Autowired
	private RegistrationService registrationService;

	@PostMapping
	public ResponseEntity<RegistrationResponse> register(@Valid @RequestBody RegistrationData request) {
		return registrationService.registerUser(request);
	}
}
