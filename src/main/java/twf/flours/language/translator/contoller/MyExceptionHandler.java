package twf.flours.language.translator.contoller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> handle(HttpMessageNotReadableException e) {
		return new ResponseEntity<String>("Request Must Contain Text", HttpStatus.BAD_REQUEST);
	}
}
