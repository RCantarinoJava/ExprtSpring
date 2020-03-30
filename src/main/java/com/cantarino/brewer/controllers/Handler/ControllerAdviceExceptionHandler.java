package com.cantarino.brewer.controllers.Handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cantarino.brewer.validations.exceptions.ErroEntidade;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {

	@ExceptionHandler(ErroEntidade.class)
	public ResponseEntity<String> handleErroEntidade(ErroEntidade erro) {
		return ResponseEntity.badRequest().body(erro.getMessage());
	}

}
