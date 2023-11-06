package org.unitelecuidado.api_rest.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unitelecuidado.api_rest.domain.paciente.Paciente;
import org.unitelecuidado.api_rest.dto.paciente.PacienteAtualizar;
import org.unitelecuidado.api_rest.dto.paciente.PacienteCadastro;
import org.unitelecuidado.api_rest.repository.PacienteRepository;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> listaID(@PathVariable Long id) {
        Optional<Paciente> pacienteOptional = repository.findById(id);
        if (pacienteOptional.isPresent()) {
            Paciente paciente = pacienteOptional.get();
            return ResponseEntity.ok(paciente);
        } else {
            return ResponseEntity.notFound().build();
        }
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
