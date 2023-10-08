package com.mercadonarest.infraestructure.api.resources;

import com.mercadonarest.domain.model.Barcode;
import com.mercadonarest.infraestructure.api.dtos.BarcodeDTO;
import com.mercadonarest.infraestructure.postgres.service.BarcodeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/barcode")
public class BarcodeController {

    @Autowired
    private final BarcodeService barcodeService;

    public BarcodeController(BarcodeService barcodeService) {
        this.barcodeService = barcodeService;
    }

    @GetMapping("/{code}")
    @Cacheable(value = "barcode_cache", key = "{#code}")
    public BarcodeDTO getInformation(@PathVariable Long code) {
        return this.barcodeService.getBarcode(code);
    }

    @PostMapping()
    public BarcodeDTO create( @RequestBody Barcode barcode) {
        return this.barcodeService.create(barcode);
    }

    @PutMapping("/{code}")
    public BarcodeDTO update(@PathVariable Long code, @Valid @RequestBody Barcode barcode) {
        return this.barcodeService.update(code,barcode);
    }

    @DeleteMapping("/{code}")
    public void delete(@PathVariable Long code) {
        this.barcodeService.delete(code);
    }

}
