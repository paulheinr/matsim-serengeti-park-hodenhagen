package org.matsim.analysis.class202505;

import org.matsim.api.core.v01.events.LinkLeaveEvent;
import org.matsim.api.core.v01.events.handler.LinkLeaveEventHandler;
import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.core.events.EventsUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class RunTripDurationAnalysis {
    public static void main(String[] args) {
        EventsManager eventsManager = EventsUtils.createEventsManager();
        LinkEventHandler linkEventHandler = new LinkEventHandler();
        eventsManager.addHandler(linkEventHandler);
        EventsUtils.readEvents(eventsManager, "./scenarios/serengeti-park-v1.0/output/output-serengeti-park-v1.0-run1/serengeti-park-v1.0-run1.output_events.xml.gz");

        // we are using a tree map for sorted output
        Map<Integer, Integer> countByHour = new TreeMap<>();

        for (Double time : linkEventHandler.times) {
            int hour = getHour(time);
            Integer count = countByHour.get(hour);
            if (count == null) {
                countByHour.put(hour, 1);
            } else {
                countByHour.put(hour, count + 1);
            }
        }

        System.out.println(countByHour);
    }

    private static int getHour(double time) {
        return (int) (time / 3600);
    }

    private static class LinkEventHandler implements LinkLeaveEventHandler {
        private List<Double> times = new ArrayList<>();

        @Override
        public void handleEvent(LinkLeaveEvent linkLeaveEvent) {
            times.add(linkLeaveEvent.getTime());
        }
    }


}
