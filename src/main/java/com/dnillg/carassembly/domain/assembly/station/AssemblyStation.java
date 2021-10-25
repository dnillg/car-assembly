package com.dnillg.carassembly.domain.assembly.station;

import com.dnillg.carassembly.domain.assembly.AssemblyCarEntity;

public interface AssemblyStation {

    /**
     * Takes an {@link AssemblyCarEntity} and performs a step of production .
     * @param assemblyCarEntity The received job, performing the step means changing the state of assemblyCarEntity.
     * @return true if the step was executed successfully, false if the step failed
     */
    Object accept(AssemblyCarEntity assemblyCarEntity);

    AssemblyStationType getType();

}
