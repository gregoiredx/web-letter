package ggd.webletter;

public class Main {

  public static void main(String[] args) {
    Integer port = Integer.valueOf(System.getenv("PORT"));
    WebServer.create(port).start();
  }

}
