package org.matsim.analysis.class202505;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.LinkLeaveEvent;
import org.matsim.api.core.v01.events.handler.LinkLeaveEventHandler;
import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.core.events.EventsUtils;

import java.util.ArrayList;
import java.util.List;

public class MyOwnEventsAnalysis {
    public static void main(String[] args) {
        //create
        EventsManager eventsManager = EventsUtils.createEventsManager();

        MyLinkLeaveEventHandler handler = new MyLinkLeaveEventHandler();

        //add handler
        eventsManager.addHandler(handler);

        //read events
        EventsUtils.readEvents(eventsManager, "scenarios/serengeti-park-v1.0/output/output-serengeti-park-v1.0-run1/serengeti-park-v1.0-run1.output_events.xml.gz");

        //print results
        System.out.println(handler.getTimes());

        int count10 = 0;
        int count11 = 0;
        int count12 = 0;
        int count13 = 0;
        int count14 = 0;

        for (Integer time : handler.getTimes()) {
            if (time==10) {
                count10++;
            } else if (time==11) {
                count11++;
            } else if (time==12) {
                count12++;
            } else if (time==13) {
                count13++;
            } else if (time==14) {
                count14++;
            }
        }

        System.out.println("Count of hour 10 is " + count10);
        System.out.println("Count of hour 11 is " + count11);
        System.out.println("Count of hour 12 is " + count12);
        System.out.println("Count of hour 13 is " + count13);
        System.out.println("Count of hour 14 is " + count14);
    }

    static class MyLinkLeaveEventHandler implements LinkLeaveEventHandler {
        private List<Integer> times = new ArrayList<>();

        @Override
        public void handleEvent(LinkLeaveEvent linkLeaveEvent) {
            if (!linkLeaveEvent.getLinkId().equals(Id.createLinkId("7238850360005f"))) {
                return;
            }
            times.add((int) linkLeaveEvent.getTime()/3600);
        }

        public List<Integer> getTimes() {
            return times;
        }
    }
}
