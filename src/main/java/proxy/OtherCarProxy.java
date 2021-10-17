package proxy;

import entity.OtherCar;

public class OtherCarProxy extends OtherCar {
  @Override public OtherCarBuilder builder() {
    System.out.println("Creating strange Car...");
    return super.builder();
  }
}
