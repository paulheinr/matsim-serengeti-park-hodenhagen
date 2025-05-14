package org.matsim.analysis.class202505;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.ActivityEndEvent;
import org.matsim.api.core.v01.events.ActivityStartEvent;
import org.matsim.api.core.v01.events.handler.ActivityEndEventHandler;
import org.matsim.api.core.v01.events.handler.ActivityStartEventHandler;
import org.matsim.api.core.v01.population.Person;

import java.util.*;

public class ActivityAnalysis {
    private static class TripDurationEventHandler implements ActivityEndEventHandler, ActivityStartEventHandler {

        private Map<Id<Person>, Double> tripStart = new HashMap<>();

        private List<Double> durations = new ArrayList<>();

        @Override
        public void handleEvent(ActivityEndEvent activityEndEvent) {
            tripStart.put(activityEndEvent.getPersonId(), activityEndEvent.getTime());
        }

        @Override
        public void handleEvent(ActivityStartEvent activityStartEvent) {
            Double start = tripStart.get(activityStartEvent.getPersonId());
            durations.add(activityStartEvent.getTime() - start);
            tripStart.remove(activityStartEvent.getPersonId());
        }

        public List<Double> getDurations() {
            return durations;
        }
    }

    private static void printHistogram(List<Double> durations) {
        System.out.println(durations.stream().mapToDouble(i -> i).summaryStatistics());

        durations.sort(Double::compareTo);

        Double shortest = durations.getFirst();
        Double longest = durations.getLast();

        int binCount = 10;
        double binSize = (longest-shortest)/binCount;

        int[] counts = new int[binCount];

        for (Double duration : durations) {
            int bin = (int) (duration/binSize);
            if (bin>=binCount) {
                bin = binCount -1 ;
            }
            counts[bin]++;
        }

        System.out.println("Trip durations with " + binCount + " bins and size of " + binSize);
        System.out.println(Arrays.toString(counts));
    }
}
