package Homeworks.Homework_3;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
/**
 * Created by masaki on 4/21/2017.
 */
public class HeadlineCollector {

    private static String bbc = "http://www.bbc.com/news";

    static String rootDir = System.getProperty("user.dir");
    static Document document;
    static Elements headlines;

    private static void getConnection(){
        try {
            document = Jsoup.connect(bbc).get();
            System.out.println("[+] Page loaded");
        } catch (IOException e) {
            System.out.println("[-] Could not load the page");
        }
    }

    private static String getLink(String path) {
        String link = "";
        if(path.startsWith("http")){
        } else {
            String root = bbc;
            link = root.replace("/news", path);
        }
        return link;
    }

    public static void run() throws IOException, URISyntaxException, ParseException, java.text.ParseException {
        getConnection();
        parseToJson();
    }

    private static void parseToJson() throws URISyntaxException, IOException, ParseException, java.text.ParseException {
        String pattern = "MM/dd/yyyy";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String date = String.valueOf(format.format(new Date())).replace("/", "_");
        Date time = new Date();

        headlines = document.select("a.gs-c-promo-heading");

        JSONObject updateList = new JSONObject();
        HashMap<String, String> compiledList = new HashMap<>();

        for (Element headline : headlines) {
            String title = headline.select("h3").text();
            String url = getLink(String.valueOf(headline.attr("href")));
            compiledList.put(title, url);
        }
        updateList.put(String.valueOf(time), compiledList);

        String fileLocation = rootDir + ""+ "/src/main/java/Homeworks/Homework_3/result/" + date + ".json";
        System.out.println(fileLocation);

        try (FileWriter file = new FileWriter(fileLocation)) {
            file.write(updateList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(updateList);
    }

//    private static void update() throws IOException, ParseException {
//        JSONParser parser = new JSONParser();
//        Object result = parser.parse(new FileReader(rootDir + "/result.json"));
//
//        for (Iterator iterator = obj.keySet().iterator(); iterator.hasNext(); ) {
//                String key = (String) iterator.next();
//
//                HashMap<String, String> h = (HashMap<String, String>) obj.get(key);
//                for (int i = 0; i < headlineLog.size(); i++) {
//                    System.out.println(h.get(headlineLog.get(i)));
//                }
//        }

//    }

}
