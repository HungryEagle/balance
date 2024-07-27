package com.simplemoney.balance.Repository;

import com.simplemoney.balance.Dto.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity getByUsername(String username);
    Boolean existsByUsername(String username);
}
