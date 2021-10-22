package com.dnillg.carassembly.domain.assembly.station.qa;

import com.dnillg.carassembly.domain.assembly.AssemblyCarEntity;
import com.dnillg.carassembly.domain.assembly.station.AssemblyStation;
import com.dnillg.carassembly.domain.assembly.station.AssemblyStationType;

import java.util.stream.Stream;

public class FinalQualityAssuranceStation implements AssemblyStation {

    @Override
    public boolean accept(AssemblyCarEntity assemblyCarEntity) {
        boolean interiorComplete = assemblyCarEntity.getNumberOfSeats() > 0;
        boolean hasEngine = assemblyCarEntity.getEngine() != null;
        boolean isPainted = assemblyCarEntity.getPainting() != null;
        return Stream.of(interiorComplete, hasEngine, isPainted).allMatch(check -> check);
    }

    @Override
    public AssemblyStationType getType() {
        return AssemblyStationType.QUALITY_ASSURANCE;
    }

}
