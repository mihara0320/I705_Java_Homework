package Homeworks.Homework_4.src.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by masaki on 4/26/2017.
 */
@Entity
@Table(name = "NewsHeadlines")

public class NewsHeadlines {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String url;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getURL() { return url; }
    public void setURL(String url) { this.url = url; }


}
