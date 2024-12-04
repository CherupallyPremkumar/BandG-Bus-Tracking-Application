package com.bgbus.tracker.bus.configuration.controller;

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
import com.bgbus.tracker.bus.model.Bus;
import org.chenile.security.model.SecurityConfig;

@RestController
@ChenileController(value = "busService", serviceName = "_busStateEntityService_",
		healthCheckerName = "busHealthChecker")
public class BusController extends ControllerSupport{
	
	@GetMapping("/bus/{id}")
    @SecurityConfig(authorities = {"some_premium_scope","test.premium"})
	public ResponseEntity<GenericResponse<StateEntityServiceResponse<Bus>>> retrieve(
			HttpServletRequest httpServletRequest,
			@PathVariable String id){
		return process(httpServletRequest,id);
	}

	@PostMapping("/bus")
    @SecurityConfig(authorities = {"some_premium_scope","test.premium"})
	public ResponseEntity<GenericResponse<StateEntityServiceResponse<Bus>>> create(
			HttpServletRequest httpServletRequest,
			@ChenileParamType(StateEntity.class)
			@RequestBody Bus entity){
		return process(httpServletRequest,entity);
	}

	
	@PatchMapping("/bus/{id}/{eventID}")
	@BodyTypeSelector("busBodyTypeSelector")
    @SecurityConfig(authoritiesSupplier = "busEventAuthoritiesSupplier")
	public ResponseEntity<GenericResponse<StateEntityServiceResponse<Bus>>> processById(
			HttpServletRequest httpServletRequest,
			@PathVariable String id,
			@PathVariable String eventID,
			@ChenileParamType(Object.class) 
			@RequestBody String eventPayload){
		return process(httpServletRequest,id,eventID,eventPayload);
	}


}
