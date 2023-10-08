package com.mercadonarest.infraestructure.postgres.entities;

import com.mercadonarest.domain.model.Barcode;
import com.mercadonarest.domain.model.Proveedor;
import com.mercadonarest.infraestructure.postgres.entities.constraints.ProveedorSizeConstraint;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "proveedor")
@NoArgsConstructor
@Getter
@Setter
public class ProveedorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ProveedorSizeConstraint
    @Column(unique=true)
    private Long code;
    @NotNull()
    private String name;

    public Proveedor toProveedor() {
        Proveedor proveedor = new Proveedor();
        BeanUtils.copyProperties(this, proveedor);
        return proveedor;
    }

}
