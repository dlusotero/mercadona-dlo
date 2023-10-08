package com.mercadonarest.infraestructure.postgres.persistence;

import com.mercadonarest.domain.exceptions.NotFoundException;
import com.mercadonarest.domain.model.Barcode;
import com.mercadonarest.domain.persistence.BarcodePersistence;
import com.mercadonarest.infraestructure.postgres.daos.BarcodeDAO;
import com.mercadonarest.infraestructure.postgres.entities.BarcodeEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BarcodePersistenceImpl implements BarcodePersistence {
    private final BarcodeDAO barcodeDAO;

    public BarcodePersistenceImpl(BarcodeDAO barcodeDAO) {
        this.barcodeDAO = barcodeDAO;
    }

    @Override
    public Barcode findByCode(Long code) {
        Optional<BarcodeEntity> optional = this.barcodeDAO.findByCode(code);
        if(optional.isEmpty()){
            throw new NotFoundException();
        }
        else {
            return optional.get().toBarcode();
        }
    }

    @Override
    public boolean existByCode(Long code) {
        return this.barcodeDAO.findByCode(code).isPresent();
    }

    @Override
    public Barcode create(Barcode barcode) {
        BarcodeEntity barcodeEntity = new BarcodeEntity();
        barcodeEntity.setCode(barcode.getCode());
        return this.barcodeDAO.save(barcodeEntity).toBarcode();
    }

    @Override
    public Barcode update(Long code,Barcode barcode) {
        Optional<BarcodeEntity> optional = this.barcodeDAO.findByCode(code);
        if(optional.isEmpty()){
            throw new NotFoundException();
        }
        else{
            BarcodeEntity updateEntity = optional.get();
            updateEntity.setCode(barcode.getCode());
            return this.barcodeDAO.save(updateEntity).toBarcode();
        }

    }

    @Override
    public void delete(Long code) {
        Optional<BarcodeEntity> optional = this.barcodeDAO.findByCode(code);
        if(optional.isEmpty()){
            throw new NotFoundException();
        }
        else {
            this.barcodeDAO.delete(optional.get());
        }
    }
}
