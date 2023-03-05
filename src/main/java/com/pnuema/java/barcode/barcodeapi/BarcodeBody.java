package com.pnuema.java.barcode.barcodeapi;

import java.util.Optional;

@SuppressWarnings("unused")
public class BarcodeBody {
    private String type;
    private String data;
    private Integer w;
    private Integer h;
    private boolean label;
    private String barcolor;
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
