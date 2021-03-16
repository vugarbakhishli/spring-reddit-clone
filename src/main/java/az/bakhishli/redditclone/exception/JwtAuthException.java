package az.bakhishli.redditclone.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
@Getter
public class JwtAuthException extends RuntimeException{

    private HttpStatus httpStatus;

    public JwtAuthException(String message) {
        super(message);
    }

    public JwtAuthException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
