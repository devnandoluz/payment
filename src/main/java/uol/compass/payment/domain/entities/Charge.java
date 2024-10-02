package uol.compass.payment.domain.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Charge {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID code;

    @Column(columnDefinition = "varchar(255)", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;

    @Column(columnDefinition = ("number(10, 2)"), nullable = false)
    private BigDecimal amount;

    public Charge() {
    }

    public Charge(String description, BigDecimal amount) {
        this.description = description;
        this.amount = amount;
    }

    @PrePersist
    public void prePersist() {
        this.code = UUID.randomUUID();
    }

    public Long getId() {
        return id;
    }

    public UUID getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Seller getSeller() {
        return seller;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
