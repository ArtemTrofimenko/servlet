package service;

import entity.Car;
import utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarFacade {
  public List<Car> loadCars(HttpServletRequest req, String type) {
    List<Car> carList = new ArrayList<>();
    if (type.equals("url")) {
      Optional<String> optionalParameter = Optional.ofNullable(req.getParameter("page"));
      int pageNumber = Integer.parseInt(optionalParameter.orElse("1"));
      UrlPageCarParser parser = new UrlPageCarParser();
      carList = parser.parseCarsToFile(pageNumber);
    }
    if (type.equals("file")) {
      FileCarParser parser = new FileCarParser();
      carList = parser.getCarFromJson(JsonUtils.getBody(req));
    }
    return carList;
  }
}
