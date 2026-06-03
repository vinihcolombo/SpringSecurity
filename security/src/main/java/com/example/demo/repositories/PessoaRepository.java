package com.example.demo.repositories;

import com.example.demo.models.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PessoaRepository extends JpaRepository<PessoaModel, UUID> {
}
