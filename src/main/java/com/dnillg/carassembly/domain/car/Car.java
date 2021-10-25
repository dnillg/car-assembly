package com.dnillg.carassembly.domain.car;

import com.dnillg.carassembly.domain.car.painting.CarPainting;

import java.util.Objects;

public class Car {

    private final Engine engine;
    private final CarPainting painting;
    private final int numberOfSeats;

    public Car(Engine engine, CarPainting painting, int numberOfSeats) {
        this.engine = engine;
        this.painting = painting;
        this.numberOfSeats = numberOfSeats;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return numberOfSeats == car.numberOfSeats &&
            Objects.equals(engine, car.engine) &&
            Objects.equals(painting, car.painting);
    }

    @Override
    public int hashCode() {
        return Objects.hash(engine, painting, numberOfSeats);
    }
}
