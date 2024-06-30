package com.pnuema.java.barcode.barcodeapi;

import com.pnuema.java.barcode.EncodingType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Optional;

@SuppressWarnings("unused")
@Schema(name = "BarcodeBody", requiredMode = Schema.RequiredMode.REQUIRED)
public class BarcodeBody {
    @Schema(
            description = "Symbology type",
            type = "String",
            example = "upca",
            implementation = EncodingType.class,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String type;

    @Schema(
            description = "Barcode data to be encoded",
            type = "string",
            example = "123456789012",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String data;

    @Schema(
            example = "png",
            allowableValues = { "png", "jpg", "gif" },
            defaultValue = "png",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String imageFormat;

    @Schema(
            description = "Desired image width",
            type = "int",
            example = "400",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private Integer w;

    @Schema(
            description = "Desired image height",
            type = "int",
            example = "200",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private Integer h;

    @Schema(
            allowableValues = { "false", "true" },
            defaultValue = "false",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private boolean label;

    @Schema(
            description = "Hex color code for bars",
            type = "string",
            example = "000000",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String barcolor;

    @Schema(
            description = "Hex color code for spaces and background",
            type = "string",
            example = "ffffff",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String background;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    public Optional<String> getImageFormat() {
        if (imageFormat == null) {
            return Optional.empty();
        } else {
            return Optional.of(imageFormat);
        }
    }

    public Optional<Integer> getW() {
        if (w == null)
            return Optional.empty();
        else
            return Optional.of(w);
    }

    public void setW(Integer w) {
        this.w = w;
    }

    public Optional<Integer> getH() {
        if (h == null)
            return Optional.empty();
        else
            return Optional.of(h);
    }

    public void setH(Integer h) {
        this.h = h;
    }

    public Optional<Boolean> isLabel() {
        return Optional.of(label);
    }

    public void setLabel(boolean label) {
        this.label = label;
    }

    public Optional<String> getBarcolor() {
        if (barcolor == null)
            return Optional.empty();
        else
            return Optional.of(barcolor);
    }

    public void setBarcolor(String barcolor) {
        this.barcolor = barcolor;
    }

    public Optional<String> getBackground() {
        if (background == null)
            return Optional.empty();
        else
            return Optional.of(background);
    }

    public void setBackground(String background) {
        this.background = background;
    }
}
