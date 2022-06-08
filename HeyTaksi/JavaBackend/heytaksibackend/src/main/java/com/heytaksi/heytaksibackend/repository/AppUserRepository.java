package com.heytaksi.heytaksibackend.repository;

import com.heytaksi.heytaksibackend.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findAppUserByUserEmail(String email);

    Optional<List<AppUser>> findAllByRoleAndIsAvailable(String role, Boolean isAvailable);
}
