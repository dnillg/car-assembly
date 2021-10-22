package com.dnillg.carassembly.domain.car;

import com.dnillg.carassembly.domain.car.painting.CarPainting;

public class Car {

    private Engine engine;
    private CarPainting painting;
    private int numberOfSeats;

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

}
