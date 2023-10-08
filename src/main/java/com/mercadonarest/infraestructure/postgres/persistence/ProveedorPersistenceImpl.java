package com.mercadonarest.infraestructure.postgres.persistence;

import com.mercadonarest.domain.exceptions.ConflictException;
import com.mercadonarest.domain.exceptions.NotFoundException;
import com.mercadonarest.domain.model.Proveedor;
import com.mercadonarest.domain.persistence.ProveedorPersistence;
import com.mercadonarest.infraestructure.postgres.daos.ProveedorDAO;
import com.mercadonarest.infraestructure.postgres.entities.ProveedorEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProveedorPersistenceImpl implements ProveedorPersistence {
    private final ProveedorDAO proveedorDAO;

    public ProveedorPersistenceImpl(ProveedorDAO proveedorDAO) {
        this.proveedorDAO = proveedorDAO;
    }

    @Override
    public Proveedor findByCode(Long code) {
        Optional<ProveedorEntity> optional = this.proveedorDAO.findByCode(code);
        if(optional.isEmpty()){
            throw new NotFoundException("Proveedor no registrado en la BD");
        }
        else {
            return optional.get().toProveedor();
        }
    }

    @Override
    public Proveedor create(Proveedor proveedor) {
        ProveedorEntity proveedorEntity = new ProveedorEntity();
        proveedorEntity.setCode(proveedor.getCode());
        proveedorEntity.setName(proveedor.getName());
        return this.proveedorDAO.save(proveedorEntity).toProveedor();
    }

    @Override
    public Proveedor update(Long code,Proveedor proveedor) {
        Optional<ProveedorEntity> optional = this.proveedorDAO.findByCode(code);
        if(optional.isEmpty()){
            throw new NotFoundException();
        }
        else{
            ProveedorEntity updateEntity = optional.get();
            updateEntity.setCode(proveedor.getCode());
            updateEntity.setName(proveedor.getName());
            return this.proveedorDAO.save(updateEntity).toProveedor();
        }

    }

    @Override
    public void delete(Long code) {
        Optional<ProveedorEntity> optional = this.proveedorDAO.findByCode(code);
        if(optional.isEmpty()){
            throw new NotFoundException();
        }
        else {
            this.proveedorDAO.delete(optional.get());
        }
    }

    @Override
    public boolean existByCode(Long code) {
        return this.proveedorDAO.findByCode(code).isPresent();

    }
}
