package utils;

import entity.Car;
import org.jsoup.nodes.Document;

import static java.util.Objects.requireNonNull;

public class ParseUtils {

  public static Car parseCar(Document carPage, String url) {

    String mark = requireNonNull(carPage.select("span[itemprop=\"brand\"]").first()).text();
    String model = requireNonNull(carPage.select("span[itemprop=\"name\"]").first()).text();
    String price = requireNonNull(carPage.select("div[class=\"price_value\"]").first()).text();

    String mileage = requireNonNull(carPage.select("div[class=\"base-information bold\"]").first()).text();
    mileage = mileage.substring(0, mileage.indexOf(" "));
    price = price.substring(0, price.indexOf(" $"));

    return new Car.Builder()
          .mark(mark)
          .model(model)
          .price(deleteSpaces(price))
          .mileage(mileage + "000")
          .page(url)
          .build();
  }

  private static String deleteSpaces(String string) {
    return string.trim().replaceAll(" ", "");
  }
}
