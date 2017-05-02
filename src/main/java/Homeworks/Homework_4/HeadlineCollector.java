package Homeworks.Homework_4;

import Homeworks.Homework_4.entity.Log;
import Homeworks.Homework_4.entity.NewsHeadlines;
import Homeworks.Homework_4.entity.TitleLog;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.persistence.*;
import java.io.IOException;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

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

            em.getTransaction().commit();
            em.close();

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
    public static void search(){
        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter keywords :");
        String keyword = scanner.next();

        TypedQuery<NewsHeadlines> query = em.createQuery("from NewsHeadlines where title  like :keyword", NewsHeadlines.class);
        query.setParameter("keyword", "%" + keyword + "%");

        List<NewsHeadlines> result = query.getResultList();

        if(result != null){
            for (NewsHeadlines headline: result) {
                String title = headline.getTitle();
                String url = headline.getURL();
                System.out.println("==============================================================");
                System.out.println("Title: " + title);
                System.out.println("URL: " + url );
                System.out.println("==============================================================");
            }
        } else {
            System.out.println("Nothing found");
        }

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

            TypedQuery<NewsHeadlines> query = em.createQuery("from NewsHeadlines where title like(:headline)", NewsHeadlines.class);

            query.setParameter("headline", title);
            query.setMaxResults(1);
            NewsHeadlines newsHeadlines = null;

            try {
                if(query.getSingleResult().getTitle() == null){
                    newsHeadlines = new NewsHeadlines();
                    newsHeadlines.setTitle(title);
                    newsHeadlines.setURL(url);
                    em.persist(newsHeadlines);
                }
            } catch (NoResultException e) {}


            Log log = new Log();
            log.setDate(date);
            log.setNewsHeadlines(newsHeadlines);
            em.persist(log);

        }

    }


}


