package service;

import entity.Car;
import entity.OtherCar;
import interfaces.CurrencyStrategy;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import strategy.EURStrategy;
import strategy.UAHStrategy;
import strategy.USDStrategy;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileCarParser {

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
            new TypeReference<List<OtherCar>>() {
            });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return carList;
  }

  public List<Car> getCarFromJson(JsonNode body) {
    List<Car> parsedCarList = new FileCarParser().parseCarFromJson();

    Optional<String> mileageUnitOptional = Optional.ofNullable(body.get(MILEAGE_UNIT).getTextValue());
    Optional<String> currencyOptional = Optional.ofNullable(body.get(CURRENCY).getTextValue());

    parsedCarList
          .forEach(car -> changeForView(
                car, mileageUnitOptional.orElse("kilometers"), currencyOptional.orElse(USD)));
    return parsedCarList;
  }

  private void adaptMileage(Car car, String mileageUnit) {
    if ("miles".equals(mileageUnit)) {
      car.setMileage(car.getMileageInMiles());
    } else {
      car.setMileage(car.getMileageInKm());
    }
  }

  private void getCurrencyStrategy(Car car, String currency) {
    CurrencyStrategy currencyStrategy;
    switch (currency) {
    case (UAH):
      currencyStrategy = new UAHStrategy();
      car.setCurrencyStrategy(currencyStrategy);
      break;
    case (EURO):
      currencyStrategy = new EURStrategy();
      car.setCurrencyStrategy(currencyStrategy);
      break;
    default:
      currencyStrategy = new USDStrategy();
      car.setCurrencyStrategy(currencyStrategy);
      break;
    }
    car.setPrice(currencyStrategy.getPriceWithCurrency(car.getPrice()));
  }

  private void changeForView(Car car, String mileage, String currency) {

    adaptMileage(car, mileage);
    getCurrencyStrategy(car, currency);
  }
}
