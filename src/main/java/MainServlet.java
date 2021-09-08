import entity.Car;
import service.CarService;
import utils.JsonUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet("/cars")

public class MainServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

    resp.setContentType("text/html");

    try (PrintWriter out = resp.getWriter()) {
      Optional<String> optionalParameter = Optional.ofNullable(req.getParameter("page"));
      int pageNumber = Integer.parseInt(optionalParameter.orElse("1"));
      List<Car> parsedCarList = new CarService().parseCarsToFile(pageNumber);
      out.println(parsedCarList);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    List<Car> parsedCarList;
    parsedCarList = new CarService().getCarFromJson(JsonUtils.getBody(req));
    try (PrintWriter out = resp.getWriter()) {
      out.println(parsedCarList);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}