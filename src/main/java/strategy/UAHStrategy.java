package strategy;

import interfaces.CurrencyStrategy;

public class UAHStrategy implements CurrencyStrategy {
  @Override public String getPriceWithCurrency(String price) {
    return Double.parseDouble(price) * 26.7 + " UAH";
  }
}
