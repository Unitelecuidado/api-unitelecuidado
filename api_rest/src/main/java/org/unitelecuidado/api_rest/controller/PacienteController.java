package org.unitelecuidado.api_rest.controller;


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

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastra(@RequestBody @Valid PacienteCadastro dados, UriComponentsBuilder uriBuilder) {
        Paciente paciente = new Paciente(dados);
        repository.save(paciente);
        URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(paciente);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listaAtivo() {
        List<Paciente> pacientes = repository.findAllByAtivoTrue();
        return ResponseEntity.ok(pacientes);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Paciente>> listaTodos() {
        List<Paciente> pacientes = repository.findAll();
        return ResponseEntity.ok(pacientes);
    }
    @GetMapping("/inativos")
    public ResponseEntity<List<Paciente>> listaInativo() {
        List<Paciente> pacientes = repository.findAllByAtivoFalse();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> listaID(@PathVariable Long id) {
        Optional<Paciente> pacienteOptional = repository.findById(id);
        return pacienteOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

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

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remove(@PathVariable Long id) {
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
