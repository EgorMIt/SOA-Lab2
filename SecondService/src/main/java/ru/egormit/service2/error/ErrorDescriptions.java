package ru.egormit.service2.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import ru.egormit.service2.error.model.ApplicationError;

/**
 * Ошибки с комментариями.
 *
 * @author Egor Mitrofanov.
 */
@Getter
@RequiredArgsConstructor
public enum ErrorDescriptions {
    HANDLER_NOT_FOUND("HANDLER_NOT_FOUND", "Не найден обработчик", HttpStatus.NOT_FOUND),
    SPACEMACS_IS_BUSY("SPACEMACS_IS_BUSY", "Этот космодесантник уже на другом корабле", HttpStatus.BAD_REQUEST),
    STARSHIP_IS_EMPTY("STARSHIP_IS_EMPTY", "Этот космический корабль уже пустой", HttpStatus.BAD_REQUEST),
    STARSHIP_NOT_FOUND("STARSHIP_IS_EMPTY", "Этот космический корабль уже пустой", HttpStatus.NOT_FOUND);

    /**
     * Код ошибки.
     */
    private final String code;

    /**
     * Сообщение ошибки.
     */
    private final String message;

    /**
     * Статус ошибки.
     */
    private final HttpStatus status;

    /**
     * Метод выбрасывает исключение приложения.
     *
     * @throws ApplicationException исключение приложения
     */
    public void throwException() throws ApplicationException {
        throw exception();
    }

    /**
     * Метод выбрасывает ислючение если объект == null.
     *
     * @param obj объект для проверки
     */
    public void throwIfNull(Object obj) {
        if (obj == null) {
            throw exception();
        }
    }

    /**
     * Метод выбрасывает ислючение если условие истинно.
     *
     * @param condition условие для проверки
     */
    public void throwIfTrue(Boolean condition) {
        if (condition) {
            throw exception();
        }
    }

    /**
     * Метод выбрасывает ислючение если условие ложно.
     *
     * @param condition условие для проверки
     */
    public void throwIfFalse(Boolean condition) {
        if (!condition) {
            throw exception();
        }
    }

    public ApplicationError applicationError() {
        return new ApplicationError(this.message, this.status);
    }


    public ApplicationException exception() {
        return new ApplicationException(applicationError());
    }
}
