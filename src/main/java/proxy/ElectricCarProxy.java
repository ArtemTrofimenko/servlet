package proxy;

import entity.ElectricCar;

public class ElectricCarProxy extends ElectricCar {
  @Override public ElectricCarBuilder builder() {
    System.out.println("Creating Car with Electric Engine...");
    return super.builder();
  }
}
