package com.mercadonarest.domain.persistence;

import com.mercadonarest.domain.model.Barcode;
import com.mercadonarest.domain.model.Proveedor;

public interface ProveedorPersistence {
    public Proveedor findByCode(Long code);
    public Proveedor create(Proveedor proveedor);
    public Proveedor update(Long code,Proveedor proveedor);
    public void delete(Long code);
    boolean existByCode(Long code);
}
