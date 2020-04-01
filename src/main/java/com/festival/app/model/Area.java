package com.festival.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "area")
@Data
@NoArgsConstructor
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NonNull
    private String name;

    private String genre;

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL)
    private Set<Artist> artists;

    @ManyToOne
    @JoinColumn(name = "festival_id", referencedColumnName = "id", nullable = false)
    private Festival festival;
}
