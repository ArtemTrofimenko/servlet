import entity.Car;
import service.CarFacade;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/cars")

public class MainServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    resp.setContentType("application/json");
    try (PrintWriter out = resp.getWriter()) {
      CarFacade loader = new CarFacade();
      List<Car> parsedCarList = loader.loadCars(req, "url");
      out.println(parsedCarList);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    resp.setContentType("application/json");
    List<Car> parsedCarList;
    try (PrintWriter out = resp.getWriter()) {
      CarFacade loader = new CarFacade();
      parsedCarList = loader.loadCars(req, "file");
      out.println(parsedCarList);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}