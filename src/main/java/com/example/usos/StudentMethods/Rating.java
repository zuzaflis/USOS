package com.example.usos.StudentMethods;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "Ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rating_id;

    @Column(name = "rate")
    Integer rate;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    Subject subject;
    @Column(name = "date")
    Date date;
    @Column(name = "additionalInfo")
    String additionalInfo;

    public Long getRating_id() {
        return rating_id;
    }

    public void setRating_id(Long rating_id) {
        this.rating_id = rating_id;
    }

    public Rating(){}
    public Rating(Integer rate, Subject subject, Date date, String additionalInfo) {
        this.rate = rate;
        this.subject = subject;
        this.date = date;
        this.additionalInfo = additionalInfo;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
