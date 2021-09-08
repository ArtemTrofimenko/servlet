package interfaces;

@FunctionalInterface
public interface CarInterface<C, M, Cu> {
  void changeForView(C c, M m, Cu cu);
}
