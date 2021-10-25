package com.dnillg.carassembly.domain.assembly.station.decorator;

import com.dnillg.carassembly.domain.assembly.AssemblyCarEntity;
import com.dnillg.carassembly.domain.assembly.station.AssemblyStation;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.stream.IntStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HastyAssemblyStationTest {

    @Test
    void wrap() {
        AssemblyStation assemblyStation = mock(AssemblyStation.class);
        final HastyAssemblyStation hastyAssemblyStation = HastyAssemblyStation.wrap(assemblyStation);

        IntStream.range(0, 1000).forEach(i -> hastyAssemblyStation.accept(mock(AssemblyCarEntity.class)));

        verify(assemblyStation, Mockito.atMost(1000-1)).accept(any());
    }

}