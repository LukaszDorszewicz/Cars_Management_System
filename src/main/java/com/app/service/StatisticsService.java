package com.app.service;

import com.app.exception.CarsManagementSystemException;
import com.app.model.Car;
import com.app.model.enums.*;
import lombok.Data;
import org.eclipse.collections.impl.collector.BigDecimalSummaryStatistics;
import org.eclipse.collections.impl.collector.Collectors2;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
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
                        .sorted(Comparator.comparing(c -> c.getBrand().toString()));
                break;
            case 3:
                carStream = carService.getCars()
                        .stream()
                        .sorted(Comparator.comparing(c -> c.getCarBody().getColour().toString()));
                break;
            case 4:
                carStream = carService.getCars()
                        .stream()
                        .sorted(Comparator.comparing(c -> c.getCarBody().getBodyType().toString()));
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
                                .orElseThrow(() ->
                                        new CarsManagementSystemException("CANNOT FIND MOST EXPENSIVE CARS")))
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

    public List<Car> getCarsWithComponent(CarComponent component) {
        return carService.getCars()
                .stream()
                .filter(c -> c.getCarBody().getComponents().contains(component))
                .sorted(Comparator.comparing(Car::getBrand))
                .collect(Collectors.toList());
    }

    public List<Car> getCarsWithComponents(Set<CarComponent> components) {
        return carService.getCars()
                .stream()
                .filter(c -> c.getCarBody().getComponents().containsAll(components))
                .sorted(Comparator.comparing(Car::getBrand))
                .collect(Collectors.toList());
    }

    public BigDecimalSummaryStatistics getPriceStatistics() {
        return carService.getCars()
                .stream()
                .collect(Collectors2.summarizingBigDecimal(Car::getPrice));
    }

    public IntSummaryStatistics getMileageStatistics() {
        return carService.getCars()
                .stream()
                .collect(Collectors.summarizingInt(Car::getMileage));
    }

    public Car getMostExpensiveCar() {
        return carService.getCars()
                .stream()
                .sorted(Comparator.comparing(Car::getPrice).reversed())
                .limit(1)
                .findAny()
                .orElseThrow(() -> new CarsManagementSystemException("NO CARS AVAILABLE"));
    }

    public List<Car> getCarsWithSortedComponents() {
        return carService.getCars()
                .stream()
                .peek(c -> c.getCarBody().setComponents(c.getCarBody().getComponents()
                        .stream()
                        .sorted(Comparator.comparing(Object::toString))
                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public Map<Integer, Car> getCarsWithTheirMileages() {
        return carService.getCars()
                .stream()
                .collect(Collectors.toMap(
                        Car::getMileage,
                        Function.identity()
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                ));
    }

    public Map<TireType, List<Car>> getCarsWithSpecifiedTireTypes() {
        return carService.getCars()
                .stream()
                .collect(Collectors.groupingBy(c -> c.getWheel().getType()));
    }

    public Map<CarBodyType, List<Car>> getCarsWithSpecifiedBodyTypes() {
        return carService.getCars()
                .stream()
                .collect(Collectors.groupingBy(c -> c.getCarBody().getBodyType()));
    }

    public Map<EngineType, List<Car>> getCarsWithSpecifiedEngineTypes() {
        return carService.getCars()
                .stream()
                .collect(Collectors.groupingBy(c -> c.getEngine().getType()));
    }
}
