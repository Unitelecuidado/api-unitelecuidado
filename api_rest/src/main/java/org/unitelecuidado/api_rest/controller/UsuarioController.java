package org.unitelecuidado.api_rest.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.unitelecuidado.api_rest.domain.usuario.Usuario;
import org.unitelecuidado.api_rest.dto.usuario.UsuarioAtualizar;
import org.unitelecuidado.api_rest.dto.usuario.UsuarioCadastro;
import org.unitelecuidado.api_rest.repository.UsuarioRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid UsuarioCadastro dados, UriComponentsBuilder uriBuilder) {
        Usuario usuario = new Usuario(dados);
        repository.save(usuario);
        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }
    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = repository.findAll();
        return ResponseEntity.ok(usuarios);
    }
    @PutMapping
    @Transactional
    public ResponseEntity<?> atualizar(@RequestBody @Valid UsuarioAtualizar dados) {
        Optional<Usuario> usuarioOptional = repository.findById(dados.id());
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.atualizarDados(dados);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = repository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.excluir();
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
