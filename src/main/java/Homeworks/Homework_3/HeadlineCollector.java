package Homeworks.Homework_3;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;


/**
 * Created by masaki on 4/21/2017.
 */
public class HeadlineCollector {
    static String rootDir = System.getProperty("user.dir");
    static Document document;
    static Elements headlines;
    static JSONObject obj = new JSONObject();
    static ArrayList<String> headlineList = new ArrayList<>();
    private static String bbc = "http://www.bbc.com/news";

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

    public static void run() throws IOException, URISyntaxException, ParseException {
        getConnection();

        parseToJson();
        update();

    }

    private static void parseToJson() throws URISyntaxException, IOException {
        Date time = new Date();

        obj.put("date", String.valueOf(time));

        HashMap<String, String> compiledList = new HashMap<>();

        headlines = document.select("a.gs-c-promo-heading");

        for (Element headline : headlines) {
            String title = headline.select("h3").text();
            headlineList.add(title);
            String url = getLink(String.valueOf(headline.attr("href")));
            compiledList.put(title, url);
        }

        obj.put("headlines", compiledList);



        try (FileWriter file = new FileWriter(rootDir + "/result.json" )) {

            file.write(obj.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void update() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object result = parser.parse(new FileReader(rootDir + "/result.json"));

//        for (Iterator iterator = obj.keySet().iterator(); iterator.hasNext(); ) {
//                String key = (String) iterator.next();
//                if (key == "headlines") {
//                    HashMap<String, String> h = (HashMap<String, String>) obj.get(key);
//                    for (int i = 0; i < headlineList.size(); i++) {
//                        System.out.println(h.get(headlineList.get(i)));
//                    }
//                }
//        }

        System.out.println(result);
    }


}
