package ro.ubb.movie_catalog.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;
import ro.ubb.movie_catalog.client.ui.Console;


public class ClientApp {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        "ro.ubb.movie_catalog.client.config"
                );

        RestTemplate restTemplate = context.getBean(RestTemplate.class);
        Console console = new Console(restTemplate);
        console.runConsole();
    }
}
