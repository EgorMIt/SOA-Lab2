package ru.egormit.service2.service;


import ru.egormit.library.StarShipDto;
import ru.egormit.library.StarShipRequest;

import java.util.List;

/**
 * Интерфейс сервиса работы с starship.
 *
 * @author Egor Mitrofanov.
 */
public interface StarshipService {

    /**
     * Создане корабля
     *
     * @param request модель корабля
     */
    void createStarship(StarShipRequest request);

    /**
     * Получение списка кораблей
     *
     * @return список всех кораблей
     */
    List<StarShipDto> getAllStarships();

    /**
     * Добавление десантника к кораблю
     *
     * @param spaceMarineId идентификатор десантника
     * @param starShipId    идентификатор корабля
     */
    void addMarineToStarship(Long spaceMarineId, Long starShipId);

    /**
     * Выгрузка всех десантников с корабля
     *
     * @param starShipId идентификатор корабля
     */
    void cleanStarship(Long starShipId);

}
