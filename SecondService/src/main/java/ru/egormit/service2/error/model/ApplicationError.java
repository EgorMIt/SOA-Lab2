package ru.egormit.service2.error.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Ошибка приложения.
 *
 * @author Egor Mitrofanov.
 */
@Data
@AllArgsConstructor(staticName = "of")
public class ApplicationError {

    /**
     * Сообщение об ошибке.
     */
    private String message;

    /**
     * Время ошибки.
     */
    private LocalDateTime time;

    /**
     * Стутус, который возвращается при вызове ошибки.
     */
    private HttpStatus status;

    /**
     * Создание ошибки.
     *
     * @param message текст ошибки.
     * @param status  статус ошибки.
     */
    public ApplicationError(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
        this.time = LocalDateTime.now();
    }

}
