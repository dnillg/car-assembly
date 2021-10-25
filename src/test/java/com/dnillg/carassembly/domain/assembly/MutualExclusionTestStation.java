package com.dnillg.carassembly.domain.assembly;

import com.dnillg.carassembly.domain.assembly.station.AssemblyStation;
import com.dnillg.carassembly.domain.assembly.station.AssemblyStationType;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MutualExclusionTestStation implements AssemblyStation {

    AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Object accept(AssemblyCarEntity assemblyCarEntity) {
        assertEquals(0, counter.compareAndExchange(0, 1));
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted!", e);
        }
        counter.compareAndExchange(1, 0);
        return new Object();
    }

    @Override
    public AssemblyStationType getType() {
        return AssemblyStationType.BUILDER;
    }
}
