package com.app.model;

import com.app.model.enums.CarTireBrand;
import com.app.model.enums.TireType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wheel {
    private CarTireBrand brand;
    private int size;
    private TireType type;

    @Override
    public String toString() {
        return "(brand=" + brand +
                ", size=" + size +
                ", type=" + type + ')';
    }
}
