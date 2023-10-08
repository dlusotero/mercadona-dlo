package com.mercadonarest.domain.persistence;

import com.mercadonarest.domain.model.Barcode;

public interface BarcodePersistence {
    public Barcode findByCode(Long code);
    public boolean existByCode(Long code);
    public Barcode create(Barcode barcode);
    public Barcode update(Long code,Barcode barcode);
    public void delete(Long code);


}
