package com.company.apelsin.repository;

import com.company.apelsin.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

    Optional<ProfileEntity> findByPhone(String phone);

    Optional<ProfileEntity> findByLogin(String login);

    Optional<ProfileEntity> findByLoginAndPassword(String login, String password);

    boolean existsByPhone(String phone);

}
