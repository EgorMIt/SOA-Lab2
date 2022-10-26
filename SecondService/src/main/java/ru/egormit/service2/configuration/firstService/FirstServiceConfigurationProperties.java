package ru.egormit.service2.configuration.firstService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;

/**
 * Настройки подключения к первому сервису.
 *
 * @author Egor Mitrofanov.
 */
@Data
@Slf4j
@Validated
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "service.service-one")
public class FirstServiceConfigurationProperties {

    /**
     * Адрес сервиса опросов.
     */
    private String url;

    /**
     * Таймаут.
     */
    private Integer timeout;

    /**
     * Инициализация
     */
    @PostConstruct
    public void postConstruct() {
        log.info(" ::: {} initialized :::\n\t\turl: {}\n\t\ttimeout : {}", this.getClass().getSimpleName(), url,
                timeout);
    }
}
