package com.store.pharmacy.shop.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class ShopUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_SHOP_USER")
    private long IdShopUser;

    @Column(name="ID_SHOP")
    private long IdShop;

    @Column(name="ID_USER", length = 11)
    private String IdUser;

    @Column(name="ROLE_SHOP_USER", length = 2)
    private String RoleShopUser;

    @Column(name="STATUS", length = 2)
    private String status;

    @Column(name="ENABLED")
    private boolean enabled;

    @Column(name="CREATED_BY")
    private String createdBy;

    @Column(name="CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name="UPDATED_BY", nullable = false)
    private String updatedBy;

    @Column(name="UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;
}
