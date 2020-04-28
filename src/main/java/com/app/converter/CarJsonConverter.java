package com.app.converter;

import com.app.model.Car;
import java.util.List;

public class CarJsonConverter extends JsonConverter<List<Car>> {
    public CarJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
