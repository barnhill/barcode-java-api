package com.pnuema.java.barcode.barcodeapi.errors;

public final class ErrorResponse {
    public int httpStatusCode;
    public String errorMessage;
    public String stackTrace;

    public ErrorResponse(int httpStatusCode, String errorMessage, String stackTrace) {
        this.httpStatusCode = httpStatusCode;
        this.errorMessage = errorMessage;
        this.stackTrace = stackTrace;
    }
}
