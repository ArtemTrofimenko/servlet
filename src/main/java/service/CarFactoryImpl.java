package service;

import entity.ElectricCar;
import entity.ICECar;
import proxy.ElectricCarProxy;
import proxy.ICECarProxy;
import entity.OtherCar;
import interfaces.CarFactory;
import proxy.OtherCarProxy;

import java.util.Map;

public class CarFactoryImpl implements CarFactory {
  @Override public ElectricCar createElectricCar(Map<String, String> carMap) {

    return new ElectricCarProxy().builder()
          .mark(carMap.get("mark"))
          .model(carMap.get("model"))
          .price(carMap.get("price"))
          .mileage(carMap.get("mileage") + "000")
          .page(carMap.get("url"))
          .build();
  }

  @Override public ICECar createICECar(Map<String, String> carMap) {
    return new ICECarProxy().builder()
          .mark(carMap.get("mark"))
          .model(carMap.get("model"))
          .price(carMap.get("price"))
          .mileage(carMap.get("mileage") + "000")
          .page(carMap.get("url"))
          .build();
  }

  @Override public OtherCar createOtherCar(Map<String, String> carMap) {
  return new OtherCarProxy().builder()
          .mark(carMap.get("mark"))
          .model(carMap.get("model"))
          .price(carMap.get("price"))
          .mileage(carMap.get("mileage") + "000")
          .page(carMap.get("url"))
          .build();
  }


}
