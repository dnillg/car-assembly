package com.dnillg.carassembly.domain.assembly;

import com.dnillg.carassembly.domain.car.Car;
import com.dnillg.carassembly.domain.exception.AssemblyPreconditionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssemblyCarEntityTest {

    @Test
    void testPaintWhenInvokedThenItIsSet() {
        final Car result = new AssemblyCarEntity().paint().build();

        assertNotNull(result.getPainting());
    }

    @Test
    void testAssemblyInteriorWhenInvokedThenItIsSet() {
        final Car result = new AssemblyCarEntity().assemblyInterior().build();

        assertNotNull(result.getNumberOfSeats());
    }

    @Test
    void testAssemblyMechanichWhenInvokedThenItIsSet() {
        final Car result = new AssemblyCarEntity().assemblyMechanich().build();

        assertNotNull(result.getEngine());
    }

    @Test
    void testPolishWhenInvokedBeforePaintingThenExceptionIsThrown() {
        assertThrows(AssemblyPreconditionException.class, () -> new AssemblyCarEntity().polish().build());
    }

    @Test
    void testIdIsAutogeneratedOnCreation() {
        assertNotEquals(new AssemblyCarEntity().getId(), new AssemblyCarEntity().getId());
    }

}