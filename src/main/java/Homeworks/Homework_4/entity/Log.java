package Homeworks.Homework_4.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by masaki on 4/26/2017.
 */
@Entity
@Table(name = "Log")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String url;

//    @Column(nullable = false)
//    private NewsHeadlines newsHeadlines;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //    public NewsHeadlines getNewsHeadlines() { return newsHeadlines; }
//    public void setNewsHeadlines(NewsHeadlines newsHeadlines) { this.newsHeadlines = newsHeadlines; }

}