package Homeworks.Homework_4.src;

import Homeworks.Homework_4.src.entity.Log;
import Homeworks.Homework_4.src.entity.NewsHeadlines;
import org.hibernate.Session;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

/**
 * Created by masaki on 4/26/2017.
 */
public class HeadlineCollector {

//    static EntityManager em;
    static Session session;
    static NewsHeadlines newsHeadlineModel = new NewsHeadlines();
    static Log logModel = new Log();

    private static String bbc = "http://www.bbc.com/news";

//    static String rootDir = System.getProperty("user.dir");
    static Document document;
    static Elements headlines;
    static boolean loadSucess;


    private static void getConnection(){
        try {
            document = Jsoup.connect(bbc).get();
            System.out.println("[+] Page loaded");
            loadSucess = true;
        } catch (IOException e) {
            System.out.println("[-] Could not load the page");
            loadSucess = false;
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

    public static void run() throws URISyntaxException {
        getConnection();

        if (loadSucess) {
            Date date = new Date();
            logModel.setDate(date);

            headlines = document.select("a.gs-c-promo-heading");

            for (Element headline : headlines) {
                String title = headline.select("h3").text();
                String url = getLink(String.valueOf(headline.attr("href")));
                if (title != null && url != null){
                    newsHeadlineModel.setTitle(title);
                    newsHeadlineModel.setURL(url);
                }
            }

            logModel.setNewsHeadlines(newsHeadlineModel);
        }

        session.persist(logModel);
        session.persist(newsHeadlineModel);

    }

    public static void listHeadlines(){
//        Criteria criteria = (Criteria) session.createQuery("select d from Car d order by d.id").list();
        List<Log> logs = session.createQuery("select l from Log l").list();
//        List<Log> logs = session.createQuery("from Log", Log.class).getResultList();

        for ( Log log: logs) {
            System.out.println(log);
        }
    }



}
