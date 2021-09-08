package utils;

import entity.Car;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class JsonUtils {
  private static final String JSON_PATH = "C:\\Users\\artem.trofimenko\\car.json";

  public static void packCarList(List<Car> car) {
    ObjectMapper mapper = new ObjectMapper();
    File file = new File(JSON_PATH);
    try (FileWriter fileWriter = new FileWriter(file, false)) {
      mapper.writeValue(fileWriter, car);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static JsonNode getBody(HttpServletRequest request) {
    JsonNode body = null;
    ObjectMapper objectMapper = new ObjectMapper();

    try {
      JsonFactory jsonFactory = objectMapper.getJsonFactory();
      JsonParser jsonParser = jsonFactory.createJsonParser(request.getReader());
      body = objectMapper.readTree(jsonParser);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return body;
  }
}
