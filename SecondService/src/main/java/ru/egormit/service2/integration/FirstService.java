package ru.egormit.service2.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.egormit.library.SpaceMarineResponse;

/**
 * Запросы к первому сервису.
 *
 * @author Egor Mitrofanov.
 */
@FeignClient(name = "first-service", url = "${service.first-service.url}")
public interface FirstService {

    /**
     * Получение Spacemarine
     *
     * @param id идентификатор
     * @return модель Spacemarine
     */
    @GetMapping(EndpointsFirstService.GET_SPACEMARINE)
    SpaceMarineResponse getSpacemarine(@PathVariable Long id);

    /**
     * Изменение Spacemarine
     *
     * @param id             идентификатор
     * @param spaceMarineDto новая модель
     */
    @PutMapping(EndpointsFirstService.UPDATE_SPACEMARINE)
    void updateSpacemarine(@PathVariable Long id, @RequestBody SpaceMarineResponse spaceMarineDto);

}
