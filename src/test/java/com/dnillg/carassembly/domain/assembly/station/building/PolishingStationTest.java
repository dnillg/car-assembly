package com.dnillg.carassembly.domain.assembly.station.building;

import com.dnillg.carassembly.domain.assembly.AssemblyCarEntity;
import com.dnillg.carassembly.domain.assembly.station.AssemblyStationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PolishingStationTest {

    private PolishingStation interiorAssemblyStation; //sut

    @BeforeEach
    void setUp() {
        interiorAssemblyStation = new PolishingStation();
    }

    @Test
    void testAcceptInvokesAssemblyInteriorMethod() {
        AssemblyCarEntity assemblyCarEntity = mock(AssemblyCarEntity.class);
        when(assemblyCarEntity.polish()).thenReturn(assemblyCarEntity);

        interiorAssemblyStation.accept(assemblyCarEntity);

        verify(assemblyCarEntity).polish();
    }

    @Test
    void testGetTypeIsBuilder() {
        assertEquals(AssemblyStationType.BUILDER, interiorAssemblyStation.getType());
    }

}