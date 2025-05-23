package org.matsim.analysis;

import org.matsim.api.core.v01.events.LinkLeaveEvent;
import org.matsim.api.core.v01.events.handler.LinkLeaveEventHandler;

public class MyEventsHandler implements LinkLeaveEventHandler {
    public int count = 0;

    @Override
    public void handleEvent(LinkLeaveEvent linkLeaveEvent) {
        count++;
    }
}
