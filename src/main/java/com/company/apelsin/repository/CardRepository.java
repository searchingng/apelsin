package com.company.apelsin.repository;

import com.company.apelsin.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;

public interface CardRepository extends JpaRepository<CardEntity, Long> {

}
