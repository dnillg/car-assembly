package com.dnillg.carassembly.domain.assembly.station.decorator;

import com.dnillg.carassembly.domain.assembly.AssemblyCarEntity;
import com.dnillg.carassembly.domain.assembly.station.AssemblyStation;
import com.dnillg.carassembly.domain.assembly.station.AssemblyStationType;

import java.util.Random;

public class HastyAssemblyStation implements AssemblyStation {

    private static final Random RANDOM = new Random();

    private final AssemblyStation delegate;

    private HastyAssemblyStation(AssemblyStation delegate) {
        this.delegate = delegate;
    }

    public static HastyAssemblyStation wrap(AssemblyStation station) {
        return new HastyAssemblyStation(station);
    }

    @Override
    public Object accept(AssemblyCarEntity assemblyCarEntity) {
        if (RANDOM.nextInt(10) >= 1) { // 90%
            delegate.accept(assemblyCarEntity);
        } else {
            // Skip the station
        }
        return null;
    }

    @Override
    public AssemblyStationType getType() {
        return delegate.getType();
    }
}
