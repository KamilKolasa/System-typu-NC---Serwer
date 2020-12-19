package com.app.model;

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
@Table(name = "PackTvs")
public class PackTv {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinTable(
            name = "tvPack_channel",
            joinColumns = {@JoinColumn(name = "tvPack_id")},
            inverseJoinColumns = {@JoinColumn(name = "channel_id")}
    )
    private Set<Channel> channels = new LinkedHashSet<>();

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "packTv", fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Pack> packs = new LinkedHashSet<>();
}
