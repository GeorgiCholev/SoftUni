package com.resellerapp.model.entity;

import com.resellerapp.model.dtos.OfferAddDto;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Condition condition;

    @ManyToOne
    @JoinTable(name = "users_offers",
            joinColumns = @JoinColumn(name = "offer_id"))
    private User seller;

    public Offer() {
    }

    public Offer(OfferAddDto dto, Condition condition, User user) {
        this.description = dto.getDescription();
        this.price = dto.getPrice();
        this.condition = condition;
        this.seller = user;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Condition getCondition() {
        return condition;
    }

    public User getSeller() {
        return seller;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Offer offer = (Offer) o;

        if (!id.equals(offer.id)) return false;
        return description.equals(offer.description);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
