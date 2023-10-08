package com.mercadonarest.infraestructure.postgres.daos;

import com.mercadonarest.infraestructure.postgres.entities.BarcodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BarcodeDAO extends JpaRepository<BarcodeEntity, Long> {
    @Query("select b from BarcodeEntity b where b.code = ?1")
    Optional<BarcodeEntity> findByCode(Long code);

}
