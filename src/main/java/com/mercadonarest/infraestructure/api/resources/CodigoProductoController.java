package com.mercadonarest.infraestructure.api.resources;

import com.mercadonarest.domain.model.CodigoProducto;
import com.mercadonarest.domain.model.CodigoProducto;
import com.mercadonarest.infraestructure.postgres.service.CodigoProductoService;
import com.mercadonarest.infraestructure.postgres.service.CodigoProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/codigo_producto")
public class CodigoProductoController {

    @Autowired
    private final CodigoProductoService codigoProductoService;

    public CodigoProductoController(CodigoProductoService codigoProductoService) {
        this.codigoProductoService = codigoProductoService;
    }

    @GetMapping("/{code}")
    @Cacheable(value = "codigo_producto_cache", key = "{#code}")
    public CodigoProducto getInformation(@PathVariable Long code) {
        return this.codigoProductoService.get(code);
    }

    @PostMapping()
    public CodigoProducto create(@RequestBody CodigoProducto codigoProducto) {
        return this.codigoProductoService.create(codigoProducto);
    }

    @PutMapping("/{code}")
    public CodigoProducto update(@PathVariable Long code, @Valid @RequestBody CodigoProducto codigoProducto) {
        return this.codigoProductoService.update(code,codigoProducto);
    }

    @DeleteMapping("/{code}")
    public void delete(@PathVariable Long code) {
        this.codigoProductoService.delete(code);
    }

}
