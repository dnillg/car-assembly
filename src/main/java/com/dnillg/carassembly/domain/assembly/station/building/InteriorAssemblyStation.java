package com.dnillg.carassembly.domain.assembly.station.building;

import com.dnillg.carassembly.domain.assembly.AssemblyCarEntity;
import com.dnillg.carassembly.domain.assembly.station.AssemblyStation;
import com.dnillg.carassembly.domain.assembly.station.AssemblyStationType;

public class InteriorAssemblyStation implements AssemblyStation {

    @Override
    public boolean accept(AssemblyCarEntity assemblyCarEntity) {
        assemblyCarEntity.assemblyInterior();
        return true;
    }

    @Override
    public AssemblyStationType getType() {
        return AssemblyStationType.BUILDING;
    }

}
