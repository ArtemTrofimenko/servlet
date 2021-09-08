package service;

import entity.Car;
import interfaces.CarInterface;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import utils.JsonUtils;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class CarService {

  private final List<Car> carList = Collections.synchronizedList(new ArrayList<>());
  private final List<Thread> threadList = new ArrayList<>();

  private static final String URL_TEMPLATE = "https://auto.ria.com/search/?indexName=auto&verified.VIN=1&categories.main.id=1&abroad.not=0&custom.not=1&sellerType=1&damage.not=1&spareParts=0&confiscated=0&credit=0&page=";
  private static final String JSON_PATH = "C:\\Users\\artem.trofimenko\\car.json";
  private static final String UAH = "UAH";
  private static final String EURO = "EURO";
  private static final String USD = "USD";
  private static final String MILEAGE_UNIT = "mileageUnit";
  private static final String CURRENCY = "currency";

  public List<Car> parseCarFromJson() {

    ObjectMapper objectMapper = new ObjectMapper();
    List<Car> carList = new ArrayList<>();
    try {
      carList = objectMapper.readValue(Paths.get(JSON_PATH).toFile(),
            new TypeReference<List<Car>>() {
            });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return carList;
  }

  public List<Car> getCarFromJson(JsonNode body) {
    List<Car> parsedCarList = new CarService().parseCarFromJson();

    Optional<String> mileageUnitOptional = Optional.ofNullable(body.get(MILEAGE_UNIT).getTextValue());
    Optional<String> currencyOptional = Optional.ofNullable(body.get(CURRENCY).getTextValue());

    CarInterface<Car, String, String> carInterface = (car, mileageUnit, currency) -> {
      adaptMileage(car, mileageUnit);
      adaptCurrency(car, currency);
    };

    parsedCarList
          .forEach(car -> carInterface.changeForView(
                car, mileageUnitOptional.orElse("kilometers"), currencyOptional.orElse(USD)));
    return parsedCarList;
  }

  public List<Car> parseCarsToFile(int pageNumber) {

    Document doc = null;
    try {
      doc = Jsoup.connect(buildUrl(pageNumber)).get();
    } catch (IOException e) {
      e.printStackTrace();
    }

    List<Element> listOfElements = requireNonNull(doc).getElementsByClass("item ticket-title");

    for (Element e :
          listOfElements) {
      String text = e.html();
      e.select("div[footer_ticket]");
      String url = text.substring(text.indexOf("https"), text.indexOf("class") - 2);
      System.out.println("Start No: " + url);
      Thread thread = new Thread(new CarPageParser(url, carList));
      threadList.add(thread);
      thread.start();
    }
    threadList.forEach(thread -> {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    JsonUtils.packCarList(carList);
    return carList;
  }

  public String buildUrl(int pageNumber) {
    return URL_TEMPLATE + pageNumber + "&size=100";
  }

  private void adaptMileage(Car car, String mileageUnit) {
    if ("miles".equals(mileageUnit)) {
      car.setMileage(car.getMileageInMiles());
    } else {
      car.setMileage(car.getMileageInKm());
    }
  }

  private void adaptCurrency(Car car, String currency) {
    switch (currency) {
    case (UAH):
      car.setPrice(car.getUahPrice());
      break;
    case (EURO):
      car.setPrice(car.getEuroPrice());
      break;
    default:
      car.setPrice(car.getUsdPrice());
      break;
    }
  }
}
