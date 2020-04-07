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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private User user;

    @NonNull
    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    private Cart cart;

    private boolean mailSent;

    public UserOrder(LocalDate orderMade, boolean resolved, User user, Cart cart, boolean mailSent) {
        this.orderMade = orderMade;
        this.resolved = resolved;
        this.user = user;
        this.cart = cart;
        this.mailSent = mailSent;
    }
}
