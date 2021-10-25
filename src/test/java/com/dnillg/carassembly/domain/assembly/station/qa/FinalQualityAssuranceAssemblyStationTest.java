package com.dnillg.carassembly.domain.assembly.station.qa;

import com.dnillg.carassembly.domain.assembly.AssemblyCarEntity;
import com.dnillg.carassembly.domain.assembly.station.AssemblyStationType;
import com.dnillg.carassembly.domain.car.Car;
import com.dnillg.carassembly.domain.car.Engine;
import com.dnillg.carassembly.domain.car.painting.CarPainting;
import com.dnillg.carassembly.domain.exception.AssemblyQualityAssuranceFailureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FinalQualityAssuranceAssemblyStationTest {

    private FinalQualityAssuranceAssemblyStation station; //sut
    @Mock
    private Car car;
    @Mock
    private AssemblyCarEntity assemblyCarEntity;

    @BeforeEach
    void setUp() {
        station = new FinalQualityAssuranceAssemblyStation();
    }

    @Test
    void thenAcceptWhenCompleteThenNoValidationFailure() {
        initComplete();

        final Car result = station.accept(assemblyCarEntity);

        assertNotNull(result);
    }

    @Test
    void thenAcceptWhenEngineMissingThenValidationFailure() {
        initComplete();
        when(car.getEngine()).thenReturn(null);

        assertThrows(AssemblyQualityAssuranceFailureException.class, () -> station.accept(assemblyCarEntity));
    }

    @Test
    void thenAcceptWhenSeatsAreNotInstalledThenValidationFailure() {
        initComplete();
        when(car.getNumberOfSeats()).thenReturn(0);

        assertThrows(AssemblyQualityAssuranceFailureException.class, () -> station.accept(assemblyCarEntity));
    }

    @Test
    void thenAcceptWhenNotPaintedThenValidationFailure() {
        initComplete();
        when(car.getPainting()).thenReturn(null);

        assertThrows(AssemblyQualityAssuranceFailureException.class, () -> station.accept(assemblyCarEntity));
    }

    @Test
    void getType() {
        assertEquals(AssemblyStationType.QUALITY_ASSURANCE, station.getType());
    }

    private void initComplete() {
        lenient().when(assemblyCarEntity.getId()).thenReturn("id");
        lenient().when(car.getEngine()).thenReturn(mock(Engine.class));
        lenient().when(car.getNumberOfSeats()).thenReturn(99);
        lenient().when(car.getPainting()).thenReturn(mock(CarPainting.class));
        when(assemblyCarEntity.build()).thenReturn(car);
    }
}