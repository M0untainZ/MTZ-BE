package MTZ.mountainz.global.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class RequestException extends RuntimeException {
	private final HttpStatus httpStatus;

	public RequestException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.httpStatus = errorCode.getHttpStatus();
	}
}
