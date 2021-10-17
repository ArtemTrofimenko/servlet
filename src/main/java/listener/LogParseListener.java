package listener;

import entity.Car;
import interfaces.EventListener;

public class LogParseListener implements EventListener {
  @Override public void update(String eventType, Car car) {
    System.out.println("An operation \"" + eventType + "\" was applied to the Car with URL: " + car.getPage());
  }
}
