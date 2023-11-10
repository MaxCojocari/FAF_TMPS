package core.observers;

public class NewAccountObserver implements Observer {
    public void update(String eventType, String data) {
        System.out.println(data);
    }
}