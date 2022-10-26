package ru.egormit.service2.error.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.egormit.library.ErrorResponse;
import ru.egormit.service2.error.ApplicationException;

import java.io.IOException;

/**
 * Декодер кастомных ошибок.
 *
 * @author Egor Mitrofanov.
 */
@RequiredArgsConstructor
@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {

    /**
     * {@inheritDoc}.
     */
    private final ObjectMapper objectMapper;

    @Override
    public Exception decode(String methodKey, Response response) {
        String body;
        try {
            body = response.body().asInputStream().toString();
        } catch (IOException ioe) {
            body = "";
        }
        ErrorResponse error;
        try {
            error = objectMapper.readValue(body, ErrorResponse.class);
        } catch (Exception e) {
            log.error("the error occurred while decoding response body", e);
            error = ErrorResponse.of(
                    response.status(),
                    "Неизвестная ошибка сервера"
            );
        }
        return new ApplicationException(error);
    }

}
