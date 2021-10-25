package com.dnillg.carassembly.domain.assembly.station.qa;

import com.dnillg.carassembly.domain.assembly.AssemblyCarEntity;
import com.dnillg.carassembly.domain.assembly.station.QualityAssuranceAssemblyStation;
import com.dnillg.carassembly.domain.car.Car;
import com.dnillg.carassembly.domain.exception.AssemblyQualityAssuranceFailureException;

import java.util.Random;

public class FakeQualityAssuranceAssemblyStation extends QualityAssuranceAssemblyStation {

    private static final Random RANDOM = new Random();

    @Override
    public Car accept(AssemblyCarEntity assemblyCarEntity) {
        if (RANDOM.nextInt(10) >= 2) { //80%
            return assemblyCarEntity.build();
        } else {
            throw new AssemblyQualityAssuranceFailureException("Fake QA failed!");
        }
    }

}
