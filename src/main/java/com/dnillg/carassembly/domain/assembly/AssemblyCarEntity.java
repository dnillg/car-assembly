package com.dnillg.carassembly.domain.assembly;

import com.dnillg.carassembly.domain.car.Car;
import com.dnillg.carassembly.domain.car.painting.CarColor;
import com.dnillg.carassembly.domain.car.painting.CarPainting;
import com.dnillg.carassembly.domain.car.Engine;
import com.dnillg.carassembly.domain.car.painting.PaintingFinish;
import com.dnillg.carassembly.domain.exception.AssemblyPreconditionException;

import java.util.Objects;

public class AssemblyCarEntity {

    private Engine engine;
    private CarPainting painting;
    private int numberOfSeats;

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

    public void polish() {
        if (Objects.isNull(painting)) {
            throw new AssemblyPreconditionException("Painting has to be applied before polish.");
        }
        painting = painting.polished();
    }

    public Engine getEngine() {
        return engine;
    }

    public CarPainting getPainting() {
        return painting;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public Car build() {
        return new Car(engine, painting, numberOfSeats);
    }
}
