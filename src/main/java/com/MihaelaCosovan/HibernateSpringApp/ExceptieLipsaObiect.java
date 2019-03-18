package com.MihaelaCosovan.HibernateSpringApp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

	@ResponseStatus(HttpStatus.NOT_FOUND)
	public class ExceptieLipsaObiect extends RuntimeException{
		
		private static final long serialVersionUID = 1L;

		public ExceptieLipsaObiect() {
			super();
		}
	
	public ExceptieLipsaObiect(String message) {
		super(message);
	}
	
	public ExceptieLipsaObiect(String message, Throwable cause) {
		super(message, cause);
	}
}
