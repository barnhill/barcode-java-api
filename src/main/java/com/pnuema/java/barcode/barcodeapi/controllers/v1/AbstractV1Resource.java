package com.pnuema.java.barcode.barcodeapi.controllers.v1;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(
        path = "/v1",
        produces = { MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE }
)
public abstract class AbstractV1Resource {}
