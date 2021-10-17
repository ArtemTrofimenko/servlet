package entity;

import interfaces.CurrencyStrategy;

public class ElectricCar extends Car {
  public ElectricCarBuilder builder() {
    return new ElectricCarBuilder();
  }

  public static class ElectricCarBuilder extends CarBuilder {
    private final ElectricCar builtCar;

    public ElectricCarBuilder() {
      builtCar = new ElectricCar();
    }

    public ElectricCarBuilder mark(String mark) {
      builtCar.setMark(mark);
      return this;
    }

    public ElectricCarBuilder model(String model) {
      builtCar.setModel(model);
      return this;
    }

    public ElectricCarBuilder price(String price) {
      builtCar.setPrice(price);
      return this;
    }

    public ElectricCarBuilder mileage(String mileage) {
      builtCar.setMileage(mileage);
      return this;
    }

    public ElectricCarBuilder page(String page) {
      builtCar.setPage(page);
      return this;
    }

    @Override public CarBuilder currencyStrategy(CurrencyStrategy currencyStrategy) {
      builtCar.setCurrencyStrategy(currencyStrategy);
      return this;
    }

    public ElectricCar build() {
      return builtCar;
    }
  }
}
