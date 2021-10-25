package com.dnillg.carassembly.domain;

import com.dnillg.carassembly.domain.assembly.AssemblyCarEntity;
import com.dnillg.carassembly.domain.assembly.AssemblyLine;
import com.dnillg.carassembly.domain.assembly.MutualExclusionTestStation;
import com.dnillg.carassembly.domain.lock.DummyDomainLock;
import com.dnillg.carassembly.domain.assembly.station.AssemblyStation;
import com.dnillg.carassembly.domain.assembly.station.decorator.MutuallyExclusiveAssemblyStation;
import com.dnillg.carassembly.domain.assembly.station.building.InteriorAssemblyStation;
import com.dnillg.carassembly.domain.assembly.station.building.MechanichAssemblyStation;
import com.dnillg.carassembly.domain.assembly.station.building.PaintingAssemblyStation;
import com.dnillg.carassembly.domain.assembly.station.building.PolishingStation;
import com.dnillg.carassembly.domain.assembly.station.decorator.HastyAssemblyStation;
import com.dnillg.carassembly.domain.assembly.station.qa.FinalQualityAssuranceAssemblyStation;
import com.dnillg.carassembly.domain.car.Car;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class AssemblyLineIntegrationTest {

    /**
     * No mutual exclusion for the stations, just simply thread-safe.
     */
    @Test
    void testAssemblyLineWhenStationsAreReliableThenCarIsBuilt() {
        final AssemblyLine assemblyLine = buildReliableCompleteAssemblyLine();

        final Car result = assemblyLine.produce(new AssemblyCarEntity());

        assertNotNull(result);
    }

    /**
     * Tests an assembly line that behaves like a pipeline.
     * When one job is being processed on a station of the assembly line, then the other stations can work on other jobs, but keeping the order.
     * This solution models a real-life assembly line, where we can't have 2 jobs at one single station at the same time.
     */
    @Test
    void testAssemblyLineWhenStationsAreReliableParallelThenCarIsBuiltParallel() {
        final AssemblyLine assemblyLine = buildExclusiveHastyCompleteAssemblyLine();
        final int jobsCount = 500;
        final List<Car> result = executeParallelJobs(assemblyLine, jobsCount);

        assertEquals(jobsCount, result.size());
        for (var item : result) {
            assertNotNull(item);
        }
    }

    /**
     * Tests the negative case for the mutual exclusion. One station is not wrapped with {@link MutuallyExclusiveAssemblyStation}.
     * Once the station is occupied by two separate jobs, then {@link AssertionFailedError} is expected to happen from {@link MutualExclusionTestStation}.
     */
    @Test
    void testAssemblyLineWhenOneStationIsNotMutuallyExclusiveThenItFails() {
        final AssemblyStation mutualExclusionTestAssemblyStation = new MutualExclusionTestStation();
        final AssemblyLine assemblyLine = buildExclusiveHastyCompleteAssemblyLine(mutualExclusionTestAssemblyStation);
        assertThrows(AssertionFailedError.class, () -> executeParallelJobs(assemblyLine, 500));

    }

    private List<Car> executeParallelJobs(AssemblyLine assemblyLine, int jobsCount) {
        return IntStream.range(0, jobsCount)
            .mapToObj(i -> new AssemblyCarEntity())
            .parallel()
            .map(assemblyLine::produce)
            .collect(Collectors.toList());
    }

    private AssemblyLine buildReliableCompleteAssemblyLine() {
        final MechanichAssemblyStation mechanichAssemblyStation = new MechanichAssemblyStation();
        final FinalQualityAssuranceAssemblyStation qualityAssuranceStation = new FinalQualityAssuranceAssemblyStation();
        final InteriorAssemblyStation interiorAssemblyStation = new InteriorAssemblyStation();
        final PaintingAssemblyStation paintingAssemblyStation = new PaintingAssemblyStation();
        return new AssemblyLine(mechanichAssemblyStation, interiorAssemblyStation, paintingAssemblyStation, qualityAssuranceStation);
    }

    private AssemblyLine buildExclusiveHastyCompleteAssemblyLine(AssemblyStation... additionalStations) {
        final AssemblyStation mechanichAssemblyStation = MutuallyExclusiveAssemblyStation.wrap(new MechanichAssemblyStation(), createLock(MechanichAssemblyStation.class.getSimpleName()));
        final AssemblyStation qualityAssuranceStation = MutuallyExclusiveAssemblyStation.wrap(new FinalQualityAssuranceAssemblyStation(), createLock(FinalQualityAssuranceAssemblyStation.class.getSimpleName()));
        final AssemblyStation interiorAssemblyStation = MutuallyExclusiveAssemblyStation.wrap(new InteriorAssemblyStation(), createLock(InteriorAssemblyStation.class.getSimpleName()));
        final AssemblyStation paintingAssemblyStation = MutuallyExclusiveAssemblyStation.wrap(new PaintingAssemblyStation(), createLock(PaintingAssemblyStation.class.getSimpleName()));
        final AssemblyStation polishingAssemblyStation = HastyAssemblyStation.wrap(MutuallyExclusiveAssemblyStation.wrap(new PolishingStation(), createLock(PolishingStation.class.getSimpleName())));
        List<AssemblyStation> stations = new ArrayList<>(List.of(mechanichAssemblyStation, interiorAssemblyStation, paintingAssemblyStation, polishingAssemblyStation, qualityAssuranceStation));
        stations.addAll(0, Arrays.asList(additionalStations));
        return new AssemblyLine(stations.toArray(AssemblyStation[]::new));
    }

    private DummyDomainLock createLock(String prefix) {
        return new DummyDomainLock(prefix + "_" + UUID.randomUUID().toString());
    }

}
