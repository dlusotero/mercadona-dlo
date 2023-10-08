package com.mercadonarest.infraestructure.postgres.service;

import com.mercadonarest.domain.exceptions.ConflictException;
import com.mercadonarest.domain.model.CodigoProducto;
import com.mercadonarest.domain.persistence.CodigoProductoPersistence;
import com.mercadonarest.domain.persistence.CodigoProductoPersistence;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CodigoProductoService {
    final CodigoProductoPersistence codigoProductoPersistence;

    public CodigoProductoService(CodigoProductoPersistence codigoProductoPersistence) {
        this.codigoProductoPersistence = codigoProductoPersistence;
    }


    @Transactional(readOnly = true)
    public CodigoProducto get(Long code) {
        return this.codigoProductoPersistence.findByCode(code);
    }


    public CodigoProducto create(CodigoProducto codigoProducto) {

        if(this.codigoProductoPersistence.existByCode(codigoProducto.getCode())){
            throw new ConflictException("Ya existe el codigo del producto");
        }
        return this.codigoProductoPersistence.create(codigoProducto);
    }

    public CodigoProducto update(Long code, CodigoProducto codigoProducto) {
        return this.codigoProductoPersistence.update(code,codigoProducto);
    }

    public void delete(Long code) {
        this.codigoProductoPersistence.delete(code);
    }
}
