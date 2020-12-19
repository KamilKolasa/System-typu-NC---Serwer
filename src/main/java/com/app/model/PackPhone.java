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
@Table(name = "PackPhones")
public class PackPhone {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private String limitedConversation; //in minutes
    private String limitedInternet; //in Gb

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "packPhone", fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Pack> packs = new LinkedHashSet<>();
}
