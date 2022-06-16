package peaksoft.company_restapi.responses;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class Response{
    private HttpStatus httpStatus;
    private String message;
}