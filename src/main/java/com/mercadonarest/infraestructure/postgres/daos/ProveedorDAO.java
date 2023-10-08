package com.mercadonarest.infraestructure.postgres.daos;

import com.mercadonarest.infraestructure.postgres.entities.BarcodeEntity;
import com.mercadonarest.infraestructure.postgres.entities.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProveedorDAO extends JpaRepository<ProveedorEntity, Long> {
    @Query("select p from ProveedorEntity p where p.code = ?1")
    Optional<ProveedorEntity> findByCode(Long code);

}
