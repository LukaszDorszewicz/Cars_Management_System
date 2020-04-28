package com.app.service;

import com.app.model.Car;
import lombok.Data;

import java.util.List;

@Data
public class CarService {
    private List<Car> cars;
    private final DataGeneratorService dataGeneratorService = new DataGeneratorService();
    private final ValidationService validationService = new ValidationService();

    public CarService(int numberOfCars) {
        var generatedCars = dataGeneratorService.generateCars(numberOfCars);
        dataGeneratorService.saveToJson(generatedCars);
        cars = validationService.getCarsFromJsonAndValidate(dataGeneratorService.getFileName());
    }
}
