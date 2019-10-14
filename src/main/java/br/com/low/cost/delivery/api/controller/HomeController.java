package br.com.low.cost.delivery.api.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private static final String STATIC_HOME_CONTENT = "<body><p style=\"text-align: center;\">&nbsp;</p><p style=\"text-align: center;\">&nbsp;</p><p style=\"text-align: center;\">&nbsp;</p><p style=\"text-align: center;\">&nbsp;</p><h1 style=\"text-align: center;\"><strong>API Low Coast Delivery</strong></h1><p style=\"text-align: center;\">v1.0</p><p style=\"text-align: center;\">&nbsp;</p><p style=\"text-align: center;\">&nbsp;</p><p style=\"text-align: center;\">&nbsp;</p><p style=\"text-align: center;\">&nbsp;</p><p style=\"text-align: center;\">&nbsp;</p><p style=\"text-align: center;\">&nbsp;</p><p style=\"text-align: center;\">&nbsp;</p><p style=\"text-align: center;\">&nbsp;</p><p style=\"text-align: center;\">&nbsp;</p><p style=\"text-align: center;\">&nbsp;</p><p style=\"text-align: center;\">&nbsp;</p><p style=\"text-align: center;\">&nbsp;</p><p style=\"text-align: center;\"><strong>Author</strong>: Welinton Carvalho de P&aacute;dua</p></body>";

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity home() {
        return ResponseEntity.ok(STATIC_HOME_CONTENT);
    } 

}
