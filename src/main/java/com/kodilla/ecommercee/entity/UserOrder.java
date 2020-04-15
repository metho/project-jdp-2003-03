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
    @EqualsAndHashCode.Include
    private Long id;

    private LocalDate orderMade = LocalDate.now();

    private boolean resolved;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private User user;

    @NonNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @MapsId
    private Cart cart;

    private boolean mailSent;

    public UserOrder(LocalDate orderMade, boolean resolved, boolean mailSent, User user, Cart cart) {
        this.orderMade = orderMade;
        this.resolved = resolved;
        this.mailSent = mailSent;
        this.user = user;
        this.cart = cart;
    }

    public UserOrder(Long id, LocalDate orderMade, boolean resolved, boolean mailSent, User user, Cart cart) {
        this.id = id;
        this.orderMade = orderMade;
        this.resolved = resolved;
        this.mailSent = mailSent;
        this.user = user;
        this.cart = cart;
    }
}
