package com.app.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "buyPacks")
public class BuyPack {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate dateStart;
    private LocalDate dateEnd;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pack_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Pack pack;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;
}
