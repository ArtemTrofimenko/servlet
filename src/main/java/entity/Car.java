package entity;

import interfaces.CurrencyStrategy;
import interfaces.MileageAdapter;
import org.codehaus.jackson.annotate.JsonIgnore;

public abstract class Car implements MileageAdapter{
  private CurrencyStrategy currencyStrategy;
  private String mark;
  private String model;
  private String price;
  private String mileage;
  private String page;

  @Override public String toString() {
    return "{\"mark\": \"" + mark + "\"" +
          ",\"model\": \"" + model + "\"" +
          ", \"price\": \"" + price + "\"" +
          ", \"mileage\": \"" + mileage + "\"" +
          ", \"page\": \"" + page + "\"}";
  }

  @JsonIgnore
  @Override public String getMileageInMiles() {
    return Double.parseDouble(mileage) * 1.6 + " miles";
  }

  @JsonIgnore
  @Override public String getMileageInKm() {
    return Integer.parseInt(mileage) + " kilometers";
  }

  public Car() {
  }

  public String getMark() {
    return mark;
  }

  public void setMark(String mark) {
    this.mark = mark;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getMileage() {
    return mileage;
  }

  public void setMileage(String mileage) {
    this.mileage = mileage;
  }

  public CurrencyStrategy getCurrencyStrategy() {
    return currencyStrategy;
  }

  public void setCurrencyStrategy(CurrencyStrategy currencyStrategy) {
    this.currencyStrategy = currencyStrategy;
  }

  public String getPage() {
    return page;
  }

  public void setPage(String page) {
    this.page = page;
  }

  public abstract static class CarBuilder {

    public abstract CarBuilder mark(String mark);

    public abstract CarBuilder model(String model);

    public abstract CarBuilder price(String price);

    public abstract CarBuilder mileage(String mileage);

    public abstract CarBuilder page(String page);

    public abstract CarBuilder currencyStrategy(CurrencyStrategy currencyStrategy);

    public abstract Car build();
  }

}
