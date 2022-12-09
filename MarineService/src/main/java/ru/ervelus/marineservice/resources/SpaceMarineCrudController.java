package ru.ervelus.marineservice.resources;

import ru.ervelus.marineservice.service.SpaceMarineCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.egormit.library.*;

@RestController
@RequestMapping( value = "/spacemarines", produces = "application/json")
@RequiredArgsConstructor
public class SpaceMarineCrudController extends BaseController {
    private final SpaceMarineCrudService crudService;

    @GetMapping
    public ResponseEntity<SpaceMarineSearchResponse> getAllSpaceMarines(@RequestBody PageDto pageDto) {
        return ResponseEntity.ok(crudService.getAllSpaceMarines(pageDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpaceMarineResponse> getSpaceMarine(@PathVariable Long id) {
        return ResponseEntity.ok(crudService.getSpaceMarineById(id));
    }

    @PostMapping
    public ResponseEntity createSpaceMarine(@RequestBody SpaceMarineCreateRequest request) {
        crudService.createSpaceMarine(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSpaceMarine(@PathVariable Long id) {
        crudService.deleteSpaceMarine(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateSpaceMarine(@PathVariable Long id, @RequestBody SpaceMarineUpdateRequest request) {
        crudService.updateSpaceMarine(id, request);
        return ResponseEntity.ok().build();
    }
}