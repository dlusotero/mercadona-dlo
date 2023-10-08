package com.mercadonarest.domain.persistence;

import com.mercadonarest.domain.model.CodigoProducto;

public interface CodigoProductoPersistence {
    public CodigoProducto findByCode(Long code);
    public CodigoProducto create(CodigoProducto cp);
    public CodigoProducto update(Long code,CodigoProducto cp);
    public void delete(Long code);
    boolean existByCode(Long code);
}
