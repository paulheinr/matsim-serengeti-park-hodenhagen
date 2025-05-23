package org.matsim.analysis;

import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.core.events.EventsUtils;

public class MyAnalysis {
    public static void main(String[] args) {
        EventsManager manager = EventsUtils.createEventsManager();
        MyEventsHandler eventHandler = new MyEventsHandler();
        manager.addHandler(eventHandler);

        EventsUtils.readEvents(manager, "scenarios/serengeti-park-v1.0/output/output-serengeti-park-v1.0-run1/serengeti-park-v1.0-run1.output_events.xml.gz");

        System.out.println("Count is: " + eventHandler.count);
    }
}
