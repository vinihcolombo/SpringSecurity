package com.example.demo.controllers;

import com.example.demo.models.PessoaModel;
import com.example.demo.repositories.PessoaRepository;
import com.example.demo.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping(path = "/pessoas") // Caminho usado para chegar à tabela
@RestController // Diz que o controller será usado como REST
public class PessoaController {

    @Autowired // Instância da classe chamada
    private PessoaService pessoaService;

    @GetMapping // Notação para o comando de GET no CRUD (Visualizar)
    public ResponseEntity<List<PessoaModel>> buscarTodosOsPessoas(){
        List<PessoaModel> requisicao = pessoaService.buscarTodosPessoas();
        return ResponseEntity.ok().body(requisicao);
    }

    @PostMapping // Notação para o comando de POST no CRUD (criar)
    public ResponseEntity<PessoaModel> criarPessoa(@RequestBody PessoaModel pessoaModel){ //RequestBody pede um objeto java para ser usado

        PessoaModel requisicao =  pessoaService.criarPessoa(pessoaModel);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(pessoaModel.getId())
                .toUri();
        return ResponseEntity.created(uri).body(requisicao);
    }

    @DeleteMapping("/{id}") // Notação para o comando DELETE no CRUD (deletar)
    public ResponseEntity<?> deletar(@PathVariable Long id){ // PathVariable Pede um objeto que serve como caminho
        pessoaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}") // Notação para o comando de GET no CRUD (visualizar)
    public Optional<PessoaModel> buscarPessoaPorId(@PathVariable Long id){ return pessoaService.buscarPessoaId(id); }

    @PutMapping("/{id}") // Notação para o comando de PUT no CRUD (editar)
    public ResponseEntity<PessoaModel> atualizar(@PathVariable Long id, @RequestBody PessoaModel pessoaModel){
        PessoaModel requisicao = pessoaService.atualizarPessoa(id,pessoaModel);

        return ResponseEntity.ok().body(requisicao);
    }

}
