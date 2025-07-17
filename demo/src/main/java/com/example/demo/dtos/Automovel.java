package com.example.demo.dtos;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@Builder
public class Automovel {
    private Integer id;
    private String marca;
    private String modelo;
    private Double valor;
    private LocalDate dataCadastro;
}
