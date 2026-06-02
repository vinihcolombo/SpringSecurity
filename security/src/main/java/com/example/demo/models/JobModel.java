package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity // Define a classe como uma entidade, criando uma tabela no banco
@Data // Gera Getter e Setter automaticamente
public class JobModel {

    @Id // Designa a variável como a coluna de ID no banco
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Meio no qual o ID será gerado
    private Long id;

    private String nome;
    private String endereco;



}
