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
import org.unitelecuidado.api_rest.domain.paciente.Paciente;
import org.unitelecuidado.api_rest.dto.paciente.PacienteAtualizar;
import org.unitelecuidado.api_rest.dto.paciente.PacienteCadastro;
import org.unitelecuidado.api_rest.repository.PacienteRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")

public class PacienteController {
    @Autowired
    private PacienteRepository repository;

    @Operation(summary = "Cadastrar um novo paciente", description = "Endpoint para cadastrar um novo paciente.")
    @ApiResponse(responseCode = "201", description = "Paciente cadastrado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Paciente.class)))

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastra(@RequestBody @Valid PacienteCadastro dados, UriComponentsBuilder uriBuilder) {
        Paciente paciente = new Paciente(dados);
        repository.save(paciente);
        URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(paciente);
    }

    @Operation(summary = "Listar pacientes ativos", description = "Endpoint para listar pacientes ativos.")
    @ApiResponse(responseCode = "200", description = "Lista de pacientes ativos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Paciente.class)))

    @GetMapping
    public ResponseEntity<List<Paciente>> listaAtivo() {
        List<Paciente> pacientes = repository.findAllByAtivoTrue();
        return ResponseEntity.ok(pacientes);
    }

    @Operation(summary = "Listar todos os pacientes", description = "Endpoint para listar todos os pacientes.")
    @ApiResponse(responseCode = "200", description = "Lista de todos os pacientes", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Paciente.class)))

    @GetMapping("/getAll")
    public ResponseEntity<List<Paciente>> listaTodos() {
        List<Paciente> pacientes = repository.findAll();
        return ResponseEntity.ok(pacientes);
    }

    @Operation(summary = "Listar pacientes inativos", description = "Endpoint para listar pacientes inativos.")
    @ApiResponse(responseCode = "200", description = "Lista de pacientes inativos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Paciente.class)))

    @GetMapping("/inativos")
    public ResponseEntity<List<Paciente>> listaInativo() {
        List<Paciente> pacientes = repository.findAllByAtivoFalse();
        return ResponseEntity.ok(pacientes);
    }

    @Operation(summary = "Obter paciente por ID", description = "Endpoint para obter um paciente pelo ID.")
    @ApiResponse(responseCode = "200", description = "Paciente encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Paciente.class)))
    @ApiResponse(responseCode = "404", description = "Paciente não encontrado")

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> listaID(@Parameter(description = "ID do paciente a ser obtido", required = true) @PathVariable Long id) {
        Optional<Paciente> pacienteOptional = repository.findById(id);
        return pacienteOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualizar dados do paciente", description = "Endpoint para atualizar dados de um paciente.")
    @ApiResponse(responseCode = "200", description = "Dados do paciente atualizados com sucesso")
    @ApiResponse(responseCode = "404", description = "Paciente não encontrado")

    @PutMapping
    @Transactional
    public ResponseEntity<?> atualizar(@RequestBody @Valid PacienteAtualizar dados) {
        Optional<Paciente> pacienteOptional = repository.findById(dados.id());
        if (pacienteOptional.isPresent()) {
            Paciente paciente = pacienteOptional.get();
            paciente.atualizarDados(dados);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Remover paciente", description = "Endpoint para remover um paciente.")
    @ApiResponse(responseCode = "200", description = "Paciente removido com sucesso")
    @ApiResponse(responseCode = "404", description = "Paciente não encontrado")

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remove(@Parameter(description = "ID do paciente a ser removido", required = true) @PathVariable Long id) {
        Optional<Paciente> pacienteOptional = repository.findById(id);
        if (pacienteOptional.isPresent()) {
            Paciente paciente = pacienteOptional.get();
            paciente.excluir();
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
