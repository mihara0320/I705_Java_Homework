package Homeworks.Homework_4;

import Homeworks.Homework_4.entity.Log;
import Homeworks.Homework_4.entity.NewsHeadlines;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.persistence.*;
import java.io.IOException;

import java.util.Date;
import java.util.List;

/**
 * Created by masaki on 4/29/2017.
 */
public class HeadlineCollector {
    private static EntityManager em;
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test1");
    private static String bbc = "http://www.bbc.com/news";

    static Document document;
    static Elements headlines;

    public static void run() {
        if(getConnection()){
            em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();

            getHeadlines();

            em.close();
            em.getTransaction().commit();
        } else {
            System.out.println();
        }
    }
    public static void listLogs() {
        try {
            em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            List<Log> list = em.createQuery("select l from Log l order by l.id", Log.class).getResultList();

            for (Log log : list) {

                Date date = log.getDate();
                String title = log.getNewsHeadlines().getTitle();
                String url = log.getNewsHeadlines().getURL();
                System.out.println("==============================================================");
                System.out.println("Date: " + date);
                System.out.println("Title: " + title);
                System.out.println("URL: " + url );
                System.out.println("==============================================================");

            }
            System.out.println();
            em.close();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("[-] DB does not exist");
        }
    }
    public static void cleanDB(){
        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<Log> logs = em.createQuery("DELETE * FROM Log", Log.class).getResultList();
        List<NewsHeadlines> newsHeadlines = em.createQuery("DELETE * FROM NewsHeadlines", NewsHeadlines.class).getResultList();

        em.persist(logs);
        em.persist(newsHeadlines);
        em.close();
        em.getTransaction().commit();
    }
    private static boolean getConnection(){
        try {
            document = Jsoup.connect(bbc).get();
            System.out.println("[+] Page loaded");
            return true;
        } catch (IOException e) {
            System.out.println("[-] Could not load the page");
            return false;
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
    private static void getHeadlines(){
        Date date = new Date();
        headlines = document.select("a.gs-c-promo-heading");
        for (Element headline : headlines) {

            String title = headline.select("h3").text();
            String url = getLink(String.valueOf(headline.attr("href")));

            NewsHeadlines newsHeadlines = null;

            TypedQuery<NewsHeadlines> query = null;
            try {
                query = em.createQuery("from NewsHeadlines where firstName like(:title)", NewsHeadlines.class);
                query.setParameter("title", title);
                query.setMaxResults(1);
                try {
                    newsHeadlines = query.getSingleResult();
                } catch (NoResultException e) {}
            } catch (Exception e) {}

            if (newsHeadlines == null) {
                newsHeadlines = new NewsHeadlines();
                newsHeadlines.setTitle(title);
                newsHeadlines.setURL(url);

                em.persist(newsHeadlines);
                Log log = new Log();
                log.setDate(date);
                log.setNewsHeadlines(newsHeadlines);
                em.persist(log);
            }
        }

    }

}


