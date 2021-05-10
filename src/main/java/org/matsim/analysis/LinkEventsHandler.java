package org.matsim.analysis;

import org.matsim.api.core.v01.events.LinkLeaveEvent;
import org.matsim.api.core.v01.events.handler.LinkLeaveEventHandler;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class LinkEventsHandler implements LinkLeaveEventHandler {

    LinkedList<Double> timeEventsOnLink = new LinkedList<>();

    @Override
    public void handleEvent(LinkLeaveEvent event) {
        //System.out.println(event.getLinkId().toString());

        if(!isLinkId(event.getLinkId().toString())) return;

        timeEventsOnLink.add(event.getTime());
    }

    private boolean isLinkId(String id){
        return id.equals("7232382780007f");
    }

    public void printHistogram(){
        //System.out.println(timeEventsOnLink.size());

        for(int i=10;i<15;i++){
            final int hour = i;

            int x = timeEventsOnLink.stream().filter(t -> t >= hour*60*60 && t < (hour+1)*60*60).collect(Collectors.toList()).size();

            System.out.println("in Stunde " + i + " gab es " + x + " Fahrzeuge");
        }
    }
}
