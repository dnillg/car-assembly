package com.dnillg.carassembly.domain.car.painting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarPaintingTest {

    @Test
    void testPolishWhenNotPolishedThenFinishGetsGlossy() {
        final CarPainting original = new CarPainting(CarColor.GUNMETAL, PaintingFinish.MATTE);

        final CarPainting result = original.polished();

        assertEquals(original.getColor(), result.getColor());
        assertEquals(PaintingFinish.GLOSSY, result.getFinish());
    }

    @Test
    void testPolishWhenPolishedAlreadyThenFinishStaysGlossy() {
        final CarPainting original = new CarPainting(CarColor.MIDNIGHT, PaintingFinish.GLOSSY);

        final CarPainting result = original.polished();

        assertEquals(original.getColor(), result.getColor());
        assertEquals(PaintingFinish.GLOSSY, result.getFinish());
    }
}