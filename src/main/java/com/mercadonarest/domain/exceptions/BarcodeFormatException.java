package com.mercadonarest.domain.exceptions;

public class BarcodeFormatException extends RuntimeException {
    private static final String DESCRIPTION = "The length of the number must be 13 digits";

    public BarcodeFormatException() {
        super(DESCRIPTION);
    }

}
