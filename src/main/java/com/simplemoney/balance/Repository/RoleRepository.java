package com.simplemoney.balance.Repository;

import com.simplemoney.balance.Dto.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role getByName(String name);
}
