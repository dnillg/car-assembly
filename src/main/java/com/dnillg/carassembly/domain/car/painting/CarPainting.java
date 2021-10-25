package com.dnillg.carassembly.domain.car.painting;

import java.util.Objects;

public class CarPainting {

    private final CarColor color;
    private final PaintingFinish finish;

    public CarPainting(CarColor color, PaintingFinish finish) {
        this.color = color;
        this.finish = finish;
    }

    public CarPainting polished() {
        return new CarPainting(color, PaintingFinish.GLOSSY);
    }

    public CarColor getColor() {
        return color;
    }

    public PaintingFinish getFinish() {
        return finish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarPainting that = (CarPainting) o;
        return color == that.color &&
            finish == that.finish;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, finish);
    }
}
