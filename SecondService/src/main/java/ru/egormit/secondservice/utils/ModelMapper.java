package ru.egormit.secondservice.utils;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.egormit.library.Coordinates;
import ru.egormit.library.StarShipDto;
import ru.egormit.secondservice.domain.SpaceMarine;
import ru.egormit.secondservice.domain.StarShip;

import java.util.ArrayList;
import java.util.List;

/**
 * Маппер моделей.
 *
 * @author Egor Mitrofanov.
 */
@Component
@RequiredArgsConstructor
public class ModelMapper {

    /**
     * Маппер сущности {@link StarShip} в модель {@link StarShipDto}.
     *
     * @param starShip сущность.
     * @return модель {@link StarShipDto}.
     */
    public StarShipDto mapToUserDto(StarShip starShip) {
        StarShipDto dto = new StarShipDto();
        dto.setId(starShip.getId());
        dto.setName(starShip.getName());
        dto.setFleet(starShip.getFleet());
        long health = 0L;
        long marinesCount = 0L;
        List<Long> spaceMarines = new ArrayList<>();
        for (SpaceMarine spaceMarine : starShip.getSpaceMarines()) {
            spaceMarines.add(spaceMarine.getId());
            health += spaceMarine.getHealth();
            marinesCount++;
        }
        dto.setHealth(health);
        dto.setMarinesCount(marinesCount);
        dto.setSpaceMarines(spaceMarines);
        dto.setCoordinates(Coordinates.of(starShip.getCoordinateX(), starShip.getCoordinateY()));
        return dto;
    }

}
