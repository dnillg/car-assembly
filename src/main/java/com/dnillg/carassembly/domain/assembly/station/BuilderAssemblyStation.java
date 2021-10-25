package com.dnillg.carassembly.domain.assembly.station;

import com.dnillg.carassembly.domain.assembly.AssemblyCarEntity;
import com.dnillg.carassembly.domain.car.Car;

public abstract class BuilderAssemblyStation implements AssemblyStation {

    @Override
    public Void accept(AssemblyCarEntity assemblyCarEntity) {
        doAccept(assemblyCarEntity);
        return null;
    }

    protected abstract void doAccept(AssemblyCarEntity assemblyCarEntity);

    @Override
    public AssemblyStationType getType() {
        return AssemblyStationType.BUILDER;
    }

}
