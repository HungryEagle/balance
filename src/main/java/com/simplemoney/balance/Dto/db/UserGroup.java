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
@Table(name = "user_group")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGroup {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_id")
    private UUID group;

    @Column(name = "user_id")
    private UUID user;

    @Column(name = "deleted")
    private boolean isDeleted;

    @UpdateTimestamp
    @Column(name = "updated_at")
    Instant updatedAt;

    @CreationTimestamp
    @Column(name = "created_at")
    Instant createdAt;
}
