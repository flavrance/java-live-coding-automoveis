package com.example.demo.services;

import com.example.demo.dtos.Automovel;
import com.example.demo.infraestructure.repositories.AutomovelRepository;
import com.example.demo.models.AutomovelModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AutomovelService {
    @Autowired
    private AutomovelRepository automovelRepository;

    public Automovel cadastraAutomovel (Automovel automovel) throws Exception {

        if(automovel == null) throw new Exception("Automovel é obrigatório");

        AutomovelModel model = AutomovelModel.builder()
                .valor(automovel.getValor())
                .marca(automovel.getMarca())
                .modelo(automovel.getModelo())
                .dataCadastro(LocalDate.now()).build();

        AutomovelModel saved = automovelRepository.saveAndFlush(model);

        Automovel result = Automovel.builder()
                .id(saved.getId())
                .valor(saved.getValor())
                .modelo(saved.getModelo())
                .marca(saved.getMarca())
                .dataCadastro(saved.getDataCadastro())
                .build();

        return  result;

    }

    public ArrayList<Automovel> getAll() {
        List<AutomovelModel> list = automovelRepository.findAll();

        ArrayList<Automovel> automoveis = new ArrayList<>();
        list.forEach(a -> automoveis.add(Automovel.builder()
                        .id(a.getId())
                        .valor(a.getValor())
                        .modelo(a.getModelo())
                        .marca(a.getMarca())
                        .dataCadastro(a.getDataCadastro())
                .build()));

        return automoveis;
    }

}
