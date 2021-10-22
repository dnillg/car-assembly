package com.dnillg.carassembly.domain.assembly.station;

import com.dnillg.carassembly.domain.assembly.AssemblyCarEntity;

public interface AssemblyStation {

    boolean accept(AssemblyCarEntity assemblyCarEntity);

    AssemblyStationType getType();

}
