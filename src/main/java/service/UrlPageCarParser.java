package service;

import entity.Car;
import entity.UrlSingletone;
import listener.AddParseListener;
import listener.LogParseListener;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import utils.JsonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class UrlPageCarParser {

  private static final String URL_TEMPLATE = "https://auto.ria.com/search/?indexName=auto&verified.VIN=1&categories.main.id=1&abroad.not=0&custom.not=1&sellerType=1&damage.not=1&spareParts=0&confiscated=0&credit=0&page=";

  private final List<Car> carList = Collections.synchronizedList(new ArrayList<>());
  private final List<Thread> threadList = new ArrayList<>();

  public List<Car> parseCarsToFile(int pageNumber) {
    Document doc = null;
    try {
      doc = Jsoup.connect(UrlSingletone.getPathSingletone(buildUrl(pageNumber)).toString()).get();
    } catch (IOException e) {
      e.printStackTrace();
    }

    List<Element> listOfElements = requireNonNull(doc).getElementsByClass("item ticket-title");

    for (Element e :
          listOfElements) {
      String text = e.html();
      e.select("div[footer_ticket]");
      String url = text.substring(text.indexOf("https"), text.indexOf("class") - 2);

      CarPageParser carPageParser = new CarPageParser(url, carList);
      carPageParser.getEvents().subscribe("parse", new LogParseListener());

      Thread thread = new Thread(carPageParser);
      threadList.add(thread);
      thread.start();
      carPageParser.getEvents().subscribe("add", new AddParseListener());
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
}
