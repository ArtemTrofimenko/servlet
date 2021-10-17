package utils;

import entity.Car;
import interfaces.CarFactory;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import service.CarFactoryImpl;
import service.EventManager;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;

public class ParseUtils {

  public static Car parseCar(Document carPage) {

    Car parsedCar;
    Elements technicalInfo = carPage.select("span[class=\"argument\"]");
    Elements names = carPage.select("span[class=\"label\"]");
    Map<String, String> infoMap = getTechnicalCharacteristics(technicalInfo, names);
    String carType = infoMap.get("Двигун");

    CarFactory carFactory = new CarFactoryImpl();
    Map<String, String> carMap = buildCarMap(carPage);
    if (carType.contains("Дизель") || carType.contains("Газ") || carType.contains("Бензин")) {
      parsedCar = carFactory.createICECar(carMap);
    }
    else if (carType.contains("Електро")) {
      parsedCar = carFactory.createElectricCar(carMap);
    } else {
      parsedCar = carFactory.createOtherCar(carMap);
    }
    return parsedCar;
  }

  private static Map<String, String> getTechnicalCharacteristics(Elements technicalInfo, Elements names) {
    Map<String, String> techCharacter = new HashMap<>();
    for (int i = 0; i < technicalInfo.size(); i++) {
      String info = technicalInfo.get(i).text();
      String n = names.get(i + 1).text();
      techCharacter.put(n, info);
    }
    return techCharacter;
  }

  private static Map<String, String> buildCarMap(Document carPage) {
    Map<String, String> carMap = new HashMap<>();
    String price = requireNonNull(carPage.select("div[class=\"price_value\"]").first()).text();
    String mileage = requireNonNull(carPage.select("div[class=\"base-information bold\"]").first()).text();
    mileage = mileage.substring(0, mileage.indexOf(" "));
    price = price.substring(0, price.indexOf(" $"));
    String mark = requireNonNull(carPage.select("span[itemprop=\"brand\"]").first()).text();
    String model = requireNonNull(carPage.select("span[itemprop=\"name\"]").first()).text();
    carMap.put("mileage", mileage);
    carMap.put("price", deleteSpaces(price));
    carMap.put("mark", mark);
    carMap.put("model", model);
    carMap.put("url", carPage.location());
    return carMap;
  }

  private static String deleteSpaces(String string) {
    return string.trim().replaceAll(" ", "");
  }
}
