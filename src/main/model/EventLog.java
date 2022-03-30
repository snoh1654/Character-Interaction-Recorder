package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// Represents a log of events that occur in the Champion Collection.
public class EventLog implements Iterable<Event> {
    // Class taken from EventLog class in AlarmSystem at
    // https://github.students.cs.ubc.ca/CPSC210/AlarmSystem

    private static EventLog theLog;
    private Collection<Event> events;

    // EFFECTS: creates an EventLog Object with an empty log; This class follows the Singleton design pattern, so
    // only one instance of EventLog can exist.
    private EventLog() {
        events = new ArrayList<Event>();
    }

    // EFFECTS: returns the instance of the EventLog; if the EventLog is not yet created, this instantiates the EventLog
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }
        return theLog;
    }

    // MODIFIES: this
    // EFFECTS: adds event to the event log
    public void logEvent(Event e) {
        events.add(e);
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}