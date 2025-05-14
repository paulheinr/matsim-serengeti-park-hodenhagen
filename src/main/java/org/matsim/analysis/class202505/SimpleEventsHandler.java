package org.matsim.analysis.class202505;

import org.matsim.api.core.v01.events.LinkLeaveEvent;
import org.matsim.api.core.v01.events.handler.LinkLeaveEventHandler;

public class SimpleEventsHandler implements LinkLeaveEventHandler {
    private int count = 0;

    @Override
    public void handleEvent(LinkLeaveEvent linkLeaveEvent) {
        count++;
    }

    int getCount() {
        return count;
    }
}
