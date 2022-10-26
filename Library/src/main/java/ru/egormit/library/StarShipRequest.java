package ru.egormit.library;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель запрса на саоздание.
 *
 * @author Egor Mitrofanov.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class StarShipRequest {

    /**
     * Имя.
     */
    private String name;

    /**
     * Флотн.
     */
    private String fleet;

    /**
     * Координаты.
     */
    private Coordinates coordinates;

}
