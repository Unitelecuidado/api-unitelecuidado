package org.unitelecuidado.api_rest.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unitelecuidado.api_rest.domain.usuario.Usuario;
import org.unitelecuidado.api_rest.dto.usuario.UsuarioAtualizar;
import org.unitelecuidado.api_rest.dto.usuario.UsuarioCadastro;
import org.unitelecuidado.api_rest.repository.UsuarioRepository;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid UsuarioCadastro dados){
        repository.save(new Usuario(dados));
    }

    @GetMapping
    public List<Usuario> listar(){
        return repository.findAll();
    }
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid UsuarioAtualizar dados){
        var usuario = repository.getReferenceById(dados.id());
        usuario.atualizarDados(dados);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var usuario = repository.getReferenceById(id);
        usuario.excluir();
    }


}
