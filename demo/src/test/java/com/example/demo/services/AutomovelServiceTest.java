package com.example.demo.services;

import com.example.demo.dtos.Automovel;
import com.example.demo.infraestructure.repositories.AutomovelRepository;
import com.example.demo.models.AutomovelModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AutomovelServiceTest {

    @Mock
    private AutomovelRepository repository;

    private AutomovelService automovelService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        automovelService = new AutomovelService(repository);
    }

    @Test
    void criarAutomovel_MustReturnMarcaVolkswagem() throws Exception {
        LocalDate date = LocalDate.now();
        AutomovelModel model = AutomovelModel.builder()
                .dataCadastro(date)
                .marca("Volkswagem")
                .valor(1000000D)
                .modelo("T-Cross")
                .build();

        Automovel automovel = Automovel.builder()
                .dataCadastro(date)
                .marca("Volkswagem")
                .valor(1000000D)
                .modelo("T-Cross")
                .build();

        when(repository.save(any(AutomovelModel.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));


        Automovel result = automovelService.cadastraAutomovel(automovel);

        // Assert
        assertNotNull(result);
        assertEquals("Volkswagem", result.getMarca());
        assertEquals(automovel.getModelo(), result.getModelo());
        assertEquals(automovel.getValor(), result.getValor());
        verify(repository, times(1)).save(any(AutomovelModel.class));
    }

    @Test
    void getAll_MustReturnListOfAutomoveis() {

        LocalDate date = LocalDate.now();
        AutomovelModel model = AutomovelModel.builder()
                .id(1)
                .dataCadastro(date)
                .marca("Volkswagem")
                .valor(1000000D)
                .modelo("T-Cross")
                .build();

        String marca = "Volkswagem";

        when(repository.findAll()).thenReturn(Arrays.asList(model));


        ArrayList<Automovel> resultados = automovelService.getAll();

        assertFalse(resultados.isEmpty());
        assertEquals(1, resultados.size());
        assertEquals(marca, resultados.get(0).getMarca());
        verify(repository, times(1)).findAll();
    }
}
