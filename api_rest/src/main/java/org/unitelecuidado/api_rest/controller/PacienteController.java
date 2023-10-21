package org.unitelecuidado.api_rest.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unitelecuidado.api_rest.domain.paciente.Paciente;
import org.unitelecuidado.api_rest.dto.paciente.PacienteAtualizar;
import org.unitelecuidado.api_rest.dto.paciente.PacienteCadastro;
import org.unitelecuidado.api_rest.dto.usuario.UsuarioAtualizar;
import org.unitelecuidado.api_rest.repository.PacienteRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastra(@RequestBody @Valid PacienteCadastro dados){
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public List<Paciente> lista(){
        return repository.findAll();
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid PacienteAtualizar dados){
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarDados(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void remove(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.excluir();
    }





}
