package core.interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.observers.Observer;

public class Subject {
    Map<String, List<Observer>> listeners = new HashMap<>();

    public Subject(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public void attach(String eventType, Observer listener) {
        List<Observer> users = listeners.get(eventType);
        users.add(listener);
    }

    public void detach(String eventType, Observer listener) {
        List<Observer> users = listeners.get(eventType);
        users.remove(listener);
    }

    public void notify(String eventType, String data) {
        List<Observer> users = listeners.get(eventType);
        for (Observer listener : users) {
            listener.update(eventType, data);
        }
    }
}
