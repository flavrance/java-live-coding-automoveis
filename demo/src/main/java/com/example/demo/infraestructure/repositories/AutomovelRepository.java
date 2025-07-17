package com.example.demo.infraestructure.repositories;

import com.example.demo.models.AutomovelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomovelRepository extends JpaRepository<AutomovelModel, Integer> {

}
