package com.mifos.gradleproject.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private Double amount;
    private String type; // DEPOSIT or WITHDRAWAL

    @ManyToOne
    @JoinColumn(name = "account_id")
    private SavingsAccount account;
}
