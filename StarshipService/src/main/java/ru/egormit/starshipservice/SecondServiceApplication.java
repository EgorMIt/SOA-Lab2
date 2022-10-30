package ru.egormit.starshipservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Main-класс второго сервиса.
 *
 * @author Egor Mitrofanov.
 */
@SpringBootApplication
@EnableFeignClients
public class SecondServiceApplication {

    /**
     * Тестов не будет.
     *
     * @param args только попробуй что-то передать сюда
     */
    public static void main(String[] args) {
        SpringApplication.run(SecondServiceApplication.class, args);
    }

}
