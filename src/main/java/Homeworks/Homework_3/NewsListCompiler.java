package Homeworks.Homework_3;

        import org.json.simple.parser.ParseException;

        import java.io.IOException;
        import java.net.URISyntaxException;

/**
 * Created by masaki on 4/21/2017.
 */
public class NewsListCompiler {

    public static void main(String[] args) throws IOException, URISyntaxException, ParseException {

        HeadlineCollector headlineCollector = new HeadlineCollector();
        headlineCollector.run();
    }
}
