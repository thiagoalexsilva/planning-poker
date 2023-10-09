package capgemini.challenge.api.exception;

import capgemini.api.openapi.dto.ModelApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PlanningPokerExceptionHandler {


    @ExceptionHandler(PlanningPokerException.class)
    public ResponseEntity<ModelApiResponse> planningPokerExceptionHandler(PlanningPokerException ex) {
        final var errorDTO = new ModelApiResponse()
                .message(ex.getMessage());
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ModelApiResponse> defaultExceptionHandler(final Exception ex) {
        final var errorDTO = new ModelApiResponse()
                .message(ex.getMessage());
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
