package com.app.model;

import com.app.model.enums.TypeInternet;
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
@Table(name = "PackInternets")
public class PackInternet {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private Integer speedDownload; //in Mb/s
    private Integer speedUpload; //in Mb/s
    @Enumerated(EnumType.STRING)
    private TypeInternet typeInternet;
    private BigDecimal price;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "packInternet", fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Pack> packs = new LinkedHashSet<>();
}
