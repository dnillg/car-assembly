package com.dnillg.carassembly.domain.assembly;

import com.dnillg.carassembly.domain.assembly.station.AssemblyStation;
import com.dnillg.carassembly.domain.assembly.station.AssemblyStationType;
import com.dnillg.carassembly.domain.car.Car;
import com.dnillg.carassembly.domain.exception.AssemblyLineSetupException;
import com.dnillg.carassembly.domain.exception.AssemblyQualityAssuranceFailureException;
import com.dnillg.carassembly.domain.exception.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AssemblyLineTest {

    private AssemblyLine assemblyLine; //sut

    @Test
    void testProduceWhenOneStationFailesThenTheLineIsRepeated() {
        //given
        final AssemblyStation mockBuldingStation = createStationMock(AssemblyStationType.BUILDER);
        final AssemblyStation mockQaStation = createStationMock(AssemblyStationType.QUALITY_ASSURANCE);
        final AssemblyCarEntity assemblyCarEntity = createAssemblyCarEntity();
        assemblyLine = new AssemblyLine(mockBuldingStation, mockQaStation);
        final Car mockCar = mock(Car.class);
        when(mockQaStation.accept(assemblyCarEntity)).thenThrow(new AssemblyQualityAssuranceFailureException("qa failed")).thenReturn(mockCar);

        //when
        final Car result = assemblyLine.produce(assemblyCarEntity);

        //then
        assertEquals(result, mockCar);
        verify(mockBuldingStation, times(2)).accept(assemblyCarEntity);
        verify(mockQaStation, times(2)).accept(assemblyCarEntity);
    }

    @Test
    void testProduceWhenSetupResultsInfiniteLoopThenStopsAfterThreshold() {
        //given
        final AssemblyStation mockBuldingStation = createStationMock(AssemblyStationType.BUILDER);
        final AssemblyStation mockQaStation = createStationMock(AssemblyStationType.QUALITY_ASSURANCE);
        final AssemblyCarEntity assemblyCarEntity = createAssemblyCarEntity();
        assemblyLine = new AssemblyLine(20, mockBuldingStation, mockQaStation);
        when(mockQaStation.accept(assemblyCarEntity)).thenThrow(new AssemblyQualityAssuranceFailureException("qa failed"));

        //when
        assertThrows(DomainException.class, () -> assemblyLine.produce(assemblyCarEntity));

        //then
        //exception is thrown
        verify(mockBuldingStation, times(20)).accept(assemblyCarEntity);
        verify(mockQaStation, times(20)).accept(assemblyCarEntity);
    }

    @Test
    void testProduceWhenStationCountIsLessThanTwoThenThrowsException() {
        assertThrows(AssemblyLineSetupException.class, () -> new AssemblyLine(
            createStationMock(AssemblyStationType.QUALITY_ASSURANCE)));
    }

    @Test
    void testProduceWhenQaIsBeforeBuldingStationThenThrowsException() {
        assertThrows(AssemblyLineSetupException.class, () -> new AssemblyLine(
            createStationMock(AssemblyStationType.BUILDER),
            createStationMock(AssemblyStationType.QUALITY_ASSURANCE),
            createStationMock(AssemblyStationType.BUILDER)));
    }

    private AssemblyCarEntity createAssemblyCarEntity() {
        final AssemblyCarEntity mock = mock(AssemblyCarEntity.class);
        when(mock.getId()).thenReturn("the_id");
        return mock;
    }

    private AssemblyStation createStationMock(AssemblyStationType building) {
        final AssemblyStation mockBuldingStation = mock(AssemblyStation.class);
        when(mockBuldingStation.getType()).thenReturn(building);
        return mockBuldingStation;
    }
}