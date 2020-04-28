package com.app.model;

import com.app.model.enums.EngineType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Engine {
    private EngineType type;
    private double power;

    @Override
    public String toString() {
        return "(type=" + type +
                ", power=" + power + ')';
    }
}

