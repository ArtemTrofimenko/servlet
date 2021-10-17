package entity;

import interfaces.CurrencyStrategy;

public class ICECar extends Car {

  public ICECarBuilder builder() {
    return new ICECarBuilder();
  }

  public static class ICECarBuilder extends CarBuilder {
    private final ICECar builtCar;

    public ICECarBuilder() {
      builtCar = new ICECar();
    }

    public ICECarBuilder mark(String mark) {
      builtCar.setMark(mark);
      return this;
    }

    public ICECarBuilder model(String model) {
      builtCar.setModel(model);
      return this;
    }

    public ICECarBuilder price(String price) {
      builtCar.setPrice(price);
      return this;
    }

    public ICECarBuilder mileage(String mileage) {
      builtCar.setMileage(mileage);
      return this;
    }

    public ICECarBuilder page(String page) {
      builtCar.setPage(page);
      return this;
    }

    @Override public CarBuilder currencyStrategy(CurrencyStrategy currencyStrategy) {
      builtCar.setCurrencyStrategy(currencyStrategy);
      return null;
    }

    public ICECar build() {
      return builtCar;
    }
  }
}
