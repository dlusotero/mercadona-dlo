package com.mercadonarest.infraestructure.api.resources;

import com.mercadonarest.domain.model.Proveedor;
import com.mercadonarest.domain.model.Proveedor;
import com.mercadonarest.infraestructure.postgres.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping("/{code}")
    @Cacheable(value = "proveedor_cache", key = "{#code}")
    public Proveedor getInformation(@PathVariable Long code) {
        return this.proveedorService.get(code);
    }

    @PostMapping()
    public Proveedor create( @RequestBody Proveedor proveedor) {
        return this.proveedorService.create(proveedor);
    }

    @PutMapping("/{code}")
    public Proveedor update(@PathVariable Long code, @Valid @RequestBody Proveedor proveedor) {
        return this.proveedorService.update(code,proveedor);
    }

    @DeleteMapping("/{code}")
    public void delete(@PathVariable Long code) {
        this.proveedorService.delete(code);
    }

}
