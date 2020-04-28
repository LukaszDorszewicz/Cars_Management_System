package com.app.model;

import com.app.model.enums.CarBodyType;
import com.app.model.enums.CarColour;
import com.app.model.enums.CarComponent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarBody {
    private CarColour colour;
    private CarBodyType bodyType;
    private List<CarComponent> components;

    @Override
    public String toString() {
        return "(colour=" + colour +
                ", bodyType=" + bodyType +
                ", components=" + components + ')';
    }
}
