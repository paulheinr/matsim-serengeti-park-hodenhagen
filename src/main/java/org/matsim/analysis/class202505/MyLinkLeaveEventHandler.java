package org.matsim.analysis.class202505;

import org.matsim.api.core.v01.events.LinkLeaveEvent;
import org.matsim.api.core.v01.events.handler.LinkLeaveEventHandler;

public class MyLinkLeaveEventHandler implements LinkLeaveEventHandler {
    @Override
    public void handleEvent(LinkLeaveEvent linkLeaveEvent) {
        System.out.println(linkLeaveEvent.getLinkId());
    }
}
