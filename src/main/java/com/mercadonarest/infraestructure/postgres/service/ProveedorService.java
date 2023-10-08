package com.mercadonarest.infraestructure.postgres.service;

import com.mercadonarest.domain.exceptions.ConflictException;
import com.mercadonarest.domain.model.Barcode;
import com.mercadonarest.domain.model.Proveedor;
import com.mercadonarest.domain.persistence.BarcodePersistence;
import com.mercadonarest.domain.persistence.CodigoProductoPersistence;
import com.mercadonarest.domain.persistence.ProveedorPersistence;
import com.mercadonarest.infraestructure.api.dtos.BarcodeDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProveedorService {
    final ProveedorPersistence proveedorPersistence;

    public ProveedorService(ProveedorPersistence proveedorPersistence) {
        this.proveedorPersistence = proveedorPersistence;
    }


    @Transactional(readOnly = true)
    public Proveedor get(Long code) {
        return this.proveedorPersistence.findByCode(code);
    }


    public Proveedor create(Proveedor proveedor) {

        if(this.proveedorPersistence.existByCode(proveedor.getCode())){
            throw new ConflictException("Ya existe el proveedor");
        }
        return this.proveedorPersistence.create(proveedor);
    }

    public Proveedor update(Long code, Proveedor proveedor) {
        return this.proveedorPersistence.update(code,proveedor);
    }

    public void delete(Long code) {
        this.proveedorPersistence.delete(code);
    }
}
