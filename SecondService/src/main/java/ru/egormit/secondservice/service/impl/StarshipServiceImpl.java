package ru.egormit.secondservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import ru.egormit.library.SpaceMarineResponse;
import ru.egormit.library.StarShipDto;
import ru.egormit.library.StarShipRequest;
import ru.egormit.library.SpaceMarine;
import ru.egormit.library.StarShip;
import ru.egormit.secondservice.domain.StarshipRepository;
import ru.egormit.secondservice.error.ErrorDescriptions;
import ru.egormit.secondservice.integration.FirstService;
import ru.egormit.secondservice.service.StarshipService;
import ru.egormit.secondservice.utils.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса работы с starship.
 *
 * @author Egor Mitrofanov.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StarshipServiceImpl implements StarshipService {

    /**
     * {@link FirstService}.
     */
    private final FirstService firstService;

    /**
     * {@link StarshipRepository}.
     */
    private final StarshipRepository starshipRepository;

    /**
     * {@link ModelMapper}.
     */
    private final ModelMapper modelMapper;

    /**
     * Создание корабля
     *
     * @param request модель корабля
     */
    @Override
    public void createStarship(StarShipRequest request) {
        StarShip starShip = new StarShip();
        starShip.setName(request.getName());
        starShip.setFleet(request.getFleet());
        starShip.setCoordinateX(request.getCoordinates().getX());
        starShip.setCoordinateY(request.getCoordinates().getY());

        starshipRepository.save(starShip);
    }

    /**
     * Получение списка кораблей
     *
     * @return список всех кораблей
     */
    @Override
    public List<StarShipDto> getAllStarships() {
        return starshipRepository.findAll()
                .stream()
                .map(modelMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    /**
     * Добавление десантника к кораблю
     *
     * @param spaceMarineId идентификатор десантника
     * @param starShipId    идентификатор корабля
     */
    @Override
    public void addMarineToStarship(Long spaceMarineId, Long starShipId) {
        SpaceMarineResponse spaceMarine = firstService.getSpacemarine(spaceMarineId);
        ErrorDescriptions.SPACEMACS_IS_BUSY.throwIfFalse(ObjectUtils.isEmpty(spaceMarine.getStarShipId()));

        StarShip starShip = starshipRepository.findById(starShipId)
                .orElseThrow(ErrorDescriptions.STARSHIP_NOT_FOUND::exception);

        spaceMarine.setStarShipId(starShip.getId());

        firstService.updateSpacemarine(spaceMarineId, spaceMarine);
    }

    /**
     * Выгрузка всех десантников с корабля
     *
     * @param starShipId идентификатор корабля
     */
    @Override
    public void cleanStarship(Long starShipId) {
        StarShip starShip = starshipRepository.findById(starShipId)
                .orElseThrow(ErrorDescriptions.STARSHIP_NOT_FOUND::exception);

        ErrorDescriptions.STARSHIP_IS_EMPTY.throwIfTrue(starShip.getSpaceMarines().isEmpty());

        for (SpaceMarine spaceMarine : starShip.getSpaceMarines()) {
            SpaceMarineResponse spaceMarineDto = firstService.getSpacemarine(spaceMarine.getId());
            spaceMarineDto.setStarShipId(null);
            firstService.updateSpacemarine(spaceMarineDto.getId(), spaceMarineDto);
        }
    }

}
