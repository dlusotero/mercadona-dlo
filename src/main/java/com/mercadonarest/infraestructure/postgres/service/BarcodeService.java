package com.mercadonarest.infraestructure.postgres.service;

import com.mercadonarest.domain.exceptions.ConflictException;
import com.mercadonarest.domain.model.Barcode;
import com.mercadonarest.domain.persistence.BarcodePersistence;
import com.mercadonarest.domain.persistence.CodigoProductoPersistence;
import com.mercadonarest.domain.persistence.ProveedorPersistence;
import com.mercadonarest.infraestructure.api.dtos.BarcodeDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BarcodeService {
    final BarcodePersistence barcodePersistence;
    final ProveedorPersistence proveedorPersistence;
    final CodigoProductoPersistence codigoProductoPersistence;

    public BarcodeService(BarcodePersistence barcodePersistence, ProveedorPersistence proveedorPersistence, CodigoProductoPersistence codigoProductoPersistence) {
        this.barcodePersistence = barcodePersistence;
        this.proveedorPersistence = proveedorPersistence;
        this.codigoProductoPersistence = codigoProductoPersistence;
    }


    @Transactional(readOnly = true)
    public BarcodeDTO getBarcode(Long code) {
        Barcode barcode =  this.barcodePersistence.findByCode(code);
        BarcodeDTO barcodeDTO = new BarcodeDTO(barcode.getCode());
        barcodeDTO.completeInformation();
        barcodeDTO.setValorProveedor(this.proveedorPersistence.findByCode(barcodeDTO.getProveedor()).getName());
        this.codigoProductoPersistence.findByCode(barcodeDTO.getCodigoProducto());
        return barcodeDTO;
    }


    public BarcodeDTO create(Barcode barcode) {
        BarcodeDTO barcodeDTO = new BarcodeDTO(barcode.getCode());
        barcodeDTO.completeInformation();

        if(this.barcodePersistence.existByCode(barcode.getCode())){
            throw new ConflictException("Ya existe el barcode");
        }

        if(barcodeDTO.getValorDigitoDestino() == null){
            throw new ConflictException("Digito de destino no válido");

        }

        this.proveedorPersistence.findByCode(barcodeDTO.getProveedor());
        this.codigoProductoPersistence.findByCode(barcodeDTO.getCodigoProducto());
        barcodeDTO.setValorProveedor(this.proveedorPersistence.findByCode(barcodeDTO.getProveedor()).getName());
        this.barcodePersistence.create(barcode);
        return barcodeDTO;
    }

    public BarcodeDTO update(Long code, Barcode barcode) {
        BarcodeDTO barcodeDTO = new BarcodeDTO(barcode.getCode());
        barcodeDTO.completeInformation();
        if(barcodeDTO.getValorDigitoDestino() == null){
            throw new ConflictException("Digito de destino no válido");

        }
        this.proveedorPersistence.findByCode(barcodeDTO.getProveedor());
        this.codigoProductoPersistence.findByCode(barcodeDTO.getCodigoProducto());
        barcodeDTO.setValorProveedor(this.proveedorPersistence.findByCode(barcodeDTO.getProveedor()).getName());
        barcodeDTO.setCode(this.barcodePersistence.update(code,barcode).getCode());
        return barcodeDTO;
    }

    public void delete(Long code) {
        this.barcodePersistence.delete(code);
    }
}
