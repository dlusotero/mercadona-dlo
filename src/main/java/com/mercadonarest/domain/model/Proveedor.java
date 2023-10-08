package com.mercadonarest.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mercadonarest.infraestructure.postgres.entities.constraints.ProveedorSizeConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Proveedor {
    @ProveedorSizeConstraint
    private Long code;
    private String name;
}
