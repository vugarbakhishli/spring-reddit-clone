package az.bakhishli.redditclone.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static az.bakhishli.redditclone.constants.HttpResponseConstants.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends DefaultErrorAttributes {

    @ExceptionHandler(AlreadyVotedPostException.class)
    public final ResponseEntity<Map<String, Object>> handle(AlreadyVotedPostException ex,
                                                            WebRequest request){
        log.trace("You have already voted for this post");
        return ofType(request, HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(JwtAuthException.class)
    public final ResponseEntity<Map<String, Object>> handle(JwtAuthException ex,
                                                            WebRequest request){
        log.trace("JWT token is expired or invalid");
        return ofType(request, HttpStatus.UNAUTHORIZED, ex.getMessage());
    }


    protected ResponseEntity<Map<String, Object>> ofType(WebRequest request, HttpStatus status, String message) {
        return ofType(request, status, message, Collections.EMPTY_LIST);
    }


    private ResponseEntity<Map<String, Object>> ofType(WebRequest request, HttpStatus status, String message,
                                                       List validationErrors) {
        Map<String, Object> attributes = getErrorAttributes(request, ErrorAttributeOptions.defaults());
        attributes.put(STATUS, status.value());
        attributes.put(ERROR, status.getReasonPhrase());
        attributes.put(MESSAGE, message);
        attributes.put(ERRORS, validationErrors);
        attributes.put(PATH, ((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(attributes, status);
    }


}
