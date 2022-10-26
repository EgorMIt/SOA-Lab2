package ru.egormit.service2.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий доступа к сущности {@link StarShip}.
 *
 * @author Egor Mitrofanov.
 */
public interface StarshipRepository extends JpaRepository<StarShip, Long> {

}
