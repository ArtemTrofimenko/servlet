package interfaces;

import entity.Car;

public interface EventListener {
  void update(String eventType, Car car);
}
