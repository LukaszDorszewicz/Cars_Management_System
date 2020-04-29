package com.app.menu;

import com.app.exception.CarsManagementSystemException;
import com.app.model.enums.CarComponent;
import com.app.service.StatisticsService;
import com.app.service.UserDataService;

import javax.swing.*;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.List;

public class Menu {

    public void carStatisticsMenu() {
        var numberOfCars = inputNumberOfCarsToGenerate();
        var statisticsService = new StatisticsService(numberOfCars);

        do {
            try {
                showOptions();
                int input;
                switch (UserDataService.getInteger()) {
                    case 1:
                        System.out.println(statisticsService.getCarListSize());
                        break;
                    case 2:
                        System.out.println(statisticsService.getAllCars());
                        break;
                    case 3:
                        System.out.println("PRICE - 1\nBRAND - 2\nCOLOUR - 3\nCARBODY TYPE - 4\nPUT OPTION:");
                        input = UserDataService.getInteger();
                        System.out.println("DESCENDING - 0\nPUT OPTION:");
                        var descending = UserDataService.getInteger() == 0;

                        System.out.println(statisticsService.getCarsSortedByParameter(input, descending));
                        break;
                    case 4:
                        input = UserDataService.getInteger();
                        System.out.println(statisticsService.getCarsWithBiggerMileage(input));
                        break;
                    case 5:
                        System.out.println(statisticsService.countCarsAfterColors());
                        break;
                    case 6:
                        statisticsService.getMostExpensiveCarsFromBrands()
                                .forEach((k, v) -> System.out.println(k + "\n" + v));
                        break;
                    case 7:
                        System.out.println("PUT LOWER RANGE:");
                        var lowerRange = UserDataService.getBigDecimal();
                        System.out.println("PUT UPPER RANGE:");
                        var upperRange = UserDataService.getBigDecimal();

                        System.out.println(statisticsService.getCarsAfterPriceRange(lowerRange, upperRange));
                        break;
                    case 8:
                        List.of(CarComponent.values()).forEach(k -> System.out.println(k + " - " + k.ordinal()));
                        System.out.println("PUT NUMBER OF COMPONENT:");
                        var ordinalNumber = UserDataService.getInteger();
                        var component = CarComponent.values()[ordinalNumber];

                        System.out.println(statisticsService.getCarsWithComponent(component));
                        break;
                    case 9:
                        List.of(CarComponent.values()).forEach(k -> System.out.println(k + " - " + k.ordinal()));
                        System.out.println("PUT NUMBERS OF COMPONENTS SEPARATED BY COMMAS (FE. 1,3,4)");
                        var numbers = UserDataService.getString();
                        var components = CarComponent.getComponentsFromStringLine(numbers);

                        System.out.println(statisticsService.getCarsWithComponents(components));
                        break;
                    case 10:
                        var priceStatistics = statisticsService.getPriceStatistics();

                        System.out.println("MAX PRICE: " + priceStatistics.getMax()
                                + "\nMIN PRICE: " + priceStatistics.getMin()
                                + "\nAVG PRICE: " + priceStatistics.getAverage().setScale(2, RoundingMode.HALF_EVEN));
                        break;
                    case 11:
                        var mileageStatistics = statisticsService.getMileageStatistics();

                        System.out.println("MAX PRICE: " + mileageStatistics.getMax()
                                + "\nMIN PRICE: " + mileageStatistics.getMin()
                                + "\nAVG PRICE: " + mileageStatistics.getAverage());
                        break;
                    case 12:
                        System.out.println(statisticsService.getMostExpensiveCar());
                        break;
                    case 13:
                        System.out.println(statisticsService.getCarsWithSortedComponents());
                    case 14:
                        System.out.println("THANKS FOR USING!!!!!!");
                        return;
                }
            } catch (CarsManagementSystemException e) {
                System.out.println("-------------------------------- EXCEPTIONS -----------------------------");
                System.out.println(e.getMessage());
                System.out.println("-------------------------------------------------------------------------");
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println("INVALID INPUT");
            }
        }
        while (true);
    }

    private void showOptions() {
        System.out.println("MENU\n" +
                "\nGET NUMBER OF CARS AFTER VALIDATION - 1 " +
                "\nGET ALL CARS - 2" +
                "\nGET CARS SORTED BY - 3" +
                "\nGET CARS WITH BIGGER MILEAGE THAN INPUT - 4" +
                "\nCOUNT CARS AFTER COLOURS - 5" +
                "\nGET MOST EXPENSIVE CARS FROM BRANDS - 6" +
                "\nGET CARS AFTER PRICE RANGE - 7" +
                "\nGET CARS WITH GIVEN COMPONENT - 8" +
                "\nGET CARS WITH GIVEN COMPONENTS - 9" +
                "\nGET PRICE STATISTICS - 10" +
                "\nGET MILEAGE STATISTICS - 11" +
                "\nGET MOST EXPENSIVE CAR - 12" +
                "\nGET CARS WITH COMPONENTS SORTED ALPHABETICALLY - 13" +
                "\nPUT OPTION:");
    }

    private int inputNumberOfCarsToGenerate() {
        int numberOfCarsToGenerate = 0;

        do {
            try {
                numberOfCarsToGenerate = Integer.parseInt(JOptionPane.showInputDialog("HOW MANY CARS DO YOU WANT TO GENERATE"));
            } catch (IllegalArgumentException e) {
                System.out.println("INVALID INPUT");
            }
        }
        while (numberOfCarsToGenerate <= 0);
        return numberOfCarsToGenerate;
    }
}

