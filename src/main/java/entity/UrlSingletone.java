package entity;

public class UrlSingletone {
  private static volatile UrlSingletone urlSingletone;
  private final String path;

  @Override public String toString() {
    return path;
  }

  private UrlSingletone(String path) {
    this.path = path;
  }

  public static UrlSingletone getPathSingletone(String path) {
    UrlSingletone result = urlSingletone;
    if (result != null) {
      return result;
    }
    synchronized (UrlSingletone.class) {
      if (urlSingletone == null) {
        urlSingletone = new UrlSingletone(path);

      }
      return urlSingletone;
    }
  }
}