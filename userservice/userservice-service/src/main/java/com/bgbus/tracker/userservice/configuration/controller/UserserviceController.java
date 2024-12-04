package com.bgbus.tracker.userservice.configuration.controller;

import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.chenile.base.response.GenericResponse;
import org.chenile.http.annotation.BodyTypeSelector;
import org.chenile.http.annotation.ChenileController;
import org.chenile.http.annotation.ChenileParamType;
import org.chenile.http.handler.ControllerSupport;
import org.springframework.http.ResponseEntity;

import org.chenile.stm.StateEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.chenile.workflow.dto.StateEntityServiceResponse;
import com.bgbus.tracker.userservice.model.Userservice;
import org.chenile.security.model.SecurityConfig;

@RestController
@ChenileController(value = "userserviceService", serviceName = "_userserviceStateEntityService_",
		healthCheckerName = "userserviceHealthChecker")
public class UserserviceController extends ControllerSupport{
	
	@GetMapping("/userservice/{id}")
    @SecurityConfig(authorities = {"some_premium_scope","test.premium"})
	public ResponseEntity<GenericResponse<StateEntityServiceResponse<Userservice>>> retrieve(
			HttpServletRequest httpServletRequest,
			@PathVariable String id){
		return process(httpServletRequest,id);
	}

	@PostMapping("/userservice")
    @SecurityConfig(authorities = {"some_premium_scope","test.premium"})
	public ResponseEntity<GenericResponse<StateEntityServiceResponse<Userservice>>> create(
			HttpServletRequest httpServletRequest,
			@ChenileParamType(StateEntity.class)
			@RequestBody Userservice entity){
		return process(httpServletRequest,entity);
	}

	
	@PatchMapping("/userservice/{id}/{eventID}")
	@BodyTypeSelector("userserviceBodyTypeSelector")
    @SecurityConfig(authoritiesSupplier = "userserviceEventAuthoritiesSupplier")
	public ResponseEntity<GenericResponse<StateEntityServiceResponse<Userservice>>> processById(
			HttpServletRequest httpServletRequest,
			@PathVariable String id,
			@PathVariable String eventID,
			@ChenileParamType(Object.class) 
			@RequestBody String eventPayload){
		return process(httpServletRequest,id,eventID,eventPayload);
	}


}
