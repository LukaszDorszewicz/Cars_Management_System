package com.app.model.enums;

import com.app.exception.CarsManagementSystemException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum CarComponent {
    ELECTRIC_MIRRORS, ELECTRIC_WINDOWS, BACK_DOORS, ALARM,
    FOG_LIGHTS, AIR_CONDITIONING, AUDIO, LEATHER, GPS;

    public static Set<CarComponent> getComponentsFromStringLine(String componentsNumbers) {
        if (!componentsNumbers.matches("([0-9]{1},{1})*[0-9]")) {
            throw new CarsManagementSystemException("INVALID FORMAT OF COMPONENTS NUMBERS");
        }
        var numbers = List.of(componentsNumbers.split(","));

        return numbers
                .stream()
                .map(c -> CarComponent.values()[Integer.parseInt(c)])
                .collect(Collectors.toSet());
    }
}
