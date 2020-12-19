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
@Table(name = "packs")
public class Pack {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private Integer discount;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "packPhone_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private PackPhone packPhone;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "packInternet_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private PackInternet packInternet;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "packTv_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private PackTv packTv;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "pack", fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<BuyPack> buyPacks = new LinkedHashSet<>();
}
