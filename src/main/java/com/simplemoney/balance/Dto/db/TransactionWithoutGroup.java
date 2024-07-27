package com.simplemoney.balance.Dto.db;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "transaction_without_group")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionWithoutGroup {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_one_id")
    private UUID userOneId;

    @Column(name = "user_two_id")
    private UUID userTwoId;

    @Column(name = "amount")
    private float amount;

    @Column(name = "description")
    private String description;

    @UpdateTimestamp
    @Column(name = "updated_at")
    Instant updatedAt;

    @CreationTimestamp
    @Column(name = "created_at")
    Instant createdAt;
}
