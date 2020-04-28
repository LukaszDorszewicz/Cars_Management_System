package com.app.service;

import com.app.converter.CarJsonConverter;
import com.app.exception.CarsManagementSystemException;
import com.app.model.Car;
import com.app.validation.CarValidator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ValidationService {

    public  List<Car> getCarsFromJsonAndValidate(String carsJson) {
        var counter = new AtomicInteger(1);

        return new CarJsonConverter(carsJson)
                .fromJson()
                .orElseThrow(() -> new CarsManagementSystemException("FROM JSON TO CAR LIST PARSE EXCEPTION"))
                .stream()
                .filter(car -> {
                    var carValidator = new CarValidator();
                    Map<String, String> validationResults = carValidator.validate(car);

                    if (carValidator.hasErrors()) {
                        System.out.println("\nCAR VALIDATION ERROR IN CAR NO. " + counter.get() + " " + car.getBrand());
                        validationResults.forEach((k, v) -> System.out.println(k + " " + v));
                    }
                    counter.incrementAndGet();

                    return !carValidator.hasErrors();
                })
                .collect(Collectors.toList());
    }
}
