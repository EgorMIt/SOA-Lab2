package com.ervelus.marineservice.service.impl;

import com.ervelus.marineservice.repository.SpaceMarineSearchRepository;
import com.ervelus.marineservice.service.SpaceMarineSearchService;
import ru.egormit.library.Coordinates;
import ru.egormit.library.SpaceMarine;
import ru.egormit.library.SpaceMarineFilterRequest;
import ru.egormit.library.SpaceMarineResponse;
import ru.egormit.library.SpaceMarineSearchResponse;
import ru.egormit.library.enums.SortByType;
import ru.egormit.library.enums.SortOrder;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Stateless
public class SpaceMarineSearchServiceImpl implements SpaceMarineSearchService {
    @Inject
    private SpaceMarineSearchRepository repository;

    @Override
    public SpaceMarineSearchResponse findAllSpaceMarineByFilter(SpaceMarineFilterRequest request) {
        HashMap<String, String> fieldToVal = new HashMap<>();
        if (request.getName() != null) fieldToVal.put("name", request.getName());
        if (request.getCoordinates() != null) {
            fieldToVal.put("coordinate_x", request.getCoordinates().getX().toString());
            fieldToVal.put("coordinate_y", request.getCoordinates().getY().toString());
        }
        if (request.getCreationDate() != null) fieldToVal.put("creation_date", request.getCreationDate().toString());
        if (request.getHealth() != null) fieldToVal.put("health", request.getHealth().toString());
        if (request.getCategory() != null) fieldToVal.put("astartes_category", request.getCategory());
        if (request.getWeaponType() != null) fieldToVal.put("weapon_type", request.getWeaponType());
        if (request.getMeleeWeapon() != null) fieldToVal.put("melee_weapon", request.getMeleeWeapon());
        List<SpaceMarine> marines;
        if (request.getSortBy() != null) {
            marines = (repository.getAllMatchingFieldsSortedPageable(
                    fieldToVal,
                    SortByType.valueOf(request.getSortBy()),
                    SortOrder.valueOf(request.getOrder().toUpperCase()),
                    request.getPage(),
                    request.getLimit()));
        } else marines = repository.getAllMatchingFieldsPageable(fieldToVal, request.getPage(), request.getLimit());
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
            marineResponse.setStarShipId(marine.getStarShip().getId());
            responseList.add(marineResponse);
        }
        return SpaceMarineSearchResponse.of(responseList);
    }

    @Override
    public SpaceMarineSearchResponse findAllSpaceMarineByName(SpaceMarineFilterRequest request) {
        List<SpaceMarine> marines = repository.getAllByNamePageable(request.getName(), request.getPage(), request.getLimit());
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
            marineResponse.setStarShipId(marine.getStarShip().getId());
            responseList.add(marineResponse);
        }
        return SpaceMarineSearchResponse.of(responseList);
    }

    @Override
    public SpaceMarineSearchResponse findAllSpaceMarineWithHealthGreaterThan(SpaceMarineFilterRequest request) {
        List<SpaceMarine> marines = repository.getAllByHealthGreaterPageable(request.getHealth(), request.getPage(), request.getLimit());
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
            marineResponse.setStarShipId(marine.getStarShip().getId());
            responseList.add(marineResponse);
        }
        return SpaceMarineSearchResponse.of(responseList);
    }
}
