package com.mercadonarest.infraestructure.postgres.entities;

import com.mercadonarest.domain.model.Barcode;
import com.mercadonarest.infraestructure.postgres.entities.constraints.BarcodeSizeConstraint;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "barcode")
@NoArgsConstructor
@Getter
@Setter
public class BarcodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @BarcodeSizeConstraint
    @Column(unique=true)
    private Long code;

    public Barcode toBarcode() {
        Barcode barcode = new Barcode();
        BeanUtils.copyProperties(this, barcode);
        return barcode;
    }

}
