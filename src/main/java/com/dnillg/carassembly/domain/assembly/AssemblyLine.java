package com.dnillg.carassembly.domain.assembly;

import com.dnillg.carassembly.domain.assembly.station.AssemblyStationType;
import com.dnillg.carassembly.domain.assembly.station.AssemblyStation;
import com.dnillg.carassembly.domain.car.Car;
import com.dnillg.carassembly.domain.exception.AssemblyLineSetupException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class AssemblyLine {

    private final List<AssemblyStation> stations;

    public AssemblyLine(AssemblyStation... stationsArr) {
        stations = new ArrayList<>(Arrays.asList(stationsArr));
        if (stations.size() < 2) {
            throw new AssemblyLineSetupException("Station count should be at least 2!");
        }
        final int indexOfLastBuildingStation = IntStream.range(0, stations.size()).filter(i -> stations.get(i).getType() == AssemblyStationType.BUILDING).max().orElse(Integer.MAX_VALUE);
        final int indexOfFirstQualityAssuranceStation = IntStream.range(0, stations.size()).filter(i -> stations.get(i).getType() == AssemblyStationType.QUALITY_ASSURANCE).min().orElse(Integer.MIN_VALUE);
        if (indexOfLastBuildingStation > indexOfFirstQualityAssuranceStation) {
            throw new AssemblyLineSetupException("Quality Assurance station can't be followed with Building station!");
        }
    }

    public Car produce(AssemblyCarEntity assemblyCarEntity) {
        do {
            IntStream.range(0, stations.size() - 1).forEach(i -> stations.get(i).accept(assemblyCarEntity));
        } while (!stations.get(stations.size() - 1).accept(assemblyCarEntity));
        return assemblyCarEntity.build();
    }


}
