package com.app.model;

import com.app.model.enums.CarBrand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private CarBrand brand;
    private BigDecimal price;
    private int mileage;
    private Engine engine;
    private CarBody carBody;
    private Wheel wheel;

    @Override
    public String toString() {
        return "CAR" +
                "\nbrand=" + brand +
                "\nprice=" + price +
                "\nmileage=" + mileage +
                "\nengine=" + engine +
                "\ncarBody=" + carBody +
                "\nwheel=" + wheel +
                "\n";
    }
}
