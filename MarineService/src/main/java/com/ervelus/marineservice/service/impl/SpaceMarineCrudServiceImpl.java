package com.ervelus.marineservice.service.impl;

import com.ervelus.marineservice.repository.SpaceMarineCrudRepository;
import com.ervelus.marineservice.service.SpaceMarineCrudService;
import ru.egormit.library.Coordinates;
import ru.egormit.library.PageDto;
import ru.egormit.library.SpaceMarine;
import ru.egormit.library.SpaceMarineCreateRequest;
import ru.egormit.library.SpaceMarineResponse;
import ru.egormit.library.SpaceMarineSearchResponse;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SpaceMarineCrudServiceImpl implements SpaceMarineCrudService {
    @Inject
    private SpaceMarineCrudRepository repository;

    @Override
    public void createSpaceMarine(SpaceMarineCreateRequest request) {
        SpaceMarine spaceMarine = new SpaceMarine();
        spaceMarine.setName(request.getName());
        spaceMarine.setCoordinateX(request.getCoordinates().getX());
        spaceMarine.setCoordinateY(request.getCoordinates().getY());
        spaceMarine.setHealth(request.getHealth());
        spaceMarine.setCategory(request.getCategory());
        spaceMarine.setWeaponType(request.getWeaponType());
        spaceMarine.setMeleeWeapon(request.getMeleeWeapon());
        repository.save(spaceMarine);
    }

    @Override
    public void updateSpaceMarine(Long id, SpaceMarineCreateRequest request) {
        SpaceMarine spaceMarine = repository.getById(id);
        spaceMarine.setName(request.getName());
        spaceMarine.setCoordinateX(request.getCoordinates().getX());
        spaceMarine.setCoordinateY(request.getCoordinates().getY());
        spaceMarine.setHealth(request.getHealth());
        spaceMarine.setCategory(request.getCategory());
        spaceMarine.setWeaponType(request.getWeaponType());
        spaceMarine.setMeleeWeapon(request.getMeleeWeapon());
        repository.save(spaceMarine);
    }

    @Override
    public SpaceMarineSearchResponse getAllSpaceMarines(PageDto pageDto) {
        List<SpaceMarine> marines = repository.getAllPageable(pageDto.getPage(), pageDto.getLimit());
        List<SpaceMarineResponse> responseList = new ArrayList<>();
        for (SpaceMarine marine : marines) {
            SpaceMarineResponse marineResponse = new SpaceMarineResponse();
            marineResponse.setId(marine.getId());
            marineResponse.setName(marine.getName());
            marineResponse.setCoordinates(Coordinates.of(marine.getCoordinateX(), marine.getCoordinateY()));
            marineResponse.setHealth(marine.getHealth());
            marineResponse.setCategory(marine.getCategory());
            marineResponse.setCreationDate(marine.getCreationDate());
            marineResponse.setWeaponType(marine.getWeaponType());
            marineResponse.setMeleeWeapon(marine.getMeleeWeapon());
            //marineResponse.setStarShipId(marine.getStarShip().getId());
            responseList.add(marineResponse);
        }
        return SpaceMarineSearchResponse.of(responseList);
    }

    @Override
    public SpaceMarineResponse getSpaceMarineById(Long id) {
        SpaceMarine spaceMarine = repository.getById(id);
        SpaceMarineResponse response = new SpaceMarineResponse();
        response.setId(spaceMarine.getId());
        response.setName(spaceMarine.getName());
        response.setCoordinates(Coordinates.of(spaceMarine.getCoordinateX(), spaceMarine.getCoordinateY()));
        response.setHealth(spaceMarine.getHealth());
        response.setCategory(spaceMarine.getCategory());
        response.setCreationDate(spaceMarine.getCreationDate());
        response.setWeaponType(spaceMarine.getWeaponType());
        response.setMeleeWeapon(spaceMarine.getMeleeWeapon());
        response.setStarShipId(spaceMarine.getStarShip().getId());
        return response;
    }

    @Override
    public void deleteSpaceMarine(Long id) {
        repository.deleteById(id);
    }
}
