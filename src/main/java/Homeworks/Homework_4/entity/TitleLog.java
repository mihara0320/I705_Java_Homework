package Homeworks.Homework_4.entity;

import javax.persistence.*;
/**
 * Created by masaki on 4/29/2017.
 */
@Entity
@Table(name = "TitleLog")
public class TitleLog {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false)
    private String title;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

}