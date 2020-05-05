//package ro.ubb.movie_catalog;
//
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.xml.sax.SAXException;
//
//import ro.ubb.movie_catalog.core.domain.validators.*;
//
//
//import ro.ubb.movie_catalog.client.ui.Console;
//
//import javax.xml.parsers.ParserConfigurationException;
//import java.io.IOException;
//
//public class Main {
//    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, ValidatorException {
//
//        AnnotationConfigApplicationContext context =
//                new AnnotationConfigApplicationContext(
//                        "ro.ubb.movie_catalog"
//                );
//
//        context.getBean(Console.class).runConsole();
//
//    }
//}
