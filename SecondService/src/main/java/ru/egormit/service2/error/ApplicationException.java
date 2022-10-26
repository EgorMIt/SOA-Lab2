package ru.egormit.service2.error;

import lombok.Getter;
import ru.egormit.service2.error.model.ApplicationError;

@Getter
public class ApplicationException extends RuntimeException {

    private final ApplicationError error;

    public ApplicationException(ApplicationError error) {
        super(error.getMessage());
        this.error = error;
    }

}
