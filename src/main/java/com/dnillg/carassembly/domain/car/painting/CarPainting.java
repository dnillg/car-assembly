package com.dnillg.carassembly.domain.car.painting;

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
}
