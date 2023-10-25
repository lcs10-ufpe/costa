package br.ufpe.cin.budgeting.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@EqualsAndHashCode(callSuper = false)
public class DateFormaInvalidException extends JsonMappingException {

    private HttpStatus status;
    private String keyMessage;

    public DateFormaInvalidException(final String message, final HttpStatus status) {
        super(null, message);
        this.status = status;
        this.keyMessage = message;
    }

}
