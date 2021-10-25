package com.dnillg.carassembly.domain.assembly.station.decorator;

import com.dnillg.carassembly.domain.assembly.AssemblyCarEntity;
import com.dnillg.carassembly.domain.lock.DomainLock;
import com.dnillg.carassembly.domain.assembly.station.AssemblyStation;
import com.dnillg.carassembly.domain.assembly.station.AssemblyStationType;

public class MutuallyExclusiveAssemblyStation implements AssemblyStation {

    private final AssemblyStation delegate;
    private final DomainLock lock;

    public MutuallyExclusiveAssemblyStation(AssemblyStation delegate, DomainLock lock) {
        this.delegate = delegate;
        this.lock = lock;
    }

    public static MutuallyExclusiveAssemblyStation wrap(AssemblyStation station, DomainLock distributedLock) {
        return new MutuallyExclusiveAssemblyStation(station, distributedLock);
    }

    @Override
    public Object accept(AssemblyCarEntity assemblyCarEntity) {
        return lock.executeWithLock(() -> delegate.accept(assemblyCarEntity));
    }

    @Override
    public AssemblyStationType getType() {
        return delegate.getType();
    }
}
