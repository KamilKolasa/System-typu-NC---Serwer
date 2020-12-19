package com.app.model;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "channels")
public class Channel {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Category category;

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "channels")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<PackTv> packTvs = new LinkedHashSet<>();
}
