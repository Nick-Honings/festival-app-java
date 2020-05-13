package com.festival.app.model;


import javax.persistence.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@NoArgsConstructor
public class Festival {

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

    @Column(name = "min_age")
    private Integer minimumAge;


    @OneToMany(mappedBy = "festival", cascade = CascadeType.ALL)
    private List<Area> areas = new ArrayList<Area>();

    public void addArea(Area area){
        this.areas.add(area);
        area.setFestival(this);
    }

    public List<Area> getAreas(){return areas;}

}

