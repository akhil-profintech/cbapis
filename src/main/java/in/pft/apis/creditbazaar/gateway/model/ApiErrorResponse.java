package in.pft.apis.creditbazaar.gateway.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;
import org.springframework.http.HttpStatus;

import java.time.Instant;

import static in.pft.apis.creditbazaar.gateway.constants.CommonConstants.DATE_PATTERN;
import static in.pft.apis.creditbazaar.gateway.constants.CommonConstants.TIME_ZONE;

@Value
public class ApiErrorResponse
{
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN, timezone = TIME_ZONE)
    Instant timestamp;
    String message;
    String error;
    HttpStatus status;
    String path;
}
