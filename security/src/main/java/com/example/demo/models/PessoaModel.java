package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity // Define a classe como uma entidade, criando uma tabela no banco
@Table(name = "TBL_pessoa") // Define o nome da tabela no banco de dados
@Data // Gera Getter e Setter automaticamente
public class PessoaModel {

    @Id // Designa a variável como a coluna de ID no banco
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Meio no qual o ID será gerado
    private Long id;

    private String nome;

    @OneToMany // Relação um para vários
    private List<JobModel> jobModelList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<JobModel> getJobModelList() {
        return jobModelList;
    }

    public void setJobModelList(List<JobModel> jobModelList) {
        this.jobModelList = jobModelList;
    }
}