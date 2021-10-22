package com.dnillg.carassembly.domain.assembly.station.decorator;

import com.dnillg.carassembly.domain.assembly.AssemblyCarEntity;
import com.dnillg.carassembly.domain.assembly.station.AssemblyStation;
import com.dnillg.carassembly.domain.assembly.station.AssemblyStationType;

import java.util.Random;

public class HastyStation implements AssemblyStation {

    private static final Random RANDOM = new Random();

    private final AssemblyStation proxyObject;

    private HastyStation(AssemblyStation proxyObject) {
        this.proxyObject = proxyObject;
    }

    public static HastyStation wrap(AssemblyStation station) {
        return new HastyStation(station);
    }

    @Override
    public boolean accept(AssemblyCarEntity assemblyCarEntity) {
        if (RANDOM.nextInt(10) >= 2) { // 80%
            proxyObject.accept(assemblyCarEntity);
        } else {
            // Skip the station
        }
        return true;
    }

    @Override
    public AssemblyStationType getType() {
        return proxyObject.getType();
    }
}
