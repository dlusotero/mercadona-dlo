package com.mercadonarest.infraestructure.api.dtos;

import java.util.Arrays;

public enum ProvedorEnum {

    HACENDADO(8437008L),
    OTROS;

    private Long proveedor;

    ProvedorEnum(Long proveedor) {
        this.proveedor = proveedor;
    }

    ProvedorEnum() {
    }

    public ProvedorEnum getProveedorEnum(Long proveedor) {
        return Arrays.stream(ProvedorEnum.values()).filter(p -> proveedor.equals(p.proveedor)).findFirst().orElse(null);
    }
}
