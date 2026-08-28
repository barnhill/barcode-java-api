package com.pnuema.java.barcode.barcodeapi.controllers.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeRedirectController {

    @GetMapping(value = {"", "/"})
    public String index() {
        // "forward:" tells Spring MVC to completely ignore your prefix/suffix view config
        // and fetch the actual static asset directly.
        return "redirect:/index.html";
    }
}