package strategy;

import interfaces.CurrencyStrategy;

public class USDStrategy implements CurrencyStrategy {
  @Override public String getPriceWithCurrency(String price) {
    return price + " USD";
  }
}
