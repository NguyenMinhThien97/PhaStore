package com.store.pharmacy.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "SENDER_INFO")
public class SenderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_SENDER_INFO")
    private long idSenderInfo;

    @Column(name="ID_SHOP")
    private Long idShop;

    @Column(name="ID_USER", length = 11)
    private String idUser;

    @Column(name="SENDER_TYPE", length = 4)
    private String senderType;

    @Column(name="SENDER_API_KEY", length = 255, nullable = false)
    private String senderAPIKey;

    @Column(name="MAIL_SENDER", length = 100, nullable = false)
    private String mailSender;

    @Column(name="ENABLED")
    private boolean enabled;

    @Column(name="CREATED_BY", nullable = false)
    private String createdBy;

    @Column(name="CREATED_AT", nullable = false)
    private LocalDateTime createdAt;
}
