package com.example.demo.controllers;

import com.example.demo.dtos.Automovel;
import com.example.demo.services.AutomovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;

@Controller
@RequestMapping("automoveis")
public class AutomovelController {

    @Autowired
    private AutomovelService automovelService;
    @GetMapping(path = "/", produces = "application/json")
    public ResponseEntity<ArrayList<Automovel>> getAutomoveis() {
        return ResponseEntity.ok(automovelService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity cadastraAutomovel (@RequestBody Automovel automovel) {
        Automovel result = null;
        try {

            result = automovelService.cadastraAutomovel(automovel);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }






    }



}