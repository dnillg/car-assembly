package com.dnillg.carassembly.domain.assembly.station;

import com.dnillg.carassembly.domain.assembly.AssemblyCarEntity;
import com.dnillg.carassembly.domain.car.Car;

public abstract class QualityAssuranceAssemblyStation implements AssemblyStation {

    @Override
    public abstract Car accept(AssemblyCarEntity assemblyCarEntity);

    @Override
    public AssemblyStationType getType() {
        return AssemblyStationType.QUALITY_ASSURANCE;
    }

}
