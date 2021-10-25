package com.dnillg.carassembly.domain.assembly;

import com.dnillg.carassembly.domain.car.Car;
import com.dnillg.carassembly.domain.car.painting.CarColor;
import com.dnillg.carassembly.domain.car.painting.CarPainting;
import com.dnillg.carassembly.domain.car.Engine;
import com.dnillg.carassembly.domain.car.painting.PaintingFinish;
import com.dnillg.carassembly.domain.exception.AssemblyPreconditionException;

import java.util.Objects;
import java.util.UUID;

public class AssemblyCarEntity {

    private final String id;
    private Engine engine;
    private CarPainting painting;
    private int numberOfSeats;

    public AssemblyCarEntity() {
        this.id = UUID.randomUUID().toString();
    }

    public AssemblyCarEntity paint() {
        painting = new CarPainting(CarColor.BLAZING_RED, PaintingFinish.MATTE);
        return this;
    }

    public AssemblyCarEntity assemblyMechanich() {
        engine = new Engine();
        return this;
    }

    public AssemblyCarEntity assemblyInterior() {
        numberOfSeats = 4;
        return this;
    }

    public AssemblyCarEntity polish() {
        if (Objects.isNull(painting)) {
            throw new AssemblyPreconditionException("Painting has to be applied before polish.");
        }
        painting = painting.polished();
        return this;
    }

    public Car build() {
        return new Car(engine, painting, numberOfSeats);
    }

    public String getId() {
        return id;
    }

}
