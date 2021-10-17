package listener;

import entity.Car;
import interfaces.EventListener;

public class AddParseListener implements EventListener {
  @Override public void update(String eventType, Car car) {
    System.out.println("An operation \"" + eventType + " to Thread list\" was applied to the Thread with Car with URL: " + car.getPage());

  }
}
