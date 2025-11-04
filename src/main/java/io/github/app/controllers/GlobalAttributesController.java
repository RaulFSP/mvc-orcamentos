package io.github.app.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalAttributesController {

	@ModelAttribute("requestURI")
	public String requestUri(HttpServletRequest req) {
		return req.getRequestURI();
	}
}
