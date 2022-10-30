package com.ervelus.marineservice.service;

import ru.egormit.library.PageDto;
import ru.egormit.library.SpaceMarineCreateRequest;
import ru.egormit.library.SpaceMarineResponse;
import ru.egormit.library.SpaceMarineSearchResponse;

import javax.ejb.Local;

@Local
public interface SpaceMarineCrudService {
    void createSpaceMarine(SpaceMarineCreateRequest request);
    void updateSpaceMarine(Long id, SpaceMarineCreateRequest request);
    SpaceMarineSearchResponse getAllSpaceMarines(PageDto pageDto);
    SpaceMarineResponse getSpaceMarineById(Long id);
    void deleteSpaceMarine(Long id);
}
