package entity;

import interfaces.CurrencyStrategy;

public class OtherCar extends Car {

  public OtherCarBuilder builder() {
    return new OtherCarBuilder();
  }

  public static class OtherCarBuilder extends CarBuilder {
    private final OtherCar builtCar;

    public OtherCarBuilder() {
      builtCar = new OtherCar();
    }

    public OtherCarBuilder mark(String mark) {
      builtCar.setMark(mark);
      return this;
    }

    public OtherCarBuilder model(String model) {
      builtCar.setModel(model);
      return this;
    }

    public OtherCarBuilder price(String price) {
      builtCar.setPrice(price);
      return this;
    }

    public OtherCarBuilder mileage(String mileage) {
      builtCar.setMileage(mileage);
      return this;
    }

    public OtherCarBuilder page(String page) {
      builtCar.setPage(page);
      return this;
    }

    @Override public CarBuilder currencyStrategy(CurrencyStrategy currencyStrategy) {
      builtCar.setCurrencyStrategy(currencyStrategy);
      return this;
    }

    public OtherCar build() {
      return builtCar;
    }
  }
}
