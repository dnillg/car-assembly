package com.dnillg.carassembly.domain;

import com.dnillg.carassembly.domain.assembly.AssemblyCarEntity;
import com.dnillg.carassembly.domain.assembly.AssemblyLine;
import com.dnillg.carassembly.domain.assembly.station.building.InteriorAssemblyStation;
import com.dnillg.carassembly.domain.assembly.station.building.MechanichAssemblyStation;
import com.dnillg.carassembly.domain.assembly.station.building.PaintingStation;
import com.dnillg.carassembly.domain.assembly.station.qa.FinalQualityAssuranceStation;
import com.dnillg.carassembly.domain.car.Car;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssemblyLineApplicationTest {

    @Test
    void testAssemblyWhenStationsAreReliableThenCarIsBuilt() {
        final MechanichAssemblyStation mechanichAssemblyStation = new MechanichAssemblyStation();
        final FinalQualityAssuranceStation qualityAssuranceStation = new FinalQualityAssuranceStation();
        final InteriorAssemblyStation interiorAssemblyStation = new InteriorAssemblyStation();
        final PaintingStation paintingStation = new PaintingStation();
        final AssemblyLine assemblyLine = new AssemblyLine(mechanichAssemblyStation, interiorAssemblyStation, paintingStation, qualityAssuranceStation);

        final Car result = assemblyLine.produce(new AssemblyCarEntity());

        assertNotNull(result);
    }

}
