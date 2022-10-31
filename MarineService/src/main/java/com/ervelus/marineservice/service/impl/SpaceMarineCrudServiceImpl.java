package com.ervelus.marineservice.service.impl;

import com.ervelus.marineservice.converter.SpaceMarineConverter;
import com.ervelus.marineservice.repository.SpaceMarineCrudRepository;
import com.ervelus.marineservice.service.SpaceMarineCrudService;
import ru.egormit.library.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SpaceMarineCrudServiceImpl implements SpaceMarineCrudService {
    @Inject
    private SpaceMarineCrudRepository repository;
    @Inject
    private SpaceMarineConverter converter;

    @Override
    public void createSpaceMarine(SpaceMarineCreateRequest request) {
        SpaceMarine spaceMarine = new SpaceMarine();
        ZonedDateTime time = ZonedDateTime.now();
        spaceMarine.setCreationDate(time.with(LocalTime.of(0,0,0,0)));
        repository.save(converter.createRequestToEntity(request, spaceMarine));
    }

    @Override
    public void updateSpaceMarine(Long id, SpaceMarineUpdateRequest request) {
        SpaceMarine spaceMarine = repository.getById(id);
        if (spaceMarine == null) throw new NotFoundException();
        repository.save(converter.updateRequestToEntity(request, spaceMarine));
    }

    @Override
    public SpaceMarineSearchResponse getAllSpaceMarines(PageDto pageDto) {
        List<SpaceMarine> marines = repository.getAllPageable(pageDto.getPage(), pageDto.getLimit());
        List<SpaceMarineResponse> responseList = new ArrayList<>();
        for (SpaceMarine marine : marines) {
            SpaceMarineResponse response = new SpaceMarineResponse();
            responseList.add(converter.entityToResponse(marine, response));
        }
        return SpaceMarineSearchResponse.of(responseList);
    }

    @Override
    public SpaceMarineResponse getSpaceMarineById(Long id) {
        SpaceMarine spaceMarine = repository.getById(id);
        SpaceMarineResponse response = new SpaceMarineResponse();
        return converter.entityToResponse(spaceMarine, response);
    }

    @Override
    public void deleteSpaceMarine(Long id) {
        SpaceMarine spaceMarine = repository.getById(id);
        if (spaceMarine == null) throw new NotFoundException();
        repository.deleteById(id);
    }
}
