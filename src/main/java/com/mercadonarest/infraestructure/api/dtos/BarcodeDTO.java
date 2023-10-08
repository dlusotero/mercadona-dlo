package com.mercadonarest.infraestructure.api.dtos;

import lombok.Data;

@Data
public class BarcodeDTO {
    private Long code;
    private Long proveedor;
    private Long codigoProducto;
    private Long digitoDestino;

    private String valorProveedor;
    private String valorDigitoDestino;

    public BarcodeDTO(Long code) {
        this.code = code;
    }

    public void completeInformation() {
        String codeString = this.code.toString();
        this.proveedor = Long.valueOf(codeString.substring(0,7));
        this.codigoProducto = Long.valueOf(codeString.substring(7,12));
        this.digitoDestino = Long.valueOf(codeString.substring(12,13));
        setValorDigitoDestino();
    }

    private void setValorDigitoDestino(){
        if(digitoDestino >= 1 && digitoDestino <=5){
            this.valorDigitoDestino = DestinoEnum.MERCADONA_ESPANA.getName();
        }
        else if(digitoDestino == 6){
            this.valorDigitoDestino = DestinoEnum.MERCADONA_PORTUGAL.getName();
        }
        else if(digitoDestino == 8){
            this.valorDigitoDestino = DestinoEnum.ALMACENES.getName();
        }
        else if(digitoDestino == 9){
            this.valorDigitoDestino = DestinoEnum.OFICINAS_MERCADONA.getName();
        }
        else if(digitoDestino == 0){
            this.valorDigitoDestino = DestinoEnum.COLMENAS.getName();
        }
        else{
            this.valorDigitoDestino = null;
        }
    }
}
