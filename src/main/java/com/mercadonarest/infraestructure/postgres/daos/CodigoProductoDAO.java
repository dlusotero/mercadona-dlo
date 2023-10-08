package com.mercadonarest.infraestructure.postgres.daos;

import com.mercadonarest.infraestructure.postgres.entities.BarcodeEntity;
import com.mercadonarest.infraestructure.postgres.entities.CodigoProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodigoProductoDAO extends JpaRepository<CodigoProductoEntity, Long> {
    @Query("select cp from CodigoProductoEntity cp where cp.code = ?1")
    Optional<CodigoProductoEntity> findByCode(Long code);

}
