package lk.ijse.security_back_end.Exception;

import lk.ijse.security_back_end.DTO.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobleException {
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public APIResponse usernameNotFound(UsernameNotFoundException ex) {
        return new APIResponse(
                HttpStatus.NOT_FOUND.value(),
                "User Not Found",
                ex.getMessage()
        );
    }


}
