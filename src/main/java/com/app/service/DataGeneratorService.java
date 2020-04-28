package com.app.service;

import com.app.converter.CarJsonConverter;
import com.app.model.Car;
import com.app.model.CarBody;
import com.app.model.Engine;
import com.app.model.Wheel;
import com.app.model.enums.*;
import lombok.Data;
import org.decimal4j.util.DoubleRounder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class DataGeneratorService {
    private final Random random = new Random();
    private final List<CarBrand> carBrand = List.of(CarBrand.values());
    private final List<CarColour> carColour = List.of(CarColour.values());
    private final List<CarComponent> carComponent = List.of(CarComponent.values());
    private final List<EngineType> engineType = List.of(EngineType.values());
    private final List<TireType> tireType = List.of(TireType.values());
    private final List<CarBodyType> carBodyType = List.of(CarBodyType.values());
    private final List<CarTireBrand> carTireBrand = List.of(CarTireBrand.values());
    private final String fileName = "cars.json";

    public BigDecimal getRandomCarPrice() {
        return new BigDecimal(random.nextInt(700000));
    }

    public int getRandomMileage() {
        return random.nextInt(210000);
    }

    public double getRandomEnginePower() {
        return DoubleRounder.round(1.5 + new Random().nextDouble() * (4.5), 1);
    }

    public int getRandomTireSize() {
        return random.nextInt(7) + 14;
    }

    public CarBrand getRandomCarBrand() {
        return carBrand.get(random.nextInt(carBrand.size()));
    }

    public CarColour getRandomCarColour() {
        return carColour.get(random.nextInt(carColour.size()));
    }

    public CarComponent getRandomCarComponent() {
        return carComponent.get(random.nextInt(carComponent.size()));
    }

    public EngineType getRandomEngineType() {
        return engineType.get(random.nextInt(engineType.size()));
    }

    public TireType getRandomTireType() {
        return tireType.get(random.nextInt(tireType.size()));
    }

    public CarBodyType getRandomCarBodyType() {
        return carBodyType.get(random.nextInt(carBodyType.size()));
    }

    public CarTireBrand getRandomCarTireBrand() {
        return carTireBrand.get(random.nextInt(carTireBrand.size()));
    }

    public List<CarComponent> getRandomCarComponents() {
        var components = new ArrayList<CarComponent>();

        for (int i = 0; i < random.nextInt(carComponent.size()); i++) {
            components.add(getRandomCarComponent());
        }
        return components;
    }

    public Car generateCar() {
        return Car.builder()
                .price(getRandomCarPrice())
                .brand(getRandomCarBrand())
                .mileage(getRandomMileage())
                .engine(Engine.builder()
                        .type(getRandomEngineType())
                        .power(getRandomEnginePower())
                        .build())
                .carBody(CarBody.builder()
                        .colour(getRandomCarColour())
                        .bodyType(getRandomCarBodyType())
                        .components(getRandomCarComponents())
                        .build())
                .wheel(Wheel.builder()
                        .brand(getRandomCarTireBrand())
                        .type(getRandomTireType())
                        .size(getRandomTireSize())
                        .build())
                .build();
    }

    public List<Car> generateCars(int numberOfCars) {
        var cars = new ArrayList<Car>();
        for (int i = 0; i < numberOfCars; i++) {
            cars.add(generateCar());
        }
        return cars;
    }

    public String saveToJson(List<Car> carList) {
        var carsFilename = fileName;
        var carJsonConverter = new CarJsonConverter(carsFilename);
        carJsonConverter.toJson(carList);

        return carsFilename;
    }
}
