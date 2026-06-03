package com.example.demo.services;

import com.example.demo.models.PessoaModel;
import com.example.demo.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service // Explica que estamos na camada Service
public class PessoaService {

    @Autowired // Autowired cria uma instância da classe chamada
    private PessoaRepository pessoaRepository;

    public List<PessoaModel> buscarTodosPessoas() { return pessoaRepository.findAll(); }

    public PessoaModel criarPessoa(PessoaModel pessoaModel) { return pessoaRepository.save(pessoaModel); }

    public Optional<PessoaModel> buscarPessoaId(UUID id) { return pessoaRepository.findById(id); }

    public PessoaModel atualizarPessoa(UUID id, PessoaModel PessoaModel) {
        PessoaModel model = pessoaRepository.findById(id).get();
        model.setNome(PessoaModel.getNome());
        return pessoaRepository.save(model);
    }

    public void excluir(UUID id) { pessoaRepository.deleteById(id);}

}
