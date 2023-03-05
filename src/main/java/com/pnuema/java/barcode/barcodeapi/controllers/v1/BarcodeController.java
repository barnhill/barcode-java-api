package com.pnuema.java.barcode.barcodeapi.controllers.v1;

import com.pnuema.java.barcode.Barcode;
import com.pnuema.java.barcode.EncodingType;
import com.pnuema.java.barcode.barcodeapi.BarcodeBody;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

@RestController
public class BarcodeController extends AbstractV1Resource {

    @GetMapping(value = "/barcode/{type}/data/{data}")
    @Cacheable("barcodes")
    public ResponseEntity<byte[]> getBarcodeImage(
            @PathVariable String type,
            @PathVariable String data,
            @RequestParam(name = "w") Optional<Integer> width,
            @RequestParam(name = "h") Optional<Integer> height,
            @RequestParam(name = "label") Optional<Boolean> includeLabel,
            @RequestParam(name = "barcolor") Optional<String> barColor,
            @RequestParam(name = "background") Optional<String> background) throws IOException {

        return generateBarcode(
                type,
                data,
                width,
                height,
                includeLabel,
                barColor,
                background
        );
    }

    @PostMapping(value = "/barcode/",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Cacheable("barcodes")
    public ResponseEntity<byte[]> getBarcodeImage(@RequestBody BarcodeBody body) throws IOException {

        return generateBarcode(
                body.getType(),
                body.getData(),
                body.getW(),
                body.getH(),
                body.isLabel(),
                body.getBarcolor(),
                body.getBackground()
        );
    }

    private ResponseEntity<byte[]> generateBarcode(
            String type,
            String data,
            Optional<Integer> width,
            Optional<Integer> height,
            Optional<Boolean> includeLabel,
            Optional<String> barColor,
            Optional<String> background) throws IOException {

        Barcode barcode = new Barcode();

        includeLabel.ifPresent(barcode::setIncludeLabel);
        width.ifPresent(barcode::setWidth);
        height.ifPresent(barcode::setHeight);
        barColor.ifPresent(s -> barcode.setForeColor(hex2Rgb(s)));
        background.ifPresent(s -> barcode.setBackColor(hex2Rgb(s)));

        EncodingType typeEnum = convertTypeStringToEnum(type);

        HttpHeaders responseHeaders = new HttpHeaders();

        if (typeEnum == null) {
            return ResponseEntity
                    .badRequest()
                    .headers(responseHeaders)
                    .body(null);
        }

        BufferedImage image = null;
        Exception exception = null;
        try {
            image = (BufferedImage) barcode.encode(typeEnum, data);
        } catch (Exception ex) {
            exception = ex;
        }

        //attach debug info to header
        responseHeaders.set("x-barcode-version", barcode.getTitle() + " " + barcode.getVersion());
        responseHeaders.set("x-encoded-type", typeEnum.name());
        responseHeaders.set("x-encoded-value",  barcode.getEncodedValue());
        responseHeaders.set("x-encoding-time", barcode.getEncodingTime() + " ms");
        responseHeaders.set("x-draw-time",  barcode.getDrawTime() + " ms");
        responseHeaders.set("x-raw-value", barcode.getRawData());
        responseHeaders.set("x-label-font", barcode.getLabelFont().getName());
        responseHeaders.set("x-served-by", getMachineName());

        if (exception != null || image == null) {
            return ResponseEntity
                    .badRequest()
                    .headers(responseHeaders)
                    .body(null);
        }

        return ResponseEntity
                .ok()
                .headers(responseHeaders)
                .contentType(MediaType.IMAGE_PNG)
                .body(getImgBytes(image));
    }

    private String getMachineName() {
        try {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            return addr.getHostName();
        }
        catch (UnknownHostException ex) {
            return "Unknown";
        }
    }

    @Nullable
    private EncodingType convertTypeStringToEnum(String type) {
        return switch (type.toLowerCase()) {
            case "telepen" -> EncodingType.TELEPEN;
            case "standard2of5", "industrial2of5" -> EncodingType.Standard2of5;
            case "postnet" -> EncodingType.PostNet;
            case "pharmacode" -> EncodingType.PHARMACODE;
            case "msi2mod10" -> EncodingType.MSI_2Mod10;
            case "msimod10" -> EncodingType.MSI_Mod10;
            case "msimod11" -> EncodingType.MSI_Mod11;
            case "msimod11mod10" -> EncodingType.MSI_Mod11_Mod10;
            case "jan13" -> EncodingType.JAN13;
            case "itf14" -> EncodingType.ITF14;
            case "isbn", "bookland" -> EncodingType.ISBN;
            case "interleaved2of5" -> EncodingType.Interleaved2of5;
            case "fim" -> EncodingType.FIM;
            case "code128a" -> EncodingType.CODE128A;
            case "code128b" -> EncodingType.CODE128B;
            case "code128c" -> EncodingType.CODE128C;
            case "code128" -> EncodingType.CODE128;
            case "code93" -> EncodingType.CODE93;
            case "code39mod43" -> EncodingType.CODE39_Mod43;
            case "code39", "logmars" -> EncodingType.CODE39;
            case "code39extended" -> EncodingType.CODE39Extended;
            case "code11", "usd8" -> EncodingType.CODE11;
            case "codabar" -> EncodingType.Codabar;
            case "upca", "ucc12" -> EncodingType.UPCA;
            case "upc3" -> EncodingType.UPCE;
            case "upcsup2" -> EncodingType.UPC_SUPPLEMENTAL_2DIGIT;
            case "upcsup5" -> EncodingType.UPC_SUPPLEMENTAL_5DIGIT;
            case "ean8" -> EncodingType.EAN8;
            case "ean13", "ucc13" -> EncodingType.EAN13;
            default -> null;
        };

    }

    private byte [] getImgBytes(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", baos);
        return baos.toByteArray();
    }

    /**
     *
     * @param colorStr e.g. "FFFFFF" or "00FFFF"
     * @return {@link Color}
     */
    @Nullable
    private static Color hex2Rgb(String colorStr) {
        if (colorStr.length() == 6) {
            //RGB
            return new Color(
                    Integer.valueOf(colorStr.substring(0, 2), 16),
                    Integer.valueOf(colorStr.substring(2, 4), 16),
                    Integer.valueOf(colorStr.substring(4, 6), 16));
        } else if (colorStr.length() == 8) {
            //ARGB
            return new Color(
                    Integer.valueOf(colorStr.substring(2, 4), 16),
                    Integer.valueOf(colorStr.substring(4, 6), 16),
                    Integer.valueOf(colorStr.substring(6, 8), 16),
                    Integer.valueOf(colorStr.substring(0, 2), 16));
        }

        return null;
    }
}
