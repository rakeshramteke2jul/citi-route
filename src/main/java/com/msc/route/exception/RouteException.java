package com.msc.route.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RouteException extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String cityNotFoundException() {
		return messageSource.getMessage("invalid.city.message", null, LocaleContextHolder.getLocale());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String cityRouteException() {
		return messageSource.getMessage("route.error.message", null, LocaleContextHolder.getLocale());
	}

}
