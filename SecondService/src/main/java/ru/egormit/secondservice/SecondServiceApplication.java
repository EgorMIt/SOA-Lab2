package ru.egormit.secondservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main-класс второго сервиса.
 *
 * @author Egor Mitrofanov.
 */
@SpringBootApplication
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
