package com.mercadonarest.infraestructure.postgres.entities;

import com.mercadonarest.domain.model.Barcode;
import com.mercadonarest.domain.model.CodigoProducto;
import com.mercadonarest.infraestructure.postgres.entities.constraints.CodigoProductoSizeConstraint;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "codigo_producto")
@NoArgsConstructor
@Getter
@Setter
public class CodigoProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CodigoProductoSizeConstraint
    @Column(unique=true)

    private Long code;

    public CodigoProducto toCodigoProducto() {
        CodigoProducto codigoProducto = new CodigoProducto();
        BeanUtils.copyProperties(this, codigoProducto);
        return codigoProducto;
    }

}
