package com.dnillg.carassembly.domain.assembly.station.building;

import com.dnillg.carassembly.domain.assembly.AssemblyCarEntity;
import com.dnillg.carassembly.domain.assembly.station.BuilderAssemblyStation;

public class PolishingStation extends BuilderAssemblyStation {

    @Override
    public void doAccept(AssemblyCarEntity assemblyCarEntity) {
        assemblyCarEntity.polish();
    }

}
