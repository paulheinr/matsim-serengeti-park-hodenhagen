package org.matsim.analysis.class202505;

import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.core.events.EventsUtils;

public class RunSimpleAnalysis {
    public static void main(String[] args) {
        EventsManager eventsManager = EventsUtils.createEventsManager();
        SimpleEventsHandler simpleEventsHandler = new SimpleEventsHandler();
        eventsManager.addHandler(simpleEventsHandler);

        EventsUtils.readEvents(eventsManager, "");

        System.out.println("Count was: " + simpleEventsHandler.getCount());
    }


}
