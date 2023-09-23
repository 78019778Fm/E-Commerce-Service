package com.alexandertutoriales.ecommerce.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción que mapea el código HTTP 422 - UNPROCESSABLE_ENTITY.
 */
@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends RuntimeException {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new unprocessable entity exception.
	 */
	public UnprocessableEntityException() {
		super();
	}

	/**
	 * Instantiates a new unprocessable entity exception.
	 *
	 * @param message            the message
	 * @param cause              the cause
	 * @param enableSuppression  the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	public UnprocessableEntityException(String message, Throwable cause, boolean enableSuppression,
                                        boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new unprocessable entity exception.
	 *
	 * @param message the message
	 * @param cause   the cause
	 */
	public UnprocessableEntityException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new unprocessable entity exception.
	 *
	 * @param message the message
	 */
	public UnprocessableEntityException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new unprocessable entity exception.
	 *
	 * @param cause the cause
	 */
	public UnprocessableEntityException(Throwable cause) {
		super(cause);
	}
}
