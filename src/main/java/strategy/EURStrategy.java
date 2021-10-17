package strategy;

import interfaces.CurrencyStrategy;

public class EURStrategy implements CurrencyStrategy {
  @Override public String getPriceWithCurrency(String price) {
    return Double.parseDouble(price) * 0.84 + " EUR";
  }
}
