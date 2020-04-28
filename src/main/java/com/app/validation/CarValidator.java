package com.app.validation;

import com.app.model.Car;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CarValidator implements Validator<Car> {
    private Map<String, String> errors = new HashMap<>();

    @Override
    public Map<String, String> validate(Car car) {
        errors.clear();

        if (car == null) {
            errors.put("car", "null");
        } else {
            if (!isNameValid(car)) {
                errors.put("brand", "invalid (only big letters): " + car.getBrand());
            }
            if (!isMileageValid(car)) {
                errors.put("mileage", "invalid (from 0 to 200000): " + car.getMileage());
            }
            if (!isPriceValid(car)) {
                errors.put("price", "invalid (from 50000 to 650000): " + car.getPrice());
            }
            if (!areComponentsValid(car)) {
                errors.put("components", "invalid (duplicates in list): " + car.getCarBody().getComponents());
            }
            if (!isColourVaild(car)) {
                errors.put("colour", "invalid (only big letters): " + car.getCarBody().getColour());
            }
            if (!isEnginePowerValid(car)) {
                errors.put("engine power", "invalid (from 1.6 to 5.5): " + car.getEngine().getPower());
            }
            if (!isTireSizeValid(car)) {
                errors.put("tire size", "invalid (from 15 to 19): " + car.getWheel().getSize());
            }
        }
        return errors;
    }

    @Override
    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    private boolean isNameValid(Car car) {
        return car.getBrand().toString().matches("[A-Z]+");
    }

    private boolean isMileageValid(Car car) {
        return car.getMileage() >= 0 && car.getMileage() <= 200000;
    }

    private boolean isEnginePowerValid(Car car) {
        return car.getEngine().getPower() >= 1.6
                && car.getEngine().getPower() <= 5.5;
    }

    private boolean isTireSizeValid(Car car) {
        return car.getWheel().getSize() >= 15 && car.getWheel().getSize() <= 19;
    }


    private boolean isPriceValid(Car car) {
        return car.getPrice().compareTo(new BigDecimal("50000")) >= 0
                && car.getPrice().compareTo(new BigDecimal("650000")) <= 0;
    }

    private boolean areComponentsValid(Car car) {
        return new HashSet<>(car.getCarBody().getComponents()).size()
                == car.getCarBody().getComponents().size();
    }

    private boolean isColourVaild(Car car) {
        return car.getCarBody().getColour().toString().matches("\\w+");
    }
}
