package service;

import entity.Car;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import utils.ParseUtils;

import java.util.List;

public class CarPageParser implements Runnable {
  private final String url;
  private final List<Car> carList;

  public CarPageParser(String url, List<Car> carList) {
    this.url = url;
    this.carList = carList;
  }

  @Override
  public void run() {
    try {
      Document carPage = Jsoup.connect(url).get();
      Car car = ParseUtils.parseCar(carPage, url);
      carList.add(car);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}


