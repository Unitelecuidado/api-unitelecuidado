package org.unitelecuidado.api_rest.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Cadastrar um novo usuário", description = "Endpoint para cadastrar um novo usuário.")
    @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)))

    @PostMapping
    @Transactional
    public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid UsuarioCadastro dados, UriComponentsBuilder uriBuilder) {
        Usuario usuario = new Usuario(dados);
        repository.save(usuario);
        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @ApiResponse(responseCode = "200", description = "Lista de usuários", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)))

    @GetMapping
    @Operation(summary = "Listar usuários", description = "Endpoint para listar todos os usuários.")
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = repository.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Atualizar dados do usuário", description = "Endpoint para atualizar dados de um usuário.")
    @ApiResponse(responseCode = "200", description = "Dados do usuário atualizados com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")

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

    @Operation(summary = "Excluir usuário", description = "Endpoint para excluir um usuário.")
    @ApiResponse(responseCode = "200", description = "Usuário excluído com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir( @Parameter(description = "ID do usuário a ser excluído", required = true) @PathVariable Long id) {
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
