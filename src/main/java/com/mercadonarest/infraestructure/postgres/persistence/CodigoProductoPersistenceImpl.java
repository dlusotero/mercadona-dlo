package com.mercadonarest.infraestructure.postgres.persistence;

import com.mercadonarest.domain.exceptions.NotFoundException;
import com.mercadonarest.domain.model.CodigoProducto;
import com.mercadonarest.domain.persistence.CodigoProductoPersistence;

import com.mercadonarest.infraestructure.postgres.daos.CodigoProductoDAO;
import com.mercadonarest.infraestructure.postgres.entities.CodigoProductoEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CodigoProductoPersistenceImpl implements CodigoProductoPersistence {
    private final CodigoProductoDAO codigoProductoDAO;

    public CodigoProductoPersistenceImpl(CodigoProductoDAO codigoProductoDAO) {
        this.codigoProductoDAO = codigoProductoDAO;
    }

    @Override
    public CodigoProducto findByCode(Long code) {
        Optional<CodigoProductoEntity> optional = this.codigoProductoDAO.findByCode(code);
        if(optional.isEmpty()){
            throw new NotFoundException("Codigo de producto no registrado en la BD");
        }
        else {
            return optional.get().toCodigoProducto();
        }
    }

    @Override
    public CodigoProducto create(CodigoProducto cp) {
        CodigoProductoEntity cpEntity = new CodigoProductoEntity();
        cpEntity.setCode(cp.getCode());
        return this.codigoProductoDAO.save(cpEntity).toCodigoProducto();
    }


    @Override
    public CodigoProducto update(Long code,CodigoProducto cp) {
        Optional<CodigoProductoEntity> optional = this.codigoProductoDAO.findByCode(code);
        if(optional.isEmpty()){
            throw new NotFoundException();
        }
        else{
            CodigoProductoEntity updateEntity = optional.get();
            updateEntity.setCode(cp.getCode());
            return this.codigoProductoDAO.save(updateEntity).toCodigoProducto();
        }

    }

    @Override
    public void delete(Long code) {
        Optional<CodigoProductoEntity> optional = this.codigoProductoDAO.findByCode(code);
        if(optional.isEmpty()){
            throw new NotFoundException();
        }
        else {
            this.codigoProductoDAO.delete(optional.get());
        }
    }

    @Override
    public boolean existByCode(Long code) {
        return this.codigoProductoDAO.findByCode(code).isPresent();
    }
}
