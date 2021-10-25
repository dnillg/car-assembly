package com.dnillg.carassembly.domain.assembly.station.building;

import com.dnillg.carassembly.domain.assembly.AssemblyCarEntity;
import com.dnillg.carassembly.domain.assembly.station.BuilderAssemblyStation;

public class InteriorAssemblyStation extends BuilderAssemblyStation {

    @Override
    public void doAccept(AssemblyCarEntity assemblyCarEntity) {
        assemblyCarEntity.assemblyInterior();
    }

}
