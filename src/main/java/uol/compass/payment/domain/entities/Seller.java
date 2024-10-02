package uol.compass.payment.domain.entities;

import jakarta.persistence.*;
import uol.compass.payment.application.dtos.input.SellerDTO;

import java.util.UUID;

@Entity
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID code;

    @Column(columnDefinition = "varchar(255)", nullable = false)
    private String name;

    public Seller() {
    }

    public Seller(SellerDTO sellerDTO) {
        this.name = sellerDTO.name();
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

    public String getName() {
        return name;
    }
}
