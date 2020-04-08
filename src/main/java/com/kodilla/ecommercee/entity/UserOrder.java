package com.kodilla.ecommercee.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
@Entity
public class UserOrder {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long Id;

    private LocalDate orderMade;

    private boolean resolved;

    private boolean mailSend;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_ID")
    private User user;

    @NonNull
    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    private Cart cart;
}
