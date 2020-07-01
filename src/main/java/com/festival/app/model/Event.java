package com.festival.app.model;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Data
@NoArgsConstructor
public class Event extends RepresentationModel<Event> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NonNull
    private String name;

    private String location;

    private Date date;

    private String time;

    private double price;

    @Enumerated(EnumType.STRING)
    private Attendance attendance;

    @Column(name = "min_age")
    @Min(16) @Max(99)
    private Integer minimumAge;


    @OneToMany(mappedBy = "festival", cascade = CascadeType.ALL)
    private List<Area> areas = new ArrayList<Area>();

    public void addArea(Area area){
        this.areas.add(area);
        area.setEvent(this);
    }

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "applicationuser_id")
    private ApplicationUser user;

    public List<Area> getAreas(){return areas;}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getMinimumAge() {
        return minimumAge;
    }

    public void setMinimumAge(Integer minimumAge) {
        this.minimumAge = minimumAge;
    }

    public void setUser(ApplicationUser applicationUser) {
        this.user = applicationUser;
    }
}

