package capgemini.challenge.api.exception;

import lombok.Getter;

@Getter
public class PlanningPokerException extends RuntimeException{

    public PlanningPokerException() {
        super();
    }

    public PlanningPokerException(String message) {
        super(message);
    }
}
