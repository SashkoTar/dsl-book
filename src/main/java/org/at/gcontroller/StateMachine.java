package org.at.gcontroller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by otarasenko on 4/21/15.
 */
public class StateMachine {

    private List<Event> resetEvents = new ArrayList<Event>();

    private State start;

    public StateMachine(State start) {
        this.start = start;
    }
    public Collection<State> getStates() {
        List<State> result = new ArrayList<State>();
        collectStates(result, start);
        return result;
    }
    private void collectStates(Collection<State> result,
                               State s) {
        if (result.contains(s)) return;
        result.add(s);
        for (State next : s.getAllTargets())
            collectStates(result, next);
    }

    private void addResetEvent_byAddingTransitions(Event e) {
        for (State s : getStates())
            if (!s.hasTransition(e.getCode()))
                s.addTransition(e, start);
    }

    public void addResetEvents(Event... events) {
        for (Event e : events) resetEvents.add(e);
    }

    public boolean isResetEvent(String eventCode) {
        return resetEventCodes().contains(eventCode);
    }
    private List<String> resetEventCodes() {
        List<String> result = new ArrayList<String>();
        for (Event e : resetEvents) result.add(e.getCode());
        return result;
    }


    public State getStart() {
        return this.start;
    }
}
