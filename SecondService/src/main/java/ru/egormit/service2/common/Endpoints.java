package ru.egormit.service2.common;

/**
 * Список эндпоинтов второго сервиса.
 *
 * @author Egor Mitrofanov.
 */
public interface Endpoints {
    String CREATE_STARSHIP = "/starships";
    String GET_STARSHIPS = "/starships";
    String ADD_MARINE_TO_STARSHIP = "/starships/{starship-id}/load/{spacemarine-id}";
    String CLEAN_STARSHIP = "/starships/{id}/unload";
}
