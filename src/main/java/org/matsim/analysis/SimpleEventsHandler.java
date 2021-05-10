package org.matsim.analysis;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.ActivityEndEvent;
import org.matsim.api.core.v01.events.ActivityStartEvent;
import org.matsim.api.core.v01.events.handler.ActivityEndEventHandler;
import org.matsim.api.core.v01.events.handler.ActivityStartEventHandler;
import org.matsim.api.core.v01.population.Person;

import java.util.HashMap;
import java.util.Map;

public class SimpleEventsHandler implements ActivityStartEventHandler, ActivityEndEventHandler {

    private final Map<Id<Person>, Double> personToStartTime = new HashMap<>();

    @Override
    public void handleEvent(ActivityEndEvent event) {

        if(isInteraction(event.getActType())) return;

        personToStartTime.put(event.getPersonId(), event.getTime());
        System.out.println("Person: " + event.getPersonId().toString() + " finished activity " + event.getActType());
    }

    @Override
    public void handleEvent(ActivityStartEvent event) {

        if(isInteraction(event.getActType())) return;

        System.out.println("Person: " + event.getPersonId().toString() + " started activity " + event.getActType());
        double startTime = personToStartTime.get(event.getPersonId());
        double travelTime = event.getTime() - startTime;
        System.out.println("Travel time was: " + travelTime);
    }

    private boolean isInteraction(String type) {
        return type.endsWith("interaction");
    }
}
