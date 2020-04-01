package com.festival.app.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NonNull
    private String name;

    private String genre;

    @ManyToOne
    @JoinColumn(name = "area_id", referencedColumnName = "id", nullable = false)
    private Area area;
}
