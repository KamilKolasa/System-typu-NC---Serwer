package com.app.model;

import com.app.model.enums.Quality;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private Integer yearProduction;
    private String description;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Quality quality;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Category category;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "film", fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<BuyFilm> buyFilms = new LinkedHashSet<>();
}
