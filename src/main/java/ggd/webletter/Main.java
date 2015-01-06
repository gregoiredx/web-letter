package ggd.webletter;

import ggd.webletter.config.WebServer;

public class Main {

    public static void main(String[] args) {
        Integer port = Integer.valueOf(System.getenv("PORT"));
        System.setProperty("DATABASE_URL", System.getenv("DATABASE_URL"));
        WebServer.create(port).startAndWait();
    }

}
