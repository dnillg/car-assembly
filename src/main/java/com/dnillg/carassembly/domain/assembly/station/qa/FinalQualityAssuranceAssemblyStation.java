package com.dnillg.carassembly.domain.assembly.station.qa;

import com.dnillg.carassembly.domain.assembly.AssemblyCarEntity;
import com.dnillg.carassembly.domain.assembly.station.QualityAssuranceAssemblyStation;
import com.dnillg.carassembly.domain.car.Car;
import com.dnillg.carassembly.domain.exception.AssemblyQualityAssuranceFailureException;

public class FinalQualityAssuranceAssemblyStation extends QualityAssuranceAssemblyStation {

    @Override
    public Car accept(AssemblyCarEntity assemblyCarEntity) {
        final Car car = assemblyCarEntity.build();
        boolean interiorComplete = car.getNumberOfSeats() > 0;
        boolean mechanicComplete = car.getEngine() != null;
        boolean isPainted = car.getPainting() != null;
        if (interiorComplete && mechanicComplete && isPainted) {
            return car;
        } else {
            throw new AssemblyQualityAssuranceFailureException("Final QA failed!");
        }
    }

}
