package entity;

import interfaces.MileageAdapter;
import interfaces.PriceAdapter;
import org.codehaus.jackson.annotate.JsonIgnore;

public class Car implements MileageAdapter, PriceAdapter {
  private String mark;
  private String model;
  private String price;
  private String mileage;
  private String page;

  @Override public String toString() {
    return "Car{" +
          "mark='" + mark + '\'' +
          ", model='" + model + '\'' +
          ", price='" + price + '\'' +
          ", mileage='" + mileage + '\'' +
          ", page='" + page + '\'' +
          '}';
  }

  @JsonIgnore
  @Override public String getMileageInMiles() {
    return Double.parseDouble(mileage) * 1.6 + " miles";
  }

  @JsonIgnore
  @Override public String getMileageInKm() {
    return Integer.parseInt(mileage) + " kilometers";
  }

  @JsonIgnore
  @Override public String getUahPrice() {
    return Double.parseDouble(price) * 26.7 + " UAH";
  }

  @JsonIgnore
  @Override public String getEuroPrice() {
    return Double.parseDouble(price) * 0.84 + " EURO";
  }

  @JsonIgnore
  @Override public String getUsdPrice() {
    return price + " USD";
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

  public String getPage() {
    return page;
  }

  public void setPage(String page) {
    this.page = page;
  }

  public static class Builder {
    private final Car builtCar;

    public Builder() {
      builtCar = new Car();
    }

    public Builder mark(String mark) {
      builtCar.setMark(mark);
      return this;
    }

    public Builder model(String model) {
      builtCar.setModel(model);
      return this;
    }

    public Builder price(String price) {
      builtCar.setPrice(price);
      return this;
    }

    public Builder mileage(String mileage) {
      builtCar.setMileage(mileage);
      return this;
    }

    public Builder page(String page) {
      builtCar.setPage(page);
      return this;
    }

    public Car build() {
      return builtCar;
    }
  }

}
