package com.xprotec.app.model;

import java.time.LocalDateTime;

public class Sale {
    private Integer id;
    private LocalDateTime dateTime;

    public Sale() {
    }

    public Sale(Integer id, LocalDateTime dateTime) {
        this.id = id;
        this.dateTime = dateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                '}';
    }
}
