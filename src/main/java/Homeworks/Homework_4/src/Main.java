package Homeworks.Homework_4.src;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

/**
 * Created by masaki on 4/26/2017.
 */
public class Main {
    public static void main(String[] args) throws URISyntaxException, ParseException, org.json.simple.parser.ParseException, IOException {
        HeadlineCollector headlineCollector = new HeadlineCollector();
        headlineCollector.run();
        headlineCollector.listHeadlines();
    }
}
