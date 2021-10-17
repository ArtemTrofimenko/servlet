package proxy;

import entity.ICECar;

public class ICECarProxy extends ICECar {
  @Override public ICECarBuilder builder() {
    System.out.println("Creating Car with ICE...");
    return super.builder();
  }
}
