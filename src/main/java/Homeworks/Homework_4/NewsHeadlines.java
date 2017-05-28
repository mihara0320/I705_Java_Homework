package Homeworks.Homework_4;


/**
 * Created by masaki on 4/26/2017.
 */
public class NewsHeadlines {

    private String title;
    private String url;

//    public NewsHeadlines() {
//    }

    public NewsHeadlines(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getURL() { return url; }
    public void setURL(String url) { this.url = url; }

}
