package ru.egormit.service2.error.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import ru.egormit.service2.error.ApplicationException;
import ru.egormit.service2.error.model.ApplicationError;

import java.io.IOException;

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
        ApplicationError error;
        try {
            error = objectMapper.readValue(body, ApplicationError.class);
        } catch (Exception e) {
            log.error("the error occurred while decoding response body", e);
            error = new ApplicationError(
                    "Неизвестная ошибка сервера",
                    HttpStatus.valueOf(response.status())
            );
        }
        return new ApplicationException(error);
    }

}
