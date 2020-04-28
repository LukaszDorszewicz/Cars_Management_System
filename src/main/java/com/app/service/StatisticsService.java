package com.app.service;

import com.app.exception.CarsManagementSystemException;
import com.app.model.Car;
import com.app.model.enums.CarBrand;
import com.app.model.enums.CarColour;
import com.app.model.enums.CarComponent;
import lombok.Data;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

@Data
public class StatisticsService {
    private CarService carService;

    public StatisticsService(int numberOfCars) {
        this.carService = new CarService(numberOfCars);
    }

    public int getCarListSize() {
        return carService.getCars()
                .size();
    }

    public String getAllCars() {
        return carService.getCars()
                .stream()
                .map(Car::toString)
                .collect(Collectors.joining("\n"));
    }

    public List<Car> getCarsSortedByParameter(int input, boolean descending) {

        if (input > 4 || input < 1) {
            throw new CarsManagementSystemException("INPUT OUTSIDE OF RANGE");
        }

        Stream<Car> carStream = null;

        switch (input) {
            case 1:
                carStream = carService.getCars()
                        .stream()
                        .sorted(Comparator.comparing(Car::getPrice));
                break;
            case 2:
                carStream = carService.getCars()
                        .stream()
                        .sorted(Comparator.comparing(Car::getBrand));
                break;
            case 3:
                carStream = carService.getCars()
                        .stream()
                        .sorted(Comparator.comparing(c -> c.getCarBody().getColour()));
                break;
            case 4:
                carStream = carService.getCars()
                        .stream()
                        .sorted(Comparator.comparing(c -> c.getCarBody().getBodyType()));
                break;
        }

        var carList = carStream
                .collect(Collectors.toList());

        if (descending) {
            Collections.reverse(carList);
        }
        return carList;
    }

    public List<Car> getCarsWithBiggerMileage(int mileage) {
        return carService.getCars()
                .stream()
                .filter(c -> c.getMileage() > mileage)
                .sorted(Comparator.comparing(Car::getMileage))
                .collect(Collectors.toList());
    }

    public Map<CarColour, Long> countCarsAfterColors() {
        return carService.getCars()
                .stream()
                .collect(Collectors.groupingBy(c -> c.getCarBody().getColour(), Collectors.counting()));
    }

    public Map<CarBrand, Car> getMostExpensiveCarsFromBrands() {
        return carService.getCars()
                .stream()
                .collect(Collectors.groupingBy(Car::getBrand))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        v -> v.getValue().stream().max(comparing(Car::getPrice))
                                .orElseThrow(() -> new CarsManagementSystemException("CANNOT FIND MOST EXPENSIVE CARS")))
                );
    }

    public List<Car> getCarsAfterPriceRange(BigDecimal lowerRange, BigDecimal upperRange) {

        if (lowerRange.compareTo(upperRange) >= 0) {
            throw new CarsManagementSystemException("INVALID RANGE");
        }

        return carService.getCars()
                .stream()
                .filter(c -> c.getPrice().compareTo(upperRange) <= 0
                        && c.getPrice().compareTo(lowerRange) >= 0)
                .sorted(comparing(Car::getPrice))
                .collect(Collectors.toList());
    }

    public List<Car> getCarsWithComponent(CarComponent component){
        return carService.getCars()
                .stream()
                .filter(c -> c.getCarBody().getComponents().contains(component))
                .sorted(Comparator.comparing(Car::getBrand))
                .collect(Collectors.toList());
    }

    public List<Car> getCarsWithComponents(Set<CarComponent> components){
        return carService.getCars()
                .stream()
                .filter(c -> c.getCarBody().getComponents().containsAll(components))
                .sorted(Comparator.comparing(Car::getBrand))
                .collect(Collectors.toList());
    }
}
