package interfaces;

import entity.ElectricCar;
import entity.ICECar;
import entity.OtherCar;

import java.util.Map;

public interface CarFactory {
  ElectricCar createElectricCar(Map<String, String> carMap);

  ICECar createICECar(Map<String, String> carMap);

  OtherCar createOtherCar(Map<String, String> carMap);

}
