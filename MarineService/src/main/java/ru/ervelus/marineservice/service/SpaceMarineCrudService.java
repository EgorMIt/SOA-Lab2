package ru.ervelus.marineservice.service;

import ru.egormit.library.*;

public interface SpaceMarineCrudService {
    void createSpaceMarine(SpaceMarineCreateRequest request);

    void updateSpaceMarine(Long id, SpaceMarineUpdateRequest request);

    SpaceMarineSearchResponse getAllSpaceMarines(PageDto pageDto);

    SpaceMarineResponse getSpaceMarineById(Long id);

    void deleteSpaceMarine(Long id);
}
