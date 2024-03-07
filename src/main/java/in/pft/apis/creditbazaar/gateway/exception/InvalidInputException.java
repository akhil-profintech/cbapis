package in.pft.apis.creditbazaar.gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidInputException extends RuntimeException
{
    @Serial
    private static final long serialVersionUID = 1L;
    private final String errorCode;
    private final String message;

    public InvalidInputException(String message,String errorCode)
    {
        super(message);
        this.errorCode=errorCode;
        this.message=message;
    }
}
