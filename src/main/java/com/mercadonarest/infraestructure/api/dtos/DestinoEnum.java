package com.mercadonarest.infraestructure.api.dtos;

public enum DestinoEnum {
    MERCADONA_ESPANA("Mercadona España"),
    MERCADONA_PORTUGAL("Mercadona Portugal"),
    ALMACENES("Almacenes"),
    OFICINAS_MERCADONA("Oficinas mercadona"),
    COLMENAS("Colmenas");

    private String name;

    DestinoEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
