package com.kodilla.ecommercee.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class UserOrder {

    @Id
    @GeneratedValue
    private Long Id;

    private LocalDate orderMade = LocalDate.now();

    private boolean resolved;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_ID", nullable = false)
    private User user;

    @NonNull
    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    private Cart cart;
}
