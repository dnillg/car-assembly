package com.dnillg.carassembly.domain.assembly;

import com.dnillg.carassembly.domain.assembly.station.AssemblyStation;
import com.dnillg.carassembly.domain.assembly.station.AssemblyStationType;
import com.dnillg.carassembly.domain.car.Car;
import com.dnillg.carassembly.domain.exception.AssemblyLineSetupException;
import com.dnillg.carassembly.domain.exception.AssemblyQualityAssuranceFailureException;
import com.dnillg.carassembly.domain.exception.DomainException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AssemblyLine {

    private static final Logger LOG = LoggerFactory.getLogger(AssemblyLine.class);
    private static final int DEFAULT_MAX_ASSEMBLY_LINE_CYCLES = 10;

    private final List<AssemblyStation> stations;
    private final int maxAssemblyLineCycles;

    public AssemblyLine(int maxAssemblyLineCycles, AssemblyStation... stationsArr) {
        this.maxAssemblyLineCycles = maxAssemblyLineCycles;
        stations = List.of(stationsArr);
        validateStations();
    }

    public AssemblyLine(AssemblyStation... stationsArr) {
        this(DEFAULT_MAX_ASSEMBLY_LINE_CYCLES, stationsArr);
    }

    public Car produce(AssemblyCarEntity assemblyCarEntity) {
        int assemblyLineCycles = 0;
        do {
            try {
                return executePipeline(assemblyCarEntity);
            } catch (AssemblyQualityAssuranceFailureException e) {
                LOG.debug("AssemblyCarEntity {} has failed on quality assurance!", assemblyCarEntity.getId());
            }
            assemblyLineCycles++;
        } while (assemblyLineCycles < maxAssemblyLineCycles);
        throw new DomainException("Maximum Assembly Line Cycles has been reached on AssemblyCarEntity with id: " + assemblyCarEntity.getId());
    }

    private Car executePipeline(AssemblyCarEntity assemblyCarEntity) {
        return (Car) stations.stream()
            .map(s -> s.accept(assemblyCarEntity))
            .skip(stations.size() - 1).findFirst()
            .orElseThrow(() -> new IllegalStateException("Unexpected state happened: Car hasn't been built!"));
    }

    private void validateStations() {
        if (stations.size() < 2) {
            throw new AssemblyLineSetupException("Station count should be at least 2!");
        }
        final List<Integer> indexOfQaStations = IntStream.range(0, stations.size())
            .filter(i -> stations.get(i).getType() == AssemblyStationType.QUALITY_ASSURANCE)
            .boxed()
            .collect(Collectors.toList());
        if (indexOfQaStations.size() != 1) {
            throw new AssemblyLineSetupException("The line can't have more than 1 Quality Assurance station!");
        }
        if (indexOfQaStations.get(0) != stations.size() -1) {
            throw new AssemblyLineSetupException("The last station has to be the Quality Assurance one!");
        }
    }

}
