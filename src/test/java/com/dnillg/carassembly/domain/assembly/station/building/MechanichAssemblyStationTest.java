package com.dnillg.carassembly.domain.assembly.station.building;

import com.dnillg.carassembly.domain.assembly.AssemblyCarEntity;
import com.dnillg.carassembly.domain.assembly.station.AssemblyStationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MechanichAssemblyStationTest {

    private MechanichAssemblyStation interiorAssemblyStation; //sut

    @BeforeEach
    void setUp() {
        interiorAssemblyStation = new MechanichAssemblyStation();
    }

    @Test
    void testAcceptInvokesAssemblyMechanich() {
        AssemblyCarEntity assemblyCarEntity = mock(AssemblyCarEntity.class);
        when(assemblyCarEntity.assemblyMechanich()).thenReturn(assemblyCarEntity);

        interiorAssemblyStation.accept(assemblyCarEntity);

        verify(assemblyCarEntity).assemblyMechanich();
    }

    @Test
    void testGetTypeIsBuilder() {
        assertEquals(AssemblyStationType.BUILDER, interiorAssemblyStation.getType());
    }

}