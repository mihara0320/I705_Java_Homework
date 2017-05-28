package Homeworks.Homework_4;

import Homeworks.Homework_4.entity.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.persistence.*;
import java.io.IOException;

import java.util.Date;
import java.util.List;
import java.util.Objects;
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
        getConnection();
        getHeadlines();
    }
    public static void listLogs() {
        try {
            em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            List<Log> list = em.createQuery("select l from Log l order by l.id", Log.class).getResultList();

            for (Log log : list) {

                Date date = log.getDate();
                String title = log.getTitle();
                String url = log.getUrl();
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
            System.out.println("[-] ERROR");
            System.out.println(e);
//            System.out.println("[-] DB does not exist");
        }
    }
    public static void search(){
        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter keywords :");
        String line = scanner.nextLine();
        String[] keywords = line.split(" ");

        System.out.println("Finding headlines match with keywords \""+ line + "\"");
        String queryString = "FROM Log WHERE";

        for (int i = 0; i < keywords.length; i++) {
            if(i==0){
                queryString += " (LOWER(title) LIKE LOWER(:keyword" + i + "))";
            }else {
                queryString += " AND (LOWER(title) LIKE LOWER(:keyword" + i + "))";
            }
        }
        TypedQuery<Log> query = em.createQuery(queryString, Log.class);
        for (int i = 0; i < keywords.length; i++) {
            query.setParameter("keyword" + i, "%" + keywords[i] + "%");

        }

        List<Log> result = query.getResultList();

        if(result != null){
            for (Log log: result) {
                System.out.println("==============================================================");
                System.out.println("Title: " + log.getTitle());
                System.out.println("URL: " + log.getUrl());
                System.out.println("==============================================================");
            }
        } else {
            System.out.println("[-] Nothing found");
        }

        em.close();
        em.getTransaction().commit();

    }
    private static void getConnection(){
        while (true){
            try {
                document = Jsoup.connect(bbc).get();
                System.out.println("[+] Page loaded");
                break;
            } catch (IOException e) {
                System.out.println("Now Loading ...");
            }
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
        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Date date = new Date();

        headlines = document.select("a.gs-c-promo-heading");

        for (Element headline : headlines) {
            String title = headline.select("h3").text();
            String url = getLink(String.valueOf(headline.attr("href")));

                Log log = new Log();
                log.setDate(date);
                log.setTitle(title);
                log.setUrl(url);
                em.persist(log);

        }
        em.getTransaction().commit();
        em.close();
    }
}


